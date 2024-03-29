package com.noah.backend.domain.plan.service;

import com.noah.backend.domain.plan.dto.requestDto.PlanPostDto;
import com.noah.backend.domain.plan.dto.requestDto.PlanUpdateDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.entity.Plan;

import java.util.List;

public interface PlanService {

    List<PlanListGetFromTravelDto> getPlanList(String email, Long travelId);

    PlanGetDto getPlanSelect(String email, Long PlanId);

    Long createPlan(PlanPostDto planDto);

    Long updatePlan(Long PlanId, PlanUpdateDto planDto);

    boolean changeStart(Long PlanId, PlanUpdateDto planDto);

    void deletePlan(Long PlanId);
}
