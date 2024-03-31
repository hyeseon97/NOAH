package com.noah.backend.domain.plan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import com.noah.backend.global.exception.plan.PlanAccessException;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.datailPlan.entity.QDetailPlan.detailPlan;
import static com.noah.backend.domain.image.entity.QImage.image;
import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;
import static com.noah.backend.domain.plan.entity.QPlan.plan;
import static com.noah.backend.domain.travel.entity.QTravel.travel;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<PlanListGetFromTravelDto>> getPlanList(Long travelId) {
        List<PlanListGetFromTravelDto> planDtos = query
            .select(constructor(PlanListGetFromTravelDto.class,
                                plan.startDate,
                                plan.endDate,
                                plan.travelStart,
                                plan.country
//                        plan.travel.id
            )).from(plan)
//                .leftJoin(travel)
            .where(plan.travel.id.eq(travelId)).fetch();
        return Optional.ofNullable(planDtos.isEmpty() ? null : planDtos);
    }

    @Override
    public Optional<PlanGetDto> getPlanSelect(Long travelId) {
        PlanGetDto planDto = query
            .select(Projections.constructor(PlanGetDto.class,
                                            plan.id,
                                            plan.startDate,
                                            plan.endDate,
                                            plan.travelStart,
                                            plan.country
            ))
            .from(plan)
            .where(plan.travel.id.eq(travelId))
            .fetchOne();

        if (planDto != null) {
            List<DetailPlanDto> detailDtos = query
                .select(Projections.constructor(DetailPlanDto.class,
                                                detailPlan.plan.id,
                                                detailPlan.day,
                                                detailPlan.sequence,
                                                detailPlan.place,
                                                detailPlan.pinX,
                                                detailPlan.pinY,
                                                detailPlan.memo,
                                                detailPlan.time
                ))
                .from(detailPlan)
                .leftJoin(plan)
                .on(detailPlan.plan.id.eq(plan.id))
                .orderBy(detailPlan.day.asc(), detailPlan.sequence.asc())
                .fetch();
            planDto.setDetailPlanList(detailDtos);
        }
        return Optional.ofNullable(planDto);
    }

    @Override
    public Optional<List<SimplePlan>> getSimplePlan(Long planId) {
        return Optional.ofNullable(query.select(Projections.constructor(SimplePlan.class, detailPlan.day, detailPlan.sequence, detailPlan.place, image.id,
                                                                        image.url))
                                       .from(plan)
                                       .leftJoin(detailPlan).on(detailPlan.plan.id.eq(plan.id))
                                       .leftJoin(image).on(image.detailPlan.id.eq(detailPlan.id))
                                       .where(plan.id.eq(planId))
                                       .orderBy(detailPlan.day.asc(), detailPlan.sequence.asc())
                                       .fetch());
    }

    @Override
    public void isAccessPlan(Long memberId, Long planId) {
        Optional.ofNullable(query.select()
                                .from(plan)
                                .leftJoin(travel).on(plan.travel.id.eq(travel.id))
                                .leftJoin(memberTravel).on(memberTravel.travel.id.eq(travel.id))
                                .where(memberTravel.member.id.eq(memberId).and(plan.id.eq(planId)))
                                .fetch())
            .orElseThrow(PlanAccessException::new);
    }
}
