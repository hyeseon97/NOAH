package com.noah.backend.domain.review.controller;

import com.noah.backend.domain.review.dto.requestDto.ReviewPostDto;
import com.noah.backend.domain.review.dto.requestDto.ReviewUpdateDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review 컨트롤러", description = "Review Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ApiResponse response;

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
//    private final ApiResponse apiResponse;

    @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<?> getReviewList() {
        List<ReviewListGetDto> reviewList = reviewService.getReviewList();
        return response.success(ResponseCode.REVIEW_FETCHED, reviewList);
    }

    @Operation(summary = "리뷰 선택 조회", description = "리뷰 선택 조회")
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewSelect(@PathVariable(value = "reviewId") Long reviewId) {
        ReviewGetDto review = reviewService.getReviewSelect(reviewId);
        return response.success(ResponseCode.REVIEW_FETCHED, review);
    }

    // 리뷰 생성
    @Operation(summary = "후기 등록", description = "후기 등록 버튼에 사용")
    @PostMapping
    public ResponseEntity<?> createReview(@Parameter(hidden = true) Authentication authentication, @RequestBody ReviewPostDto reviewCreateDto) {
        Long createdReviewId = reviewService.createReview(authentication.getName(), reviewCreateDto);
        return response.success(ResponseCode.REVIEW_CREATED, createdReviewId);
    }

//    @Operation(summary = "리뷰 생성", description = "리뷰 생성")
//    @PostMapping
//    public ResponseEntity<?> createReview(@RequestBody ReviewPostDto reviewCreateDto, @RequestParam(value = "travelId") Long travelId) {
//        Long createdReviewId = reviewService.createReviewTest2(reviewCreateDto, travelId);
//        return response.success(ResponseCode.REVIEW_CREATED, createdReviewId);
//    }

    // 리뷰 수정
    @Operation(summary = "리뷰 수정", description = "리뷰 수정")
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable(value = "reviewId") Long reviewId, @RequestBody ReviewUpdateDto reviewUpdateDto) {
        Long updatedReviewId = reviewService.updateReview(reviewId, reviewUpdateDto);
        return response.success(ResponseCode.REVIEW_UPDATED, updatedReviewId);
    }

    // 리뷰 삭제
    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable(value = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return response.success(ResponseCode.REVIEW_DELETED);
    }

}
