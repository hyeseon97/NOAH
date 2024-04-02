package com.noah.backend.domain.datailPlan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

//import static com.noah.backend.domain.datailPlan.entity.QDetailPlan.detailPlan;
//import static com.noah.backend.domain.plan.entity.QPlan.plan;
import static com.noah.backend.domain.datailPlan.entity.QDetailPlan.detailPlan;
import static com.noah.backend.domain.image.entity.QImage.image;
import static com.noah.backend.domain.plan.entity.QPlan.plan;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class DetailPlanRepositoryImpl implements DetailPlanRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<DetailPlanDto>> getDetailPlanList(Long PlanId) {
        List<DetailPlanDto> detailPlanDtos = query
            .select(constructor(DetailPlanDto.class,
                                detailPlan.id,
                                detailPlan.day,
                                detailPlan.sequence,
                                detailPlan.place,
                                detailPlan.memo,
                                detailPlan.time,
                                image.url
            ))
            .from(detailPlan)
            .leftJoin(image).on(image.detailPlan.id.eq(detailPlan.id))
            .where(detailPlan.plan.id.eq(PlanId))
            .orderBy(detailPlan.day.asc(), detailPlan.sequence.asc())
            .fetch();
        return Optional.ofNullable(detailPlanDtos);
    }

}
