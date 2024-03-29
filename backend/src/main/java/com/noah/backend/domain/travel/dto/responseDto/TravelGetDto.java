package com.noah.backend.domain.travel.dto.responseDto;

import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelGetDto {

    private Long travelId;
    private String title;
    private boolean isEnded;

    private Long groupAccountId;
    private int targetAmount;

    private Long accountId;

    private Long planId;
    private List<SimplePlan> simplePlanList;

    public TravelGetDto(Long travelId, String title, boolean isEnded, Long groupAccountId, int targetAmount,
                        Long accountId, Long planId) {
        this.travelId = travelId;
        this.title = title;
        this.isEnded = isEnded;
        this.groupAccountId = groupAccountId;
        this.targetAmount = targetAmount;
        this.accountId = accountId;
        this.planId = planId;
    }
}
