package com.noah.backend.domain.plan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.datailPlan.entity.QDetailPlan.detailPlan;
import static com.noah.backend.domain.plan.entity.QPlan.plan;
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
        return Optional.ofNullable(query.select(Projections.constructor(SimplePlan.class, detailPlan.day, detailPlan.place))
                                       .from(plan)
                                       .leftJoin(detailPlan).on(detailPlan.plan.id.eq(planId))
                                       .where(plan.id.eq(planId).and(detailPlan.sequence.eq(1)))
                                       .orderBy(detailPlan.day.asc())
                                       .fetch());
    }
}
