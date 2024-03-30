package com.noah.backend.domain.comment.controller;

import com.noah.backend.domain.comment.dto.requestDto.CommentUpdateDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.comment.service.CommentService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment 컨트롤러", description = "Comment Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;
    private final ApiResponse response;

    @Operation(summary = "코멘트 목록 조회", description = "코멘트 목록 조회 / travelId가 필요하다.")
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getCommentList(@PathVariable(value = "reviewId") Long reviewId) {
        List<CommentListGetDto> commentList = commentService.getCommentList(reviewId);
        return response.success(ResponseCode.COMMENT_FETCHED, commentList);
    }

    @Operation(summary = "코멘트 수정", description = "코멘트 수정 작업")
    @PutMapping()
    public ResponseEntity<?> updateCommentToMemberId(@Parameter(hidden = true) Authentication authentication, @RequestBody CommentUpdateDto reviewUpdateDto){
        Long updateCommentId = commentService.updateComment(authentication.getName(), reviewUpdateDto);
        return response.success(ResponseCode.COMMENT_UPDATED, updateCommentId);
    }

}
