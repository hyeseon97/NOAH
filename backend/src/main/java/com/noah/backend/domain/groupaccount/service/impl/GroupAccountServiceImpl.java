package com.noah.backend.domain.groupaccount.service.impl;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.groupaccount.repository.GroupAccountRepository;
import com.noah.backend.domain.groupaccount.service.GroupAccountService;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.dto.Response.GetTravelListResDto;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.groupaccount.GroupAccountAccessDeniedException;
import com.noah.backend.global.exception.groupaccount.GroupAccountNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupAccountServiceImpl implements GroupAccountService {

    private final GroupAccountRepository groupAccountRepository;
    private final AccountRepository accountRepository;
    private final TravelRepository travelRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final BankService bankService;
    private final AccountService accountService;

    @Override
    public List<GroupAccountInfoDto> getGroupAccountListByMemberId(Long memberId) {
        return groupAccountRepository.getGroupAccountListByMemberId(memberId).orElseThrow(GroupAccountNotFoundException::new);
    }

    @Transactional
    @Override
    public Long createGroupAccount(GroupAccountPostDto groupAccountPostDto) {
        Account account = accountRepository.findById(groupAccountPostDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
        Travel travel = travelRepository.findById(groupAccountPostDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        GroupAccount groupAccount = GroupAccount.builder()
                .account(account)
                .travel(travel)
                .build();
        groupAccountRepository.save(groupAccount);
        return groupAccount.getId();
    }

    @Override
    public GroupAccountInfoDto groupAccountInfo(Long groupAccountId) {

        GroupAccountInfoDto groupAccountInfoDto = groupAccountRepository.getGroupAccountInfo(groupAccountId).orElseThrow(GroupAccountNotFoundException::new);
        return groupAccountInfoDto;
    }

    @Override
    public Long updateGroupAccount(Long memberId, GroupAccountUpdateDto groupAccountUpdateDto) {
        GroupAccount groupAccount = groupAccountRepository.findById(groupAccountUpdateDto.getGroupAccountId()).orElseThrow(GroupAccountNotFoundException::new);
        if (!groupAccount.getAccount().getMember().getId().equals(memberId)) {
            throw new GroupAccountAccessDeniedException();
        }
        if (groupAccountUpdateDto.getTargetAmount() != 0) {
            groupAccount.setTargetAmount(groupAccountUpdateDto.getTargetAmount());
        }
        if (groupAccountUpdateDto.getTargetDate() != 0) {
            groupAccount.setTargetDate(groupAccountUpdateDto.getTargetDate());
        }
        if (groupAccountUpdateDto.getPerAmount() != 0) {
            groupAccount.setPerAmount(groupAccountUpdateDto.getPerAmount());
        }
        if (groupAccountUpdateDto.getPaymentDate() != 0) {
            groupAccount.setPaymentDate(groupAccountUpdateDto.getPaymentDate());
        }

        groupAccountRepository.save(groupAccount);
        return groupAccount.getId();
    }
}
