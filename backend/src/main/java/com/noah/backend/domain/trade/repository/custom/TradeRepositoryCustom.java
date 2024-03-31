package com.noah.backend.domain.trade.repository.custom;

import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeDateAndTime;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;

import java.util.List;
import java.util.Optional;

public interface TradeRepositoryCustom {

    Optional<List<TradeGetResDto>> getTradeList(Long accountId);

    Optional<TradeDateAndTime> getTradeDateAndTime(String date, String time);

    Optional<List<TradeGetResDto>> getTradeListByMemberAndConsumeType(Long accountId, List<Long> memberIds, List<String> consumeTypes);

    Optional<List<TradeGetResDto>> getHideTradeList(Long accountId);

    void isAccessTrade(Long memberId, Long tradeId);

    Optional<Integer> getTotalExpense(Long travelId);
}
