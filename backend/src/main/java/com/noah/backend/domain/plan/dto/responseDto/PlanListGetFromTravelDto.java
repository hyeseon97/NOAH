package com.noah.backend.domain.plan.dto.responseDto;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanListGetFromTravelDto {
    @Setter
    private Date start_date;
    @Setter
    private Date end_date;
    @Setter
    private boolean travel_start;
    @Setter
    private String country;
    // 테스트 용이니까 후에 travelDto로 수정하자
//    @Setter
//    private Long travel_id;
//    @Setter
//    private List<DetailPlanListGetFromPlanDto> detailList;
}
