package com.noah.backend.domain.review.service;

import com.noah.backend.domain.review.dto.requestDto.ReviewPostDto;
import com.noah.backend.domain.review.dto.requestDto.ReviewUpdateDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;

import java.util.List;

public interface ReviewService {

    //이거 여행마다 달리는게 맞나?
    List<ReviewListGetDto> getReviewList();

    ReviewGetDto getReviewSelect(Long ReviewId);

    Long createReview(ReviewPostDto review);

    Long updateReview(Long reviewId, ReviewUpdateDto review);

    void deleteReview(Long reviewId);
}
