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
        accountRepository.save(account);
        return;
    }

    @Transactional
    @Override
    public List<TradeGetResDto> getTradeList(TradeGetReqDto tradeGetReqDto) throws JsonProcessingException {
        Travel travel = travelRepository.findById(tradeGetReqDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        Account account = accountRepository.findById(travel.getGroupAccount().getAccount().getId()).orElseThrow(AccountNotFoundException::new);

        String startDate = tradeGetReqDto.getStartDate();
        String endDate = tradeGetReqDto.getEndDate();

        /* 첫 조회 시 */
        if(account.getStartDate() == null && account.getEndDate() == null){
            account.setStartDate(tradeGetReqDto.getStartDate());
            account.setEndDate(tradeGetReqDto.getEndDate());

            /* 은행에서 거래내역 가져옴 */
            String bankCode = null;
            switch (account.getBankName()) {
                case "한국은행":
                    bankCode = "001";
                    break;
                case "산업은행":
                    bankCode = "002";
                    break;
                case "기업은행":
                    bankCode = "003";
                    break;
                case "국민은행":
                    bankCode = "004";
                    break;
                default:
                    break;
            }
            /* 은행 요청용 Dto 생성*/
            TransactionHistoryReqDto transactionHistoryReqDto = TransactionHistoryReqDto.builder()
                    .userKey(account.getMember().getUserKey())
                    .bankCode(bankCode)
                    .accountNo(account.getAccountNumber())
                    .startDate(startDate)
                    .endDate(endDate)
                    .transactionType(tradeGetReqDto.getTransactionType())
                    .orderByType(tradeGetReqDto.getOrderByType())
                    .build();
            List<TransactionHistoryResDto> bankTradeHistory = bankService.transactionHistory(transactionHistoryReqDto);
            if(bankTradeHistory == null){
                List<TradeGetResDto> result = new ArrayList<>();
                return result;
            }
            /* 거래 내역 생성*/
            for (TransactionHistoryResDto bankTrade : bankTradeHistory) {
                /* tradeDate와 tradeTime으로 찾고 */
                Optional<TradeDateAndTime> existingTrade = tradeRepository.getTradeDateAndTime(bankTrade.getDate(), bankTrade.getTime());
                /* 없으면 저장 */
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
            /* 불러오기 */
            List<TradeGetResDto> result = tradeRepository.getTradeList(account.getId(), tradeGetReqDto).orElseThrow(TradeNotFoundException::new);
            return result;
        }

        int savedStartDate = Integer.parseInt(account.getStartDate());
        int savedEndDate = Integer.parseInt(account.getEndDate());
        int intStartDate = Integer.parseInt(startDate);
        int intEndDate = Integer.parseInt(endDate);
        /* 거래 내역이 DB에 있을 때 */
        // 근데 이럴라면 20240203 이런식으로 와야될텐데
        if (savedStartDate < intStartDate && savedEndDate > intEndDate) {
            List<TradeGetResDto> result = tradeRepository.getTradeList(account.getId(), tradeGetReqDto).orElseThrow(TradeNotFoundException::new);
            return result;
        } else {
            /* 거래 내역이 DB에 없을 때 */

            /* 조회 시점 최신화 */
            account.setStartDate(tradeGetReqDto.getStartDate());
            account.setEndDate(tradeGetReqDto.getEndDate());

            /* 은행에서 거래내역 가져옴 */
            String bankCode = null;
            switch (account.getBankName()) {
                case "한국은행":
                    bankCode = "001";
                    break;
                case "산업은행":
                    bankCode = "002";
                    break;
                case "기업은행":
                    bankCode = "003";
                    break;
                case "국민은행":
                    bankCode = "004";
                    break;
                default:
                    break;
            }
            /* 은행 요청용 Dto 생성*/
            TransactionHistoryReqDto transactionHistoryReqDto = TransactionHistoryReqDto.builder()
                    .userKey(account.getMember().getUserKey())
                    .bankCode(bankCode)
                    .accountNo(account.getAccountNumber())
                    .startDate(startDate)
                    .endDate(endDate)
                    .transactionType(tradeGetReqDto.getTransactionType())
                    .orderByType(tradeGetReqDto.getOrderByType())
                    .build();
            List<TransactionHistoryResDto> bankTradeHistory = bankService.transactionHistory(transactionHistoryReqDto);
            if(bankTradeHistory == null){
                List<TradeGetResDto> result = new ArrayList<>();
                return result;
            }
            /* 거래 내역 생성*/
            for (TransactionHistoryResDto bankTrade : bankTradeHistory) {
                /* tradeDate와 tradeTime으로 찾고 */
                Optional<TradeDateAndTime> existingTrade = tradeRepository.getTradeDateAndTime(bankTrade.getDate(), bankTrade.getTime());
                /* 없으면 저장 */
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
            /* 불러오기 */
            List<TradeGetResDto> result = tradeRepository.getTradeList(account.getId(), tradeGetReqDto).orElseThrow(TradeNotFoundException::new);
            return result;
        }

    }
}
