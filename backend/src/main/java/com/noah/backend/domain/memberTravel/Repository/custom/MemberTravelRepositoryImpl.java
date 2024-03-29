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

import static com.noah.backend.domain.groupaccount.entity.QGroupAccount.groupAccount;
import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;
import static com.noah.backend.domain.travel.entity.QTravel.travel;

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

    @Override
    public Optional<List<MemberTravel>> getAutoTransfer(int todayDate) {
        return Optional.ofNullable(query.select(memberTravel)
                                       .from(memberTravel)
                                       .leftJoin(travel).on(memberTravel.travel.id.eq(travel.id))
                                       .leftJoin(groupAccount).on(groupAccount.travel.id.eq(travel.id))
                                       .where(memberTravel.autoTransfer.eq(true).and(groupAccount.paymentDate.eq(todayDate).and(memberTravel.isDeleted.eq(false))))
                                       .fetch());
    }

    @Override
    public Optional<Integer> totalPeople(Long travelId) {
        return Optional.ofNullable(query.select(memberTravel.count().intValue())
                .from(memberTravel)
                .where(memberTravel.travel.id.eq(travelId))
                .fetchOne());
    }

    @Override
    public Optional<MemberTravel> findByTravelIdAndMemberId(Long memberId, Long travelId) {
        return Optional.ofNullable(query.select(memberTravel)
                                       .from(memberTravel)
                                       .where(memberTravel.member.id.eq(memberId).and(memberTravel.travel.id.eq(travelId).and(memberTravel.isDeleted.eq(false))))
                                       .fetchOne());
    }


}
