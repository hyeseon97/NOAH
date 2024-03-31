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
public class TargetExchangeRate {

    private Long travelId;
    private String travelTitle;
    private Long memberId;
    private int currency;
    private Double exchangeRate;

}
