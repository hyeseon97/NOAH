package com.noah.backend.domain.review.dto.requestDto;

import lombok.*;

import java.util.Date;

//@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPostDto {

    private Long review_id;
    @Setter
    private Integer expense;
    @Setter
    private String country;
    @Setter
    private Integer people;
    @Setter
    private Date start_date;
    @Setter
    private Date end_date;

}
