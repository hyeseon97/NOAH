package com.noah.backend.domain.review.service;

import com.noah.backend.domain.review.entity.Review;

import java.util.List;

public interface ReviewService {

    //이거 여행마다 달리는게 맞나?
    List<Review> getReviewList();

    Review getReviewSelect(Long ReviewId);

    Long createReview(Review review);

    Long updateReview(Long reviewId, Review review);

    void deleteReview(Long reviewId);
}
