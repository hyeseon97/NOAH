package com.noah.backend.domain.image.repository.custom;

import com.noah.backend.domain.image.dto.requestDto.ImageGetDto;
import com.noah.backend.domain.image.dto.requestDto.ImageListGetDto;
import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.suggest.dto.requestDto.SuggestImageGetDto;

import java.util.List;
import java.util.Optional;

public interface ImageRepositoryCustom {

    Optional<List<ImageListGetDto>> getReviewList(Long ReviewId);

    Optional<ImageGetDto> getReviewSelect(Long CommentId);

	Optional<List<SuggestImageGetDto>> findImageOfReview(Long reviewId);

    Optional<List<SuggestImageGetDto>> getImageList(Long reviewId);
}
