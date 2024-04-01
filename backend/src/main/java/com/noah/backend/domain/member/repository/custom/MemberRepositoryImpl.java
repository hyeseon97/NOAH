package com.noah.backend.domain.member.repository.custom;

import static com.noah.backend.domain.account.entity.QAccount.account;
import static com.noah.backend.domain.groupaccount.entity.QGroupAccount.groupAccount;
import static com.noah.backend.domain.member.entity.QMember.member;
import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;
import static com.noah.backend.domain.travel.entity.QTravel.travel;

import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import com.noah.backend.domain.member.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(query.selectFrom(member)
                                        .where(member.email.eq(email))
                                        .fetchFirst());
    }

    @Override
    public boolean isNicknameDuplicate(String nickname) {
        return query
            .selectFrom(member)
            .where(member.nickname.eq(nickname))
            .fetchCount() > 0;
    }

    @Override
    public Optional<List<Long>> findByTravelId(Long travelId) {
        return Optional.ofNullable(query.select(member.id)
                                       .from(member)
                                       .leftJoin(memberTravel).on(memberTravel.member.id.eq(member.id))
                                       .where(memberTravel.travel.id.eq(travelId))
                                       .fetch());
    }

    @Override
    public Optional<Member> findByNameAndAccountId(String name, Long accountId) {
        return Optional.ofNullable(query.select(member)
                                       .from(member)
                                       .leftJoin(memberTravel).on(memberTravel.member.id.eq(member.id))
                                       .leftJoin(travel).on(memberTravel.travel.id.eq(travel.id))
                                       .leftJoin(groupAccount).on(groupAccount.travel.id.eq(travel.id))
                                       .leftJoin(account).on(groupAccount.account.id.eq(account.id))
                                       .where(member.name.eq(name).and(account.id.eq(accountId)))
                                       .fetchOne());
    }

}
