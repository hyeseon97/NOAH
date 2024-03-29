package com.noah.backend.domain.exchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeInfoDto;

import java.io.IOException;

public interface ExchangeService {

    Long createExchange(ExchangeReqDto exchangeReqDto) throws IOException;

    ExchangeInfoDto getExchangeInfo(Long travelId);
}
