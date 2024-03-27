package com.noah.backend.domain.exchange.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountWithdrawReqDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeInfoDto;
import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.exchange.repository.ExchangeRepository;
import com.noah.backend.domain.exchange.service.ExchangeService;
import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.groupaccount.repository.GroupAccountRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.exchange.ExchangeCurrencyNotAcceptableException;
import com.noah.backend.global.exception.exchange.ExchangeFailedException;
import com.noah.backend.global.exception.exchange.ExchangeNotFoundException;
import com.noah.backend.global.exception.groupaccount.GroupAccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final BankService bankService;
    private final TravelRepository travelRepository;
    private final GroupAccountRepository groupAccountRepository;
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createExchange(ExchangeReqDto exchangeReqDto) throws IOException {
        Travel travel = travelRepository.findById(exchangeReqDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        GroupAccount groupAccount = groupAccountRepository.findById(travel.getGroupAccount().getId()).orElseThrow(GroupAccountNotFoundException::new);
        Account account = accountRepository.findById(groupAccount.getAccount().getId()).orElseThrow(AccountNotFoundException::new);
        Member member = memberRepository.findById(account.getMember().getId()).orElseThrow(MemberNotFoundException::new);

        Map<String, String> bankCodeMap = Map.of(
                "한국은행", "001",
                "산업은행", "002",
                "기업은행", "003",
                "국민은행", "004"
        );
        String bankCode = bankCodeMap.get(account.getBankName());
        int amount = exchangeReqDto.getAmount();
        String currencyName = exchangeReqDto.getCurrency();


        // 모임통장 계좌 출금
        BankAccountWithdrawReqDto bankAccountWithdrawReqDto = BankAccountWithdrawReqDto.builder()
                .userKey(member.getUserKey())
                .bankCode(bankCode)
                .accountNo(account.getAccountNumber())
                .transactionBalance(amount)
                .transactionSummary("E" + currencyName + "M:" + amount)
                .build();
        bankService.bankAccountWithdraw(bankAccountWithdrawReqDto);

        int currencyCode;

        switch (currencyName) {
            case "달러":
                currencyCode = 0;
                break;
            case "엔화":
                currencyCode = 1;
                break;
            case "위안화":
                currencyCode = 2;
                break;
            case "유로":
                currencyCode = 3;
                break;
            default:
                throw new ExchangeCurrencyNotAcceptableException();

        }

        Long exchangeId = exchangeRepository.getExchangeIdByTravelId(travel.getId());

        // 만약 환전 내역이 없다면
        if (exchangeId == null) {
            // 환전 생성

            Exchange exchange = Exchange.builder()
                    .currency(currencyCode)
                    .exchangeAmount(exchangeReqDto.getExchangeAmount())
                    .groupAccount(groupAccount)
                    .build();
            exchangeRepository.save(exchange);
            return exchange.getId();

            // 기존 환전 내역이 있다면
        } else {
            Exchange exchange = exchangeRepository.findById(exchangeId).orElseThrow(ExchangeNotFoundException::new);
            if (exchange.getCurrency() != currencyCode) {
                throw new ExchangeFailedException();
            }
            int previousAmount = exchange.getExchangeAmount();
            int currentAmount = exchangeReqDto.getExchangeAmount();
            int total = previousAmount + currentAmount;
            exchange.setExchangeAmount(total);
            exchangeRepository.save(exchange);

            return exchange.getId();
        }

    }

    @Override
    public ExchangeInfoDto getExchangeInfo(Long travelId) {
        Long exchangeId = exchangeRepository.getExchangeIdByTravelId(travelId);
        if (exchangeId == null) {
            return null;
        } else {
            Exchange exchange = exchangeRepository.findById(exchangeId).orElseThrow(ExchangeNotFoundException::new);

            int currencyCode = exchange.getCurrency();
            String currencyName;

            switch (currencyCode) {
                case 0:
                    currencyName = "달러";
                    break;
                case 1:
                    currencyName = "엔화";
                    break;
                case 2:
                    currencyName = "위안화";
                    break;
                case 3:
                    currencyName = "유로";
                    break;
                default:
                    throw new ExchangeCurrencyNotAcceptableException();

            }

            ExchangeInfoDto exchangeInfoDto = ExchangeInfoDto.builder()
                    .currency(currencyName)
                    .exchangeAmount(exchange.getExchangeAmount())
                    .build();

            return exchangeInfoDto;
        }
    }
}
