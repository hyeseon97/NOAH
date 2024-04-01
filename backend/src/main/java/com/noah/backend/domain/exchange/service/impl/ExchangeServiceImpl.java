package com.noah.backend.domain.exchange.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.apis.service.ForeignCurrencyService;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountWithdrawReqDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeRatePutDto;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeInfoDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeRateGetDto;
import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.exchange.repository.ExchangeRepository;
import com.noah.backend.domain.exchange.service.ExchangeService;
import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.groupaccount.repository.GroupAccountRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.exchange.ExchangeCurrencyNotAcceptableException;
import com.noah.backend.global.exception.exchange.ExchangeFailedException;
import com.noah.backend.global.exception.exchange.ExchangeNotFoundException;
import com.noah.backend.global.exception.groupaccount.GroupAccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final BankService bankService;
    private final TravelRepository travelRepository;
    private final GroupAccountRepository groupAccountRepository;
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final ForeignCurrencyService foreignCurrencyService;

    @Override
    public Long createExchange(String email, ExchangeReqDto exchangeReqDto) throws IOException {
        Travel travel = travelRepository.findById(exchangeReqDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        GroupAccount groupAccount = groupAccountRepository.findById(travel.getGroupAccount().getId()).orElseThrow(GroupAccountNotFoundException::new);
        Account account = accountRepository.findById(groupAccount.getAccount().getId()).orElseThrow(AccountNotFoundException::new);
        Member member = memberRepository.findById(account.getMember().getId()).orElseThrow(MemberNotFoundException::new);

        /* 접근권한 */
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travel.getId()).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */


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

        Long exchangeId = exchangeRepository.getExchangeIdByTravelId(travel.getId());

        // 만약 환전 내역이 없다면
        if (exchangeId == null) {
            // 환전 생성

            Exchange exchange = Exchange.builder()
                    .currency(exchangeReqDto.getCurrency())
                    .exchangeAmount(exchangeReqDto.getExchangeAmount())
                    .groupAccount(groupAccount)
                    .build();
            exchangeRepository.save(exchange);
            return exchange.getId();

            // 기존 환전 내역이 있다면
        } else {
            Exchange exchange = exchangeRepository.findById(exchangeId).orElseThrow(ExchangeNotFoundException::new);
            if (!exchange.getCurrency().equals(exchangeReqDto.getCurrency())) {
                throw new ExchangeFailedException();
            }
            int previousAmount = exchange.getExchangeAmount();
            int currentAmount = exchangeReqDto.getExchangeAmount();
            int total = previousAmount + currentAmount;
            exchange.setExchangeAmount(total);

            return exchange.getId();
        }

    }

    @Override
    public ExchangeInfoDto getExchangeInfo(String email, Long travelId) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelId).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        Long exchangeId = exchangeRepository.getExchangeIdByTravelId(travelId);
        if (exchangeId == null) {
            return null;
        } else {
            Exchange exchange = exchangeRepository.findById(exchangeId).orElseThrow(ExchangeNotFoundException::new);

            ExchangeInfoDto exchangeInfoDto = ExchangeInfoDto.builder()
                    .currency(exchange.getCurrency())
                    .exchangeAmount(exchange.getExchangeAmount())
                    .build();

            return exchangeInfoDto;
        }
    }

    @Override
    public ExchangeRateGetDto getExchangeRate() {

        CurrencyDto currencyDto = foreignCurrencyService.getExchangeRate();
        ExchangeRateGetDto exchangeRateGetDto = ExchangeRateGetDto
            .builder()
            .USD(currencyDto.getBuyDollar())
            .JPY(currencyDto.getBuyYen())
            .CNY(currencyDto.getBuyYuan())
            .EUR(currencyDto.getBuyEuro()).build();

        return exchangeRateGetDto;
    }

    @Override
    public Long updateTargetExchangeRate(String email, ExchangeRatePutDto exchangeRatePutDto) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), exchangeRatePutDto.getTravelId()).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        Exchange exchange = exchangeRepository.getExchangeByTravelId(exchangeRatePutDto.getTravelId()).orElse(null);

        if(exchange == null){
            exchange = Exchange.builder()
                .currency(null)
                .exchangeAmount(0)
                .targetExchangeCurrency(exchangeRatePutDto.getTargetExchangeCurrency())
                .targetExchangeRate(exchangeRatePutDto.getTargetExchangeRate())
                .build();
            Exchange savedExchange = exchangeRepository.save(exchange);
            return savedExchange.getId();

        } else{
            exchange.setTargetExchangeCurrency(exchangeRatePutDto.getTargetExchangeCurrency());
            exchange.setTargetExchangeRate(exchangeRatePutDto.getTargetExchangeRate());
            return exchange.getId();
        }

    }
}
