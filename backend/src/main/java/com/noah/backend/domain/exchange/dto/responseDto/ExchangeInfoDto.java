package com.noah.backend.domain.exchange.dto.responseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExchangeInfoDto {
    String currency;
    int exchangeAmount;
}
