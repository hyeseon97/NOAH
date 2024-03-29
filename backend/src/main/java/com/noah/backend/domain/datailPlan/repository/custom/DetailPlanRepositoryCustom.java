package com.noah.backend.domain.datailPlan.repository.custom;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;

import java.util.List;
import java.util.Optional;

public interface DetailPlanRepositoryCustom {

    Optional<List<DetailPlanDto>> getDetailPlanList(Long PlanId);

}
