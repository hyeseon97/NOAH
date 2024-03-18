package com.noah.backend.domain.plan.repository.custom;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Optional<List<PlanListGetFromTravelDto>> getPlanList(Long travelId) {
        return Optional.empty();
    }

    @Override
    public Optional<PlanGetDto> getPlanSelect(Long PlanId) {
        return Optional.empty();
    }
}
