package com.noah.backend.domain.trade.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.domain.bank.dto.requestDto.TransactionHistoryReqDto;
import com.noah.backend.domain.bank.dto.responseDto.TransactionHistoryResDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradePostReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeDateAndTime;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;
import com.noah.backend.domain.trade.entity.Trade;
import com.noah.backend.domain.trade.repository.TradeRepository;
import com.noah.backend.domain.trade.service.TradeService;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.trade.TradeNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class TradeServiceImpl implements TradeService {

    private final BankService bankService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TravelRepository travelRepository;
    private final MemberService memberService;
    private final TradeRepository tradeRepository;

    @Override
    public void createTrade(TradePostReqDto tradePostReqDto) {
        Account account = accountRepository.findById(tradePostReqDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
        Trade trade = Trade.builder()
                .type(tradePostReqDto.getTradeType())
                .name(tradePostReqDto.getName())
                .date(tradePostReqDto.getDate())
                .time(tradePostReqDto.getTime())
                .cost(tradePostReqDto.getCost())
                .amount(tradePostReqDto.getAmount())
                .account(account)
                .build();
        tradeRepository.save(trade);
        return;
    }


    @Transactional
    @Override
    public List<TradeGetResDto> getTradeList(TradeGetReqDto tradeGetReqDto) throws JsonProcessingException {
        Travel travel = travelRepository.findById(tradeGetReqDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        Account account = accountRepository.findById(travel.getGroupAccount().getAccount().getId()).orElseThrow(AccountNotFoundException::new);
        String startDate = tradeGetReqDto.getStartDate();
        String endDate = tradeGetReqDto.getEndDate();

        /* 만약 거래 조회 내역이 없으면(초기상태이면) */
        if (account.getStartDate() == null && account.getEndDate() == null) {
            account.setStartDate(tradeGetReqDto.getStartDate());
            account.setEndDate(tradeGetReqDto.getEndDate());
            /* 은행에서 조회해서 가져옴 */
            fetchAndSaveTradeHistory(account, startDate, endDate, tradeGetReqDto);
        }

        int savedStartDate = Integer.parseInt(account.getStartDate());
        int savedEndDate = Integer.parseInt(account.getEndDate());
        int intStartDate = Integer.parseInt(startDate);
        int intEndDate = Integer.parseInt(endDate);

        /* 만약 저장되어있는 조회 시점 내에 현재 요청이 포함되어 있으면 */
        if (savedStartDate < intStartDate && savedEndDate > intEndDate) {
            /* DB에 저장된 거래내역을 바로 가져옴 */
            return tradeRepository.getTradeList(account.getId(), tradeGetReqDto).orElseThrow(TradeNotFoundException::new);
        } else {
            /* 없으면 최신화하고 은행에서가져옴 */
            account.setStartDate(tradeGetReqDto.getStartDate());
            account.setEndDate(tradeGetReqDto.getEndDate());
            fetchAndSaveTradeHistory(account, startDate, endDate, tradeGetReqDto);
            return tradeRepository.getTradeList(account.getId(), tradeGetReqDto).orElseThrow(TradeNotFoundException::new);
        }
    }

    /* 은행에서 가져오고 저장하는 메서드 */
    private void fetchAndSaveTradeHistory(Account account, String startDate, String endDate, TradeGetReqDto tradeGetReqDto) throws JsonProcessingException {
        /* 은행 코드 */
        Map<String, String> bankCodeMap = Map.of(
                "한국은행", "001",
                "산업은행", "002",
                "기업은행", "003",
                "국민은행", "004"
        );
        String bankCode = bankCodeMap.get(account.getBankName());

        /* 은행 메서드를 사용하기 위한 reqDto 생성 */
        TransactionHistoryReqDto transactionHistoryReqDto = TransactionHistoryReqDto.builder()
                .userKey(account.getMember().getUserKey())
                .bankCode(bankCode)
                .accountNo(account.getAccountNumber())
                .startDate(startDate)
                .endDate(endDate)
                .transactionType(tradeGetReqDto.getTransactionType())
                .orderByType(tradeGetReqDto.getOrderByType())
                .build();

        /* 은행에서 가져온 내역 */
        List<TransactionHistoryResDto> bankTradeHistory = bankService.transactionHistory(transactionHistoryReqDto);
        /* 만약에 은행에서도 거래내역이 조회가 안되면 종료 */
        if (bankTradeHistory == null) {
            return;
        }

        for (TransactionHistoryResDto bankTrade : bankTradeHistory) {
            Optional<TradeDateAndTime> existingTrade = tradeRepository.getTradeDateAndTime(bankTrade.getDate(), bankTrade.getTime());
            if (existingTrade.isEmpty()) {
                Trade trade = Trade.builder()
                        .type(bankTrade.getType())
                        .name(bankTrade.getName())
                        .date(bankTrade.getDate())
                        .time(bankTrade.getTime())
                        .cost(bankTrade.getCost())
                        .amount(bankTrade.getAmount())
                        .account(account)
                        .build();
                accountRepository.save(account);
            }
        }
    }
}
