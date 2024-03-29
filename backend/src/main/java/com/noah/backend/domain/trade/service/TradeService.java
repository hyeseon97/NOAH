package com.noah.backend.domain.trade.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.trade.dto.requestDto.TradeUpdateClassifyReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradePostReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;

import java.io.IOException;
import java.util.List;

public interface TradeService {
    void createTrade(TradePostReqDto tradePostReqDto);

    List<TradeGetResDto> getTradeList(Long travelId) throws IOException;

    List<TradeGetResDto> getTradeListByMemberAndConsumeType(Long travelId, List<Long> memberIds, List<String> consumeTypes);

    Long updateTradeClassify(Long tradeId, TradeUpdateClassifyReqDto tradeUpdateClassifyReqDto);

    Long updateTradeContain(Long tradeId);

    List<TradeGetResDto> getHideTradeList(Long travelId);
}
