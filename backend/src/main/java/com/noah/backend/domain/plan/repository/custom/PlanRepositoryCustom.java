package com.noah.backend.domain.plan.repository.custom;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;

import java.util.List;
import java.util.Optional;

public interface PlanRepositoryCustom {
    Optional<List<PlanListGetFromTravelDto>> getPlanList(Long travelId);

    Optional<PlanGetDto> getPlanSelect(Long travelId);

}
