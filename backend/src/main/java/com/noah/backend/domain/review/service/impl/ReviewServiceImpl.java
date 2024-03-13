package com.noah.backend.domain.review.service.impl;

import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<ReviewListGetDto> getReviewList() {
        return null;
    }

    @Override
    public ReviewGetDto getReviewSelect(Long ReviewId) {
        return null;
    }

    @Override
    public Long createReview(Review review) {
        return null;
    }

    @Override
    public Long updateReview(Long reviewId, Review review) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }
}
