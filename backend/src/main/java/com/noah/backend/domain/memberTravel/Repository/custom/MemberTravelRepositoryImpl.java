package com.noah.backend.domain.memberTravel.Repository.custom;

import com.noah.backend.domain.memberTravel.dto.Response.GetTravelListResDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelGetDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;

@RequiredArgsConstructor
public class MemberTravelRepositoryImpl implements MemberTravelRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<MemberTravelListGetDto>> findByTravelId(Long travelId) {

        List<MemberTravelListGetDto> travelListGetDtos = query
                .select(Projections.constructor(MemberTravelListGetDto.class,
                        memberTravel.payment_amount,
                        memberTravel.member.id,
                        memberTravel.member.nickname))
                .from(memberTravel)
                .where(memberTravel.travel.id.eq(travelId))
                .fetch();

        return Optional.ofNullable(travelListGetDtos.isEmpty() ? null : travelListGetDtos);

    }


    @Override
    public Optional<MemberTravelGetDto> getSelect(Long memberTravelId) {

        MemberTravelGetDto travelGetDto = query
                .select(Projections.constructor(MemberTravelGetDto.class,
                        memberTravel.member,
                        memberTravel.travel))
                .from(memberTravel)
                .fetchOne();

        return Optional.ofNullable(travelGetDto);
    }

    @Override
    public Optional<Long> getMemberTravelByTravelIdAndMemberId(Long travelId, Long memberId) {
        Long memberTravelId = query.select(memberTravel.id)
                .from(memberTravel)
                .where(memberTravel.member.id.eq(memberId).and(memberTravel.travel.id.eq(travelId)))
                .fetchOne();
        return Optional.ofNullable(memberTravelId);
    }
}
