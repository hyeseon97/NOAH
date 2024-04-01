package com.noah.backend.domain.trade.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.trade.dto.requestDto.TradeUpdateClassifyReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradePostReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;

import java.io.IOException;
import java.util.List;

public interface TradeService {
    void createTrade(TradePostReqDto tradePostReqDto) throws IOException;

    List<TradeGetResDto> getTradeList(String email, Long travelId) throws IOException;

    List<TradeGetResDto> getTradeListByMemberAndConsumeType(String email, Long travelId, List<Long> memberIds, List<String> consumeTypes);

    Long updateTradeClassify(String email, TradeUpdateClassifyReqDto tradeUpdateClassifyReqDto);

    Long updateTradeContain(String email, Long tradeId);

    List<TradeGetResDto> getHideTradeList(String email, Long travelId);
}
