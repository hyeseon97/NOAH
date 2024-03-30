package com.noah.backend.domain.member.repository.custom;

import static com.noah.backend.domain.member.entity.QMember.member;
import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;

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

}
