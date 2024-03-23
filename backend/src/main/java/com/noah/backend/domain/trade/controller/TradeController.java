package com.noah.backend.domain.trade.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradePostReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;
import com.noah.backend.domain.trade.service.TradeService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Trade 컨트롤러", description = "Trade Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class TradeController {

    private final ApiResponse response;
    private final TradeService tradeService;

    @Operation(summary = "거래내역 생성", description = "거래내역 생성 실제로는 사용안할듯")
    @PostMapping
    public ResponseEntity<?> createTrade(@RequestBody TradePostReqDto tradePostReqDto) {
        tradeService.createTrade(tradePostReqDto);
        return response.success(ResponseCode.TRADE_CREATED);
    }

    @Operation(summary = "거래내역 조회", description = "시점에 맞는 거래 내역 조회")
    @GetMapping
    public ResponseEntity<?> getTradeList(
            @RequestParam Long travelId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String transactionType,
            @RequestParam String orderByType
    ) throws JsonProcessingException {
        TradeGetReqDto tradeGetReqDto = TradeGetReqDto.builder()
                .travelId(travelId)
                .startDate(startDate)
                .endDate(endDate)
                .transactionType(transactionType)
                .orderByType(orderByType)
                .build();
        List<TradeGetResDto> result = tradeService.getTradeList(tradeGetReqDto);
        if (result.isEmpty()) {
            return response.success(ResponseCode.TRADE_LIST_NOT_FOUND, null);
        }
        return response.success(ResponseCode.TRADE_INFO_FETCHED, result);
    }
}
