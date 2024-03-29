package com.noah.backend.domain.plan.dto.responseDto;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanGetDto {

    private Long planId;
    private Date startDate;
    private Date endDate;
    private Boolean travelStart;
    private String country;

    @Setter
    private List<DetailPlanDto> detailPlanList;

    public PlanGetDto(Long planId, Date startDate, Date endDate, boolean travelStart, String country) {
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.travelStart = travelStart;
        this.country = country;

    }
}
