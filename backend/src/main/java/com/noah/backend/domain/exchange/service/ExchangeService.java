package com.noah.backend.domain.exchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeInfoDto;

public interface ExchangeService {

    Long createExchange(ExchangeReqDto exchangeReqDto) throws JsonProcessingException;

    ExchangeInfoDto getExchangeInfo(Long travelId);
}
