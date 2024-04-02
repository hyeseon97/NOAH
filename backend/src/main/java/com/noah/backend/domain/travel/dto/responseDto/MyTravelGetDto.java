package com.noah.backend.domain.travel.dto.responseDto;

import java.util.Date;
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
public class MyTravelGetDto {

    private Long travelId;
    private String travelTitle;
    private int people;

    private Long groupAccountId;

    private Long planId;
    private String country;
    private Date startDate;
    private Date endDate;

    private Long reviewId;

}
