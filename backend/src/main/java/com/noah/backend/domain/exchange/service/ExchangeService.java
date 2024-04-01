package com.noah.backend.domain.exchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeRatePutDto;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeInfoDto;

import com.noah.backend.domain.exchange.dto.responseDto.ExchangeRateGetDto;
import java.io.IOException;

public interface ExchangeService {

    Long createExchange(String email, ExchangeReqDto exchangeReqDto) throws IOException;

    ExchangeInfoDto getExchangeInfo(String email, Long travelId);

    ExchangeRateGetDto getExchangeRate();

    Long updateTargetExchangeRate(String email, ExchangeRatePutDto exchangeRatePutDto);
}
