package com.noah.backend.domain.review.repository.custom;

import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Optional<List<ReviewListGetDto>> getReviewList() {
        return Optional.empty();
    }

    @Override
    public Optional<ReviewGetDto> getReviewSelect(Long ReviewId) {
        return Optional.empty();
    }
}
