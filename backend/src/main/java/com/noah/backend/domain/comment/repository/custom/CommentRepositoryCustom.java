package com.noah.backend.domain.comment.repository.custom;

import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryCustom {


    Optional<List<CommentListGetDto>> getCommentList(Long reviewId);

    Optional<CommentGetDto> getCommentSelect(Long CommentId);

}
