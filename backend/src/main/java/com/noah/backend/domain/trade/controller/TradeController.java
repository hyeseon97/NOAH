package com.noah.backend.domain.trade.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.trade.dto.requestDto.TradeUpdateClassifyReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradePostReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;
import com.noah.backend.domain.trade.repository.TradeRepository;
import com.noah.backend.domain.trade.service.TradeService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            @RequestParam(name = "여행ID") Long travelId,
            @RequestParam(name = "조회시작") String startDate,
            @RequestParam(name = "조회끝") String endDate,
            @RequestParam(name = "거래 유형") String transactionType,
            @RequestParam(name = "정렬") String orderByType
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

    @Operation(summary = "거래내역 수정 분류용", description = "거래내역 수정, 사용자 설정, 소비 분류 설정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTradeClassify(@PathVariable(name = "id") Long tradeId,
                                                 @RequestBody TradeUpdateClassifyReqDto tradeClassifyUpdateReqDto) {
        return response.success(ResponseCode.TRADE_UPDATED, tradeService.updateTradeClassify(tradeId, tradeClassifyUpdateReqDto));
    }

    @Operation(summary = "거래내역 수정 삭제용", description = "거래내역 포함 여부, true이면 false, false 이면 true로")
    @PutMapping("/remove/{id}")
    public ResponseEntity<?> updateTradeContain(@PathVariable(name = "id") Long tradeId) {
        return response.success(ResponseCode.TRADE_UPDATED, tradeService.updateTradeContain(tradeId));
    }
}
