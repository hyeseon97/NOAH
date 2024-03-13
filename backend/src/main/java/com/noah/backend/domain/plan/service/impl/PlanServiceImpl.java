package com.noah.backend.domain.plan.service.impl;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.plan.service.PlanService;

import java.util.List;

public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanListGetFromTravelDto> getPlanList(Long travelId) {
        return null;
    }

    @Override
    public PlanGetDto getPlanSelect(Long PlanId) {
        return null;
    }

    @Override
    public Long createPlan(Long travelId, Plan plan) {
        return null;
    }

    @Override
    public Long updatePlan(Long PlanId, Plan plan) {
        return null;
    }

    @Override
    public void deletePlan(Long PlanId) {

    }
}
