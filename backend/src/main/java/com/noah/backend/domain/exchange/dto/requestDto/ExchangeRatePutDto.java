package com.noah.backend.domain.exchange.dto.requestDto;

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
public class ExchangeRatePutDto {

    private Long travelId;
    private String targetExchangeCurrency;
    private Double targetExchangeRate;

}
