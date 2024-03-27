package com.noah.backend.domain.exchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;

public interface ExchangeService {

    Long createExchange(ExchangeReqDto exchangeReqDto) throws JsonProcessingException;


}
