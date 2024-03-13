package com.noah.backend.domain.review.repository.custom;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryCustom {

    Optional<List<ReviewListGetDto>> getReviewList();

    Optional<ReviewGetDto> getReviewSelect(Long ReviewId);


}
