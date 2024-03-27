package com.noah.backend.domain.exchange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.exchange.dto.requestDto.ExchangeReqDto;
import com.noah.backend.domain.exchange.dto.responseDto.ExchangeInfoDto;
import com.noah.backend.domain.exchange.service.ExchangeService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Exchange 컨트롤러", description = "Exchange Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

    private final ApiResponse response;
    private final ExchangeService exchangeService;

    @Operation(summary = "환전", description = "모임통장의 계좌 출금, 환전 생성")
    @PostMapping
    public ResponseEntity<?> exchangeGroupAccount(@RequestBody ExchangeReqDto exchangeReqDto) throws IOException {
        return response.success(ResponseCode.EXCHANGE_SUCCESS, exchangeService.createExchange(exchangeReqDto));
    }

    @Operation(summary = "환전금액 조회", description = "환전금액 조회")
    @GetMapping("/{travel_id}")
    public ResponseEntity<?> getGroupAccountExchange(@PathVariable(name = "travel_id") Long travelId) {
        ExchangeInfoDto result = exchangeService.getExchangeInfo(travelId);
        if (result == null) {
            return response.success(ResponseCode.EXCHANGE_NOT_FOUND, null);
        }
        return response.success(ResponseCode.EXCHANGE_INFO_FETCHED, result);
    }

}
