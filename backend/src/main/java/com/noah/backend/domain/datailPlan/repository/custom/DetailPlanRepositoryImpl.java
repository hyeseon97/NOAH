package com.noah.backend.domain.datailPlan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DetailPlanRepositoryImpl implements DetailPlanRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Optional<List<DetailPlanListGetFromPlanDto>> getDetailPlanList(Long PlanId) {
        return Optional.empty();
    }

    @Override
    public Optional<DetailPlanGetDto> getDetailPlanSelect(Long DetailPlanId) {
        return Optional.empty();
    }
}
