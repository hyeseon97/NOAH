package com.noah.backend.domain.comment.service;

import com.noah.backend.domain.comment.dto.requestDto.CommentPostDto;
import com.noah.backend.domain.comment.dto.requestDto.CommentUpdateDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;

import java.util.List;

public interface CommentService {

    List<CommentListGetDto> getCommentList(Long reivewId);

    CommentGetDto getCommentSelect(Long commentId);

//    Long createComment(CommentPostDto comment);

    Long updateCommentTest(Long commentId, CommentUpdateDto commentDto);

    Long updateComment(Long commentId, CommentUpdateDto comment);

    void deleteReview(Long commentId);

}
