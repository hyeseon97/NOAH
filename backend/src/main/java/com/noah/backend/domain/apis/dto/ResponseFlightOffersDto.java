package com.noah.backend.domain.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class ResponseFlightOffersDto {
    private String a_airport;
    private String d_airport;
    private String a_time;
    private String d_time;
    private Double price;
    private String airLine;
    private String code;
}
