package com.noah.backend.domain.plan.dto.requestDto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanPostDto {

    private Long travelId;
    private Date startDate;
    private Date endDate;
    private boolean travelStart;
    private String country;

}
