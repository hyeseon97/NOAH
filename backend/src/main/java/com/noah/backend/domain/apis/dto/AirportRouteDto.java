package com.noah.backend.domain.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AirportRouteDto {
    // @ 출발 공항 IATA
    private String departureAirportCode;
    // 결과 최대 개수
    private Integer max;
    // 도착 국가 IATA
    private String arrivalCountryCode;
}
