package com.noah.backend.domain.apis.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FlightPriceDto {
    // @ 출발지 IATA 코드
    private String originLocationCode;
    // @ 도착지 IATA 코드
    private String destinationLocationCode;
    // @ 출발일 YYYY-MM-DD
    private LocalDate departureDate;
    // 통화 = EUR(유로, 기본), CAD,HKD,ISK,PHP,DKK,HUF,CZK,AUD,RON,SEK,IDR,INR,BRL,RUB,HRK,JPY,THB,CHF,SGD,PLN,BGN,TRY,CNY,NOK,NZD,ZAR,USD,MXN,ILS,GBP,KRW,MYR
    private String currencyCode;
    // true = 편도, false = 왕복(기본)
    private Boolean oneWay;
}
