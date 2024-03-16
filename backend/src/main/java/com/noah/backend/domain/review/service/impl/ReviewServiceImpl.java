package com.noah.backend.domain.review.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;

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
