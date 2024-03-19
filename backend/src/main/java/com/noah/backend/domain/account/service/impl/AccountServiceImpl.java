package com.noah.backend.domain.account.service.impl;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.account.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.noah.backend.global.exception.account.AccountNotFoundException;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
//    private final MemberRepository memberRepository;

    @Override
    public List<Account> getMyAccountList(Long memberId) {
        return null;
    }

    @Transactional
    @Override
    public AccountInfoDto getAccountInfo(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
//        String ownerName = memberRepository.findById(account.getOwnerId()).getName();
        AccountInfoDto accountInfo = AccountInfoDto.builder()
                .id(accountId)
                .name(account.getName())
                .bank(account.getBank())
                .accountNumber(account.getAccountNumber())
                .deposit(account.getDeposit())
                .withdraw(account.getWithdraw())
                .targetAmount(account.getTargetAmount())
                .perAmount(account.getPerAmount())
                .paymentDate(account.getPaymentDate())
                .build();
        return accountInfo;
    }

    @Transactional
    @Override
    public Long createAccount(Long memberId, AccountPostDto accountPostDto) {

//        Member member = memberRepository.searchById(memberId)

        Account account = Account.builder()
                .name(accountPostDto.getName())
                .targetAmount(accountPostDto.getTargetAmount())
                .perAmount(accountPostDto.getPerAmount())
                .paymentDate(accountPostDto.getPaymentDate())
                .build();

        Account savedAccount = accountRepository.save(account);
        return account.getId();
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        accountRepository.deleteById(accountId);
    }

    @Override
    public Long updateAccount(Long accountId) {
        Account account = ac
        return null;
    }
}
