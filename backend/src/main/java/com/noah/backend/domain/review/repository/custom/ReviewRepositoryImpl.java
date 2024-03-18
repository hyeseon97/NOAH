package com.noah.backend.domain.review.repository.custom;

import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.noah.backend.domain.review.entity.QReview.review;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory query;


    @Override
    public Optional<List<ReviewListGetDto>> getReviewList() {
        List<ReviewListGetDto> reviewDtos = query
                .select(constructor(ReviewListGetDto.class,
                        review.id,
                        review.expense,
                        review.country,
                        review.people,
                        review.startDate,
                        review.endDate))
                .from(review)
                .fetch();

        return Optional.ofNullable(reviewDtos.isEmpty() ? null : reviewDtos);
    }


    @Override
    public Optional<ReviewGetDto> getReviewSelect(Long reviewId) {
        ReviewGetDto reviewDto = query
                .select(Projections.constructor(ReviewGetDto.class,
                        review.id,
                        review.expense,
                        review.country,
                        review.people,
                        review.startDate,
                        review.endDate))
                .from(review)
                .where(review.id.eq(reviewId))
                .fetchOne();

        return Optional.ofNullable(reviewDto);
    }

}
