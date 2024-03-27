package com.noah.backend.domain.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AirlineRouteDto {
    // @ 항공사 IATA 코드
    private String airlineCode;
    // 도착지 최대 수
    private Integer max;
    // 도착 국가 IATA 코드
    private String arrivalCountryCode;
}
