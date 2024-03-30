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

    private Long planId;
    private Date startDate;
    private Date endDate;
    private String country;

}
