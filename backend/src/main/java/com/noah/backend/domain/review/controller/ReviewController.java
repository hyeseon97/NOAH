package com.noah.backend.domain.review.controller;

import com.noah.backend.domain.review.dto.requestDto.ReviewPostDto;
import com.noah.backend.domain.review.dto.requestDto.ReviewUpdateDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.global.format.code.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review 컨트롤러", description = "Review Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
//    private final ApiResponse apiResponse;

    @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<List<ReviewListGetDto>> getReviewList() {
        List<ReviewListGetDto> reviewList = reviewService.getReviewList();
        return ResponseEntity.ok(reviewList);
    }

    @Operation(summary = "리뷰 선택 조회", description = "리뷰 선택 조회")
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewGetDto> getReviewSelect(@PathVariable Long reviewId) {
        ReviewGetDto review = reviewService.getReviewSelect(reviewId);
        return ResponseEntity.ok(review);
    }

    // 리뷰 생성
    @Operation(summary = "리뷰 생성", description = "리뷰 생성")
    @PostMapping
    public ResponseEntity<Long> createReview(@RequestBody ReviewPostDto reviewCreateDto, @RequestParam Long travelId) {
        Long createdReviewId = reviewService.createReviewTest2(reviewCreateDto, travelId);
        return ResponseEntity.ok(createdReviewId);
    }

    // 리뷰 수정
    @Operation(summary = "리뷰 수정", description = "리뷰 수정")
    @PutMapping("/{reviewId}")
    public ResponseEntity<Long> updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateDto reviewUpdateDto) {
        Long updatedReviewId = reviewService.updateReview(reviewId, reviewUpdateDto);
        return ResponseEntity.ok(updatedReviewId);
    }

    // 리뷰 삭제
    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

}
