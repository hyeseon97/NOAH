package com.noah.backend.domain.datailPlan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.datailPlan.entity.QDetailPlan.detailPlan;
import static com.noah.backend.domain.plan.entity.QPlan.plan;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class DetailPlanRepositoryImpl implements DetailPlanRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<DetailPlanListGetFromPlanDto>> getDetailPlanList(Long PlanId) {
        List<DetailPlanListGetFromPlanDto> detailPlanDtos = query
                .select(constructor(DetailPlanListGetFromPlanDto.class,
                        detailPlan.id,
                        detailPlan.day,
                        detailPlan.sequence,
                        detailPlan.place,
                        detailPlan.pinX,
                        detailPlan.pinY,
                        detailPlan.memo,
                        detailPlan.time,
                        detailPlan.plan))
                .from(detailPlan).leftJoin(plan).where(detailPlan.plan.id.eq(PlanId)).fetch();
        return Optional.ofNullable(detailPlanDtos);
    }

    @Override
    public Optional<DetailPlanGetDto> getDetailPlanSelect(Long DetailPlanId) {
        DetailPlanGetDto DetailPlanDto = query
                .select(Projections.constructor(DetailPlanGetDto.class,
                        detailPlan.id,
                        detailPlan.day,
                        detailPlan.sequence,
                        detailPlan.place,
                        detailPlan.pinX,
                        detailPlan.pinY,
                        detailPlan.memo,
                        detailPlan.time,
                        detailPlan.plan)
                ).from(detailPlan).where(detailPlan.id.eq(DetailPlanId)).fetchOne();

        return Optional.ofNullable(DetailPlanDto);
    }
}
