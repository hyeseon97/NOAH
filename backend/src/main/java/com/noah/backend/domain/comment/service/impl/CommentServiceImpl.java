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
import com.noah.backend.global.exception.comment.CommentNotFound;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.review.ReviewPermissionDenied;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;


    @Override
    public List<CommentListGetDto> getCommentList(Long reviewId) {
        return commentRepository.getCommentList(reviewId)
                .orElseThrow(CommentNotFound::new);
    }

    @Transactional
    @Override
    public Long updateComment(String email, CommentUpdateDto commentDto) {

        Member writer = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Comment comment = commentRepository.findCommentByMemberIdAndTravelId(writer.getId(), commentDto.getReviewId()).orElseThrow(
            ReviewPermissionDenied::new);

        comment.setContent(commentDto.getContent());

        return comment.getId();
    }

}
