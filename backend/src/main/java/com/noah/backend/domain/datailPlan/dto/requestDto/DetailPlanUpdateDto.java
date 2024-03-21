package com.noah.backend.domain.datailPlan.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPlanUpdateDto {
    private int day;
    private int sequence;
    private String place;
    private double pinX;
    private double pinY;
    private String memo;
    private String time;
    private String plan_id;
}
