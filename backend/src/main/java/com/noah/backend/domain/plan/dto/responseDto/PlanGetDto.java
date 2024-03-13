package com.noah.backend.domain.plan.dto.responseDto;

import java.math.BigInteger;
import java.util.Date;

public class PlanGetDto {

    private Long plan_id;
    private Date start_date;
    private Date end_date;
    private boolean travel_start;
    private String country;
    private Long travel_id;
}
