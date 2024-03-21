package com.noah.backend.domain.account.repository.custom;

import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.account.entity.QAccount.account;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<AccountInfoDto>> getMyAccountByMemberId(Long memberId) {
        return Optional.ofNullable(query.select(Projections.constructor(AccountInfoDto.class,
                        account.id, account.bankName, account.accountNumber, account.type, account.amount))
                .from(account)
                .where(account.member.id.eq(memberId))
                .fetch());
    }
}
