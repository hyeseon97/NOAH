package com.noah.backend.domain.plan.dto.requestDto;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanUpdateDto {
    private Long planId;
    private Date start_date;
    private Date end_date;
    private boolean travel_start;
    private String country;
    // 테스트 용이니까 후에 travelDto로 수정하자
//    private Long travel_id;
}
