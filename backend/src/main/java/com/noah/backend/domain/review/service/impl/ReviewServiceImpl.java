package com.noah.backend.domain.review.service.impl;

import com.noah.backend.domain.comment.entity.Comment;
import com.noah.backend.domain.comment.repository.CommentRepository;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
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
import org.webjars.NotFoundException;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

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
        Review review = Review.builder()
                .expense(reviewDto.getExpense())
                .country(reviewDto.getCountry())
                .people(reviewDto.getPeople())
                .startDate(reviewDto.getStart_date())
                .endDate(reviewDto.getEnd_date())
                .build();
        Review savedReview = reviewRepository.save(review);

        return savedReview.getId();
    }

    @Override
    @Transactional
    public Long createReviewTest(ReviewPostDto reviewDto, Long memberId) {
        Review createReview = Review.builder()
                .expense(reviewDto.getExpense())
                .country(reviewDto.getCountry())
                .people(reviewDto.getPeople())
                .startDate(reviewDto.getStart_date())
                .endDate(reviewDto.getEnd_date())
                .build();
        Review savedReview = reviewRepository.save(createReview);

//        Member findMember = memberRepository.findById(memberId)
//                .orElseThrow(() -> new NotFoundException("회원 id가 없어요"));

        for (int i = 0; i < reviewDto.getPeople(); i++) {

            Comment comment = Comment.builder()
                    .content(null)
                    .member(null)
                    .review(savedReview)
                    .build();

            commentRepository.save(comment);

        }

        return savedReview.getId();
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
