package com.noah.backend.domain.plan.service;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.entity.Plan;

import java.util.List;

public interface PlanService {

    List<PlanListGetFromTravelDto> getPlanList(Long travelId);

    PlanGetDto getPlanSelect(Long PlanId);

    Long createPlan(Long travelId, Plan plan);

    Long updatePlan(Long PlanId, Plan plan);

    void deletePlan(Long PlanId);
}
