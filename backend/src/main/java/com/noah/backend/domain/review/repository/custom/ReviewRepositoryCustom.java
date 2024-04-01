package com.noah.backend.domain.review.repository.custom;

import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.suggest.dto.responseDto.SuggestListResDto;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryCustom {

    Optional<List<ReviewListGetDto>> getReviewList();

    Optional<ReviewGetDto> getReviewSelect(Long ReviewId);

    //랜덤한 리뷰 아이디를 제공
    Optional<Integer> getRandomSuggestId();

    //인당 환산값보다 낮은 리뷰를 제공
    Optional<List<SuggestListResDto>> getSuggestReview(int priceOfPerson);
}
