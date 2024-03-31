package com.noah.backend.domain.review.service.impl;

import com.noah.backend.domain.comment.entity.Comment;
import com.noah.backend.domain.comment.repository.CommentRepository;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.repository.ImageRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.dto.requestDto.ReviewPostDto;
import com.noah.backend.domain.review.dto.requestDto.ReviewUpdateDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewGetDto;
import com.noah.backend.domain.review.dto.responseDto.ReviewListGetDto;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.trade.repository.TradeRepository;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.image.ImageNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.plan.PlanNotFound;
import com.noah.backend.global.exception.review.ReviewNotFound;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
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
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final TradeRepository tradeRepository;
    private final ImageRepository imageRepository;
    private final TravelRepository travelRepository;

    @Override
    public List<ReviewListGetDto> getReviewList() {
        return reviewRepository.getReviewList()
                .orElseThrow(ReviewNotFound::new);
    }

    @Override
    public ReviewGetDto getReviewSelect(Long reviewId) {
        return reviewRepository.getReviewSelect(reviewId)
                .orElseThrow(ReviewNotFound::new);
    }

    @Override
    @Transactional
    public Long createReview(String email, ReviewPostDto reviewPostDto) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), reviewPostDto.getTravelId()).orElseThrow(
            MemberTravelAccessException::new);

        // 총 사용 경비와 인원수 계산
        int expense = tradeRepository.getTotalExpense(reviewPostDto.getTravelId()).orElse(0);
        int people = memberTravelRepository.getTotalPeople(reviewPostDto.getTravelId()).orElse(0);

        // 나라, 시작일, 종료일을 구하기 위한 계획 엔티티 조회
        Plan plan = planRepository.findByTravelId(reviewPostDto.getTravelId()).orElseThrow(PlanNotFound::new);

        // 여행 엔티티 조회
        Travel travel = travelRepository.findById(reviewPostDto.getTravelId()).orElseThrow(TravelNotFoundException::new);

        // 리뷰 엔티티 생성
        Review review = Review.builder()
                .expense(expense)
                .people(people)
                .country(plan.getCountry())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .build();

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        // 이미지 리스트 반복해서 이미지 엔티티 조회해서 리뷰 연결하기
        for(Long imageId : reviewPostDto.getImageIdList()){

            Image image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
            image.setReview(savedReview);

        }

        return savedReview.getId();
    }

//    @Override
//    @Transactional
//    public Long createReviewTest(ReviewPostDto reviewDto, Long memberId) {
//        Review createReview = Review.builder()
//                .expense(reviewDto.getExpense())
//                .country(reviewDto.getCountry())
//                .people(reviewDto.getPeople())
//                .startDate(reviewDto.getStart_date())
//                .endDate(reviewDto.getEnd_date())
//                .build();
//        Review savedReview = reviewRepository.save(createReview);
//
////        Member findMember = memberRepository.findById(memberId)
////                .orElseThrow(() -> new NotFoundException("회원 id가 없어요"));
//
//        for (int i = 0; i < reviewDto.getPeople(); i++) {
//
////            Member joinMember = memberTravelRepository.findById()
//
//            Comment comment = Comment.builder()
//                    .content(null)
//                    .member(null)
//                    .review(savedReview)
//                    .build();
//
//            commentRepository.save(comment);
//
//        }
//
//        return savedReview.getId();
//    }
//
//    @Override
//    @Transactional
//    public Long createReviewTest2(ReviewPostDto reviewDto, Long travelId) {
//        Review createReview = Review.builder()
//                .expense(reviewDto.getExpense())
//                .country(reviewDto.getCountry())
//                .people(reviewDto.getPeople())
//                .startDate(reviewDto.getStart_date())
//                .endDate(reviewDto.getEnd_date())
//                .build();
//        Review savedReview = reviewRepository.save(createReview);
//
//            List<MemberTravelListGetDto> memberTravelList = memberTravelRepository.findByTravelId(travelId)
//                    .orElseThrow(TravelNotFoundException::new);
//
//        for (MemberTravelListGetDto memberTravel : memberTravelList) {
//                Long joinMemberId = memberTravel.getMember_id();
//
//                Member joinMember = memberRepository.findById(joinMemberId)
//                        .orElseThrow(MemberNotFoundException::new);
//
//            Comment comment = Comment.builder()
//                    .content("")
//                    .member(joinMember)
//                    .review(savedReview)
//                    .build();
//
//            commentRepository.save(comment);
//            }
//
//        return savedReview.getId();
//    }

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
