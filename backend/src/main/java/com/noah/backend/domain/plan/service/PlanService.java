package com.noah.backend.domain.plan.service;

import com.noah.backend.domain.plan.entity.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> getPlanList(Long travelId);

    Plan getPlanSelect(Long PlanId);

    Long createPlan(Long travelId, Plan plan);

    Long updatePlan(Long PlanId, Plan plan);

    void deletePlan(Long PlanId);
}
