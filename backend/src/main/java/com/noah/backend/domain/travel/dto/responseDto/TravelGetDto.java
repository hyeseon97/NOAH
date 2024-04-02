package com.noah.backend.domain.travel.dto.responseDto;

import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import java.util.Date;
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

    private Long groupAccountId;
    private int targetAmount;

    private Long accountId;
    private int accountAmount;
    private int depositTotal;

    private Long planId;
    private Date startDate;
    private Date endDate;
    private String country;

    public TravelGetDto(Long travelId, String title, Long groupAccountId, int targetAmount, Long accountId, int accountAmount,
                        Long planId, Date startDate, Date endDate, String country) {
        this.travelId = travelId;
        this.title = title;
        this.groupAccountId = groupAccountId;
        this.targetAmount = targetAmount;
        this.accountId = accountId;
        this.accountAmount = accountAmount;
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
    }
}
