package com.noah.backend.domain.apis.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlightOffersDto {
    // @ 출발지 IATA 코드
    private String originLocationCode;
    // @ 도착지 IATA 코드
    private String destinationLocationCode;
    // @ 출발일 YYYY-MM-DD (과거 안됨)
    private LocalDate departureDate;
    // 도착일 YYYY-MM-DD (없으면 편도)
    private LocalDate returnDate;
    // @ 성인 수 (출발일 기준 12세 이상, 어린이 포함 9이하)
    private Integer adults;
    // 어린이 수 (자체 좌석, 2세 이상 ~ 12세 미만, 0이상)
//    private Integer children;
//    // 유아 수 (2세 이하)
//    private Integer infants;
//    // ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST
//    private String travelClass;
//    // 포함 항공사 IATA 코드 (A,B,C 형태로 여러개 가능)
//    private List<String> includedAirlineCodes;
//    // 제외 항공사 IATA 코드 (A,B,C 형태로 여러개 가능)
//    private List<String> excludedAirlineCodes;
//    // 경유 (기본: false(허용))
//    private Boolean nonStop;
//    // 표시할 통화 (기본: EUR(유로화))
//    private String currencyCode;
//    // 최대 금액 (기본: 무제한)
//    private Integer maxPrice;
//    // 표시할 결과 수 (기본: 250)
//    private Integer max;
}
