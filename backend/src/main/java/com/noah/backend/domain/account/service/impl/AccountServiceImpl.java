package com.noah.backend.domain.account.service.impl;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.domain.account.dto.requestDto.AccountUpdateDto;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long createAccount(AccountPostDto accountPostDto) {
        Travel travel = travelRepository.findById(accountPostDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        Member member = memberRepository.findById(accountPostDto.getMemberId()).orElseThrow(MemberNotFoundException::new);
        Account account = Account.builder()
//                .accountNumber()
//                .bankName()
                .type("공동계좌")
                .member(member)
                .build();

        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public List<AccountInfoDto> getMyAccountList(Long memberId) {
        List<AccountInfoDto> accountInfoDtoList = accountRepository.getMyAccountByMemberId(memberId).orElseThrow(AccountNotFoundException::new);

        return accountInfoDtoList;
    }

    @Override
    public Long updateAmount(AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
        account.setAmount(accountUpdateDto.getAmount());
        accountRepository.save(account);
        return account.getId();
    }
}
