package com.noah.backend.domain.suggest.controller;

import com.noah.backend.domain.review.controller.ReviewController;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.domain.suggest.dto.requestDto.SuggestListReqDto;
import com.noah.backend.domain.suggest.dto.responseDto.SuggestListResDto;
import com.noah.backend.domain.suggest.service.SuggestService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Suggest 컨트롤러", description = "Suggest Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Suggest")
public class SuggestController {
	private final SuggestService suggestService;
	private final ApiResponse response;

	private static final Logger log = LoggerFactory.getLogger(SuggestController.class);


	//여행 썸네일은 메소드만 만들었습니다.
	@Operation(summary = "여행 추천 목록 조회", description = "여행 추천 목록 조회")
	@GetMapping("/{travelId}")
	public ResponseEntity<?> getSuggestList(@PathVariable(name = "travelId") Long travelId) {
		System.out.println("테스트");
		List<SuggestListResDto> suggestList = suggestService.getSuggestList(travelId);
		return response.success(ResponseCode.REVIEW_FETCHED, suggestList);
	}
}
