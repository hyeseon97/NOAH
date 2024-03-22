package com.noah.backend.domain.plan.dto.responseDto;

import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanGetDto {

//    private Long plan_id;
    private Date start_date;
    private Date end_date;
    
    //얘 왜 자꾸 바꾸라는거냐 대체
    private Boolean travel_start;
    @Setter
    private String country;
    // 테스트 용이니까 후에 travelDto로 수정하자
//    @Setter
//    private Long travel_id;
    @Setter
    private List<DetailPlanListGetFromPlanDto> detailPlanList;

    public PlanGetDto(Date start_date, Date end_date, boolean travel_start, String country) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.travel_start = travel_start;
        this.country = country;

    }
}
