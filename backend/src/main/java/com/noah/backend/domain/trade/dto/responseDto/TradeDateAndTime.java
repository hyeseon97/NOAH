package com.noah.backend.domain.trade.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TradeDateAndTime {
    private String date;
    private String time;
}
