package com.noah.backend.domain.account.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.domain.account.dto.requestDto.AccountUpdateDto;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountBalanceCheckReqDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;
    private final BankService bankService;

    @Transactional
    @Override
    public Long createAccount(AccountPostDto accountPostDto) {
        Travel travel = travelRepository.findById(accountPostDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        Member member = memberRepository.findById(accountPostDto.getMemberId()).orElseThrow(MemberNotFoundException::new);
        Account account = Account.builder()
                .accountNumber(accountPostDto.getAccountNumber())
                .bankName(accountPostDto.getBankName())
                .type("공동계좌")
                .member(member)
                .build();

        accountRepository.save(account);
        return account.getId();
    }

    @Transactional
    @Override
    public List<AccountInfoDto> getMyAccountList(Long memberId) throws IOException {
        List<AccountInfoDto> accountInfoDtoList = accountRepository.getMyAccountByMemberId(memberId).orElseThrow(AccountNotFoundException::new);

        /* 잔액이 보이는 시점에서 항상 최신화를 해야될 것 같음 */
        for (AccountInfoDto result : accountInfoDtoList) {
            Long accountId = result.getAccountId();
            // 계좌 찾고
            Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
            Map<String, String> bankCodeMap = Map.of(
                    "한국은행", "001",
                    "산업은행", "002",
                    "기업은행", "003",
                    "국민은행", "004"
            );
            String bankCode = bankCodeMap.get(account.getBankName());
            String userKey = account.getMember().getUserKey();

            // 은행에서 잔액조회
            BankAccountBalanceCheckReqDto bankAccountBalanceCheckReqDto = BankAccountBalanceCheckReqDto.builder()
                    .userKey(userKey)
                    .bankCode(bankCode)
                    .accountNo(account.getAccountNumber())
                    .build();
            int amount = bankService.bankAccountBalanceCheck(bankAccountBalanceCheckReqDto).getAccountBalance();
            account.setAmount(amount);
            accountRepository.save(account);
        }
        return accountInfoDtoList;
    }

    @Transactional
    @Override
    public Long updateAmount(AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
        account.setAmount(accountUpdateDto.getAmount());
        return account.getId();
    }
}
