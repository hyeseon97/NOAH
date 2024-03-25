package com.noah.backend.domain.comment.service.impl;

import com.noah.backend.domain.comment.dto.requestDto.CommentUpdateDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.comment.entity.Comment;
import com.noah.backend.domain.comment.repository.CommentRepository;
import com.noah.backend.domain.comment.service.CommentService;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final TicketRepository ticketRepository;
//    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;


    @Override
    public List<CommentListGetDto> getCommentList(Long reviewId) {
        return commentRepository.getCommentList(reviewId)
                .orElseThrow(() -> new RuntimeException("코멘트가 없슈"));
    }

    @Override
    public CommentGetDto getCommentSelect(Long commentId) {
        return commentRepository.getCommentSelect(commentId)
                .orElseThrow(() -> new RuntimeException("코멘트가 없어요"));
    }

//    @Override
//    public Long createComment(CommentPostDto commentDto) {
//
//        Member foundMember = memberRepository.findById(commentDto.getMemberId())
//                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + commentDto.getMemberId()));
//        Review foundReview = reviewRepository.findById(commentDto.getReviewId())
//                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + commentDto.getReviewId()));
//
//        Comment comment = Comment.builder()
//                .content(commentDto.getContent())
//                .member(foundMember)
//                .review(foundReview)
//                .build();
//
//        return commentRepository.save(comment).getId();
//    }

    @Override
    public Long updateCommentTest(Long commentId, CommentUpdateDto commentDto) {
        Comment currentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("코멘트 없어용"));
        //이건 따로 값을 받아야할지도 모른다.
        Member writer = memberRepository.findById(commentDto.getMember_id())
                .orElseThrow(() -> new RuntimeException("멤버 id를 확인하세요"));

        currentComment.setContent(commentDto.getContent());
        currentComment.setMember(writer);

//        commentRepository.save(currentComment);


        return currentComment.getId();
    }

    @Override
    public Long updateCommentTestToMemberId(Long commentId, Long memberId, CommentUpdateDto commentDto) {
        Comment currentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("코멘트 없어용"));
        //이건 따로 값을 받아야할지도 모른다.
        Member writer = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버 id를 확인하세요"));

        currentComment.setContent(commentDto.getContent());
        currentComment.setMember(writer);

//        commentRepository.save(currentComment);


        return currentComment.getId();
    }



    //사실 이건 매칭만 되어있다면 memberId를 따로 보낼 필요가 없으니 content만 바꾸면 되는 건가?
    // 이건 물어보고 하자
    @Override
    public Long updateComment(Long commentId, CommentUpdateDto commentDto) {
        Comment currentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("코멘트 없어용"));
        currentComment.setContent(commentDto.getContent());
        commentRepository.save(currentComment);

        return currentComment.getId();
    }

    @Override
    public void deleteReview(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
