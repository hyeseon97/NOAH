package com.noah.backend.domain.trade.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.trade.dto.requestDto.TradeUpdateClassifyReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.requestDto.TradePostReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;
import com.noah.backend.domain.trade.service.TradeService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Trade 컨트롤러", description = "Trade Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class TradeController {

    private final ApiResponse response;
    private final TradeService tradeService;

    @Operation(summary = "거래내역 생성", description = "accountId:공동통장계좌아이디 / name:거래내역내용 / cost:금액 / tradeType:입금(1), 출금(2)")
    @PostMapping
    public ResponseEntity<?> createTrade(@RequestBody TradePostReqDto tradePostReqDto)
        throws IOException {
        tradeService.createTrade(tradePostReqDto);
        return response.success(ResponseCode.TRADE_CREATED);
    }

    @Operation(summary = "거래내역 전체 조회", description = "전체 거래 내역 조회")
    @GetMapping("/{travelId}")
    public ResponseEntity<?> getTradeList(@Parameter(hidden = true) Authentication authentication, @PathVariable(name = "travelId") Long travelId) throws IOException {

        List<TradeGetResDto> result = tradeService.getTradeList(authentication.getName(), travelId);
        if (result.isEmpty()) {
            return response.success(ResponseCode.TRADE_LIST_NOT_FOUND, null);
        }
        return response.success(ResponseCode.TRADE_INFO_FETCHED, result);
    }


    @Operation(summary = "거래내역 분류 조회", description = "거래내역 분류 조회, member, consumeType으로 조회, 여러개 해야되서 List로")
    @GetMapping("/classify/{travelId}")
    public ResponseEntity<?> getTradeListByMemberAndConsumeType(@Parameter(hidden = true) Authentication authentication,
                                                                @PathVariable(name = "travelId") Long travelId,
                                                                @RequestParam(required = false) List<Long> memberIds,
                                                                @RequestParam(required = false) List<String> consumeTypes) {
        List<TradeGetResDto> result = tradeService.getTradeListByMemberAndConsumeType(authentication.getName(), travelId, memberIds, consumeTypes);
        if (result.isEmpty()) {
            return response.success(ResponseCode.TRADE_LIST_NOT_FOUND, null);
        }
        return response.success(ResponseCode.TRADE_INFO_FETCHED, result);
    }

    @Operation(summary = "숨김된 거래내역 조회", description = "숨김 된 거래내역 조회")
    @GetMapping("/hide/{travelId}")
    public ResponseEntity<?> getHideTradeList(@Parameter(hidden = true) Authentication authentication,
                                              @PathVariable(name = "travelId") Long travelId) {
        List<TradeGetResDto> result = tradeService.getHideTradeList(authentication.getName(), travelId);
        if (result.isEmpty()) {
            return response.success(ResponseCode.TRADE_LIST_NOT_FOUND, null);
        }
        return response.success(ResponseCode.TRADE_INFO_FETCHED, result);
    }

    @Operation(summary = "거래내역 수정 분류용", description = "거래내역 수정, 사용자 설정, 소비 분류 설정")
    @PutMapping()
    public ResponseEntity<?> updateTradeClassify(@Parameter(hidden = true) Authentication authentication,
                                                 @RequestBody TradeUpdateClassifyReqDto tradeClassifyUpdateReqDto) {
        return response.success(ResponseCode.TRADE_UPDATED, tradeService.updateTradeClassify(authentication.getName(), tradeClassifyUpdateReqDto));
    }

    @Operation(summary = "거래내역 수정 삭제용", description = "거래내역 포함 여부, true이면 false, false 이면 true로")
    @PutMapping("/remove/{tradeId}")
    public ResponseEntity<?> updateTradeContain(@Parameter(hidden = true) Authentication authentication,
                                                @PathVariable(name = "tradeId") Long tradeId) {
        return response.success(ResponseCode.TRADE_UPDATED, tradeService.updateTradeContain(authentication.getName(), tradeId));
    }

    // 거래내역 시점 조회 시 사용
//    @Operation(summary = "거래내역 조회", description = "시점에 맞는 거래 내역 조회")
//    @GetMapping("/{travel_id}")
//    public ResponseEntity<?> getTradeList(
//            @PathVariable(name = "travel_id") Long travelId,
//            @RequestParam(name = "조회시작") String startDate,
//            @RequestParam(name = "조회끝") String endDate,
//            @RequestParam(name = "거래 유형") String transactionType,
//            @RequestParam(name = "정렬") String orderByType
//    ) throws JsonProcessingException {
//        TradeGetReqDto tradeGetReqDto = TradeGetReqDto.builder()
//                .travelId(travelId)
//                .startDate(startDate)
//                .endDate(endDate)
//                .transactionType(transactionType)
//                .orderByType(orderByType)
//                .build();
//        List<TradeGetResDto> result = tradeService.getTradeList(tradeGetReqDto);
//        if (result.isEmpty()) {
//            return response.success(ResponseCode.TRADE_LIST_NOT_FOUND, null);
//        }
//        return response.success(ResponseCode.TRADE_INFO_FETCHED, result);
//    }
}
