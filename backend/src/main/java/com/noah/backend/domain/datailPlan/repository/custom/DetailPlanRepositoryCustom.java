package com.noah.backend.domain.datailPlan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;

import java.util.List;
import java.util.Optional;

public interface DetailPlanRepositoryCustom {

    Optional<List<DetailPlanListGetFromPlanDto>> getDetailPlanList(Long PlanId);

    Optional<DetailPlanGetDto> getDetailPlanSelect(Long DetailPlanId);


}
