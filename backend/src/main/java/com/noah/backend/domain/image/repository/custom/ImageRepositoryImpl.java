package com.noah.backend.domain.image.repository.custom;

import com.noah.backend.domain.image.dto.requestDto.ImageGetDto;
import com.noah.backend.domain.image.dto.requestDto.ImageListGetDto;
import com.noah.backend.domain.image.entity.Image;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.image.entity.QImage.image;
import static com.noah.backend.domain.review.entity.QReview.review;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<ImageListGetDto>> getReviewList(Long reviewId) {
        List<ImageListGetDto> imageList = query
                .select(constructor(ImageListGetDto.class,
                        image.url))
                .from(image)
                .leftJoin(review).where(image.review.id.eq(reviewId))
                .fetch();
        return Optional.ofNullable(imageList.isEmpty() ? null : imageList);
    }

    @Override
    public Optional<ImageGetDto> getReviewSelect(Long imageId) {
        ImageGetDto imageSelect = query
                .select(Projections.constructor(ImageGetDto.class
                ,image.url))
                .from(image).where(image.id.eq(imageId)).fetchOne();
        return Optional.ofNullable(imageSelect);
    }

    @Override
    public Optional<List<Long>> findImageOfReview(Long reviewId) {
        return Optional.ofNullable(query.select(image.id)
                .from(image)
                .where(image.review.id.eq(reviewId))
                .fetch());
    }
}
