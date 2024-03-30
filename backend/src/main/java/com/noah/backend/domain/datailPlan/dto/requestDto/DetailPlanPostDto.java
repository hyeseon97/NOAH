package com.noah.backend.domain.datailPlan.dto.requestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPlanPostDto {

    private Long planId;
    private int day;
    private int sequence;
    private String place;
    private double pinX;
    private double pinY;
    private String memo;
    private String time;

}
