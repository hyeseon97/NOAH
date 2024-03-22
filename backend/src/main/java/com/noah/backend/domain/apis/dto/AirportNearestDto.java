package com.noah.backend.domain.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AirportNearestDto {
    // @위도
    private Double latitude;
    // @경도
    private Double longitude;
    // 반경 0 ~ 500km (기본: 500)
    private Integer radius;
    // 한 페이지 최대 개수 (기본: 10)
    private Integer pageLimit;
    // 요청 페이지 시작 인덱스 (기본 : 0)
    private Integer pageOffset;
    // 정렬 기준 (기본: relevance, distance, analytics.flights.score, analytics.travelers.score)
    // 거리 및 교통 점수, 거리, 작년 공항 항공편 수, 작년 공항 여행자 수
    private String sort;
}
