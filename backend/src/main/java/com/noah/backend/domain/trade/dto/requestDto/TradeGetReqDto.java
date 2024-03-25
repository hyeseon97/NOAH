package com.noah.backend.domain.trade.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeGetReqDto {
    private Long travelId;
    private String startDate;
    private String endDate;

}
