package com.noah.backend.domain.review.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.dto.requestDto.ReviewPostDto;
import com.noah.backend.domain.review.dto.requestDto.ReviewUpdateDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import jakarta.transaction.Transactional;
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
        return reviewRepository.getReviewList()
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
    }

    @Override
    public ReviewGetDto getReviewSelect(Long reviewId) {
        return reviewRepository.getReviewSelect(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public Long createReview(ReviewPostDto reviewDto) {
        int expense = (reviewDto.getExpense() != null) ? reviewDto.getExpense() : 0;
        int people = (reviewDto.getPeople() != null) ? reviewDto.getPeople() : 0; // `null` 체크 추가
        System.out.println("전" + reviewDto.getCountry());
        Review review = Review.builder()
                .expense(expense)
                .country(reviewDto.getCountry())
                .people(people)
                .startDate(reviewDto.getStart_date())
                .endDate(reviewDto.getEnd_date())
                .build();
        Review savedReview = reviewRepository.save(review);
        System.out.println(savedReview.getCountry());
        return review.getId();
    }

    @Override
    public Long updateReview(Long reviewId, ReviewUpdateDto review) {
        Review currentReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
        currentReview.setExpense(review.getExpense());
        currentReview.setCountry(review.getCountry());
        currentReview.setPeople(review.getPeople());
        currentReview.setStartDate(review.getStart_date());
        currentReview.setEndDate(review.getEnd_date());

        reviewRepository.save(currentReview);

        return currentReview.getId();
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
