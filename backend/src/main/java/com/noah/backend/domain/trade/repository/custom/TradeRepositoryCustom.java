package com.noah.backend.domain.trade.repository.custom;

import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeDateAndTime;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;

import java.util.List;
import java.util.Optional;

public interface TradeRepositoryCustom {

    Optional<List<TradeGetResDto>> getTradeList(Long accountId, TradeGetReqDto tradeGetReqDto);

    Optional<TradeDateAndTime> getTradeDateAndTime(String date, String time);
}
