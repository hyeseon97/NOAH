package com.noah.backend.domain.groupaccount.service.impl;

import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.AmountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.groupaccount.repository.GroupAccountRepository;
import com.noah.backend.domain.groupaccount.service.GroupAccountService;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.noah.backend.global.exception.account.AccountNotFoundException;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class GroupGroupAccountServiceImpl implements GroupAccountService {

    private final GroupAccountRepository accountRepository;
    private final TravelRepository travelRepository;
//    private final MemberRepository memberRepository;

    @Override
    public List<GroupAccount> getMyAccountList(Long memberId) {
        return null;
    }

    @Transactional
    @Override
    public GroupAccountInfoDto getAccountInfo(Long accountId) {
        GroupAccount groupAccount = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
//        String ownerName = memberRepository.findById(account.getOwnerId()).getName();
        GroupAccountInfoDto accountInfo = GroupAccountInfoDto.builder()
                .id(accountId)
                .name(groupAccount.getName())
                .bank(groupAccount.getBank())
                .accountNumber(groupAccount.getAccountNumber())
                .amount(groupAccount.getAmount())
                .usedAmount(groupAccount.getUsedAmount())
                .targetAmount(groupAccount.getTargetAmount())
                .perAmount(groupAccount.getPerAmount())
                .paymentDate(groupAccount.getPaymentDate())
                .build();
        return accountInfo;
    }

    @Transactional
    @Override
    public Long createAccount(GroupAccountPostDto groupAccountPostDto) {

//        Member member = memberRepository.searchById(memberId)
        Travel travel = travelRepository.findById(groupAccountPostDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        Long owner = groupAccountPostDto.getOwnerId();
        String bank = groupAccountPostDto.getBank();
        String accountNumber = groupAccountPostDto.getAccountNumber();

        GroupAccount groupAccount = GroupAccount.builder()
                .ownerId(owner)
                .bank(bank)
                .accountNumber(accountNumber)
                .build();

        GroupAccount savedGroupAccount = accountRepository.save(groupAccount);

        return savedGroupAccount.getId();
    }

    @Override
    public Long updateAmount(AmountUpdateDto amountUpdateDto) {
        GroupAccount groupAccount = accountRepository.findById(amountUpdateDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
        int amount = amountUpdateDto.getAmount();
        groupAccount.setAmount(amount);
        accountRepository.save(groupAccount);
        return groupAccount.getId();
    }


    @Override
    public Long updateAccount(GroupAccountUpdateDto groupAccountUpdateDto) {
        GroupAccount groupAccount = accountRepository.findById(groupAccountUpdateDto.getId()).orElseThrow(AccountNotFoundException::new);
        String updateName = groupAccountUpdateDto.getName();
        int updateAmount = groupAccountUpdateDto.getAmount();
        int updateUsedAmount = groupAccountUpdateDto.getUsedAmount();
        int targetAmount = groupAccountUpdateDto.getTargetAmount();
        int perAmount = groupAccountUpdateDto.getPerAmount();
        int paymentDate = groupAccountUpdateDto.getPaymentDate();

        if (updateName != null) {
            groupAccount.setName(updateName);
        }
        if (updateAmount != 0) {
            groupAccount.setAmount(updateAmount);
        }
        if (updateUsedAmount != 0) {
            groupAccount.setUsedAmount(updateUsedAmount);
        }
        if (targetAmount != 0) {
            groupAccount.setTargetAmount(targetAmount);
        }
        if (perAmount != 0) {
            groupAccount.setPerAmount(perAmount);
        }
        if (paymentDate != 0) {
            groupAccount.setPaymentDate(paymentDate);
        }
        accountRepository.save(groupAccount);
        return groupAccount.getId();
    }

    @Override
    public void deleteAccount(Long accountId) {
        GroupAccount groupAccount = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        accountRepository.deleteById(accountId);
    }
}
