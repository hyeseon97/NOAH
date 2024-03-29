package com.noah.backend.domain.comment.service;

import com.noah.backend.domain.comment.dto.requestDto.CommentUpdateDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;

import java.util.List;

public interface CommentService {

    List<CommentListGetDto> getCommentList(Long reviewId);

    Long updateComment(String email, CommentUpdateDto commentDto);

}
