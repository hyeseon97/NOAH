package com.noah.backend.domain.exchange.dto.responseDto;

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
public class ExchangeRateGetDto {

    private Double USD;
    private Double JPY;
    private Double CNY;
    private Double EUR;

}
