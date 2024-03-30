package com.noah.backend.domain.datailPlan.dto.responseDto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPlanListDto {

    private Long planId;
    List<DetailPlanDto> detailPlanList;

}
