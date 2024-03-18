package com.noah.backend.domain.plan.repository.custom;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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
                        plan.id,
                        plan.startDate,
                        plan.endDate,
                        plan.travelStart,
                        plan.country,
                        plan.travel,
                        plan.detailPlan
                )).from(plan)
                .leftJoin(travel)
                .where(plan.travel.id.eq(travelId)).fetch();
        return Optional.ofNullable(planDtos.isEmpty() ? null : planDtos);
    }

    @Override
    public Optional<PlanGetDto> getPlanSelect(Long PlanId) {
        PlanGetDto planDto = query
                .select(Projections.constructor(PlanGetDto.class,
                        plan.id,
                        plan.startDate,
                        plan.endDate,
                        plan.travelStart,
                        plan.country,
                        plan.travel,
                        plan.detailPlan))
                .from(plan)
                .where(plan.id.eq(PlanId))
                .fetchOne();
        return Optional.ofNullable(planDto);
    }
}
