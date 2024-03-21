package com.noah.backend.domain.datailPlan.dto.responseDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPlanListGetFromPlanDto {
    private int day;
    private int sequence;
    private String place;
    private double pinX;
    private double pinY;
    private String memo;
    private String time;
    private Long plan_id;
}
