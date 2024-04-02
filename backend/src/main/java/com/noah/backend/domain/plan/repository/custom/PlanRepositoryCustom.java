package com.noah.backend.domain.plan.repository.custom;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;

import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import com.noah.backend.domain.plan.entity.Plan;
import java.util.List;
import java.util.Optional;

public interface PlanRepositoryCustom {
    Optional<List<PlanListGetFromTravelDto>> getPlanList(Long travelId);

    Optional<PlanGetDto> getPlanSelect(Long travelId);

    Optional<List<SimplePlan>> getSimplePlan(Long planId);

    void isAccessPlan(Long memberId, Long planId);

    Optional<Plan> findByTravelId(Long travelId);
}
