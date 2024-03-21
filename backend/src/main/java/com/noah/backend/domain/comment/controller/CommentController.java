package com.noah.backend.domain.comment.controller;

import com.noah.backend.domain.comment.dto.requestDto.CommentPostDto;
import com.noah.backend.domain.comment.dto.requestDto.CommentUpdateDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment 컨트롤러", description = "Comment Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "코멘트 목록 조회", description = "코멘트 목록 조회 / travelId가 필요하다.")
    @GetMapping("/list/{travelId}")
    public ResponseEntity<List<CommentListGetDto>> getCommentList(@PathVariable Long travelId) {
        List<CommentListGetDto> commentList = commentService.getCommentList(travelId);
        return ResponseEntity.ok(commentList);
    }
    @Operation(summary = "코멘트 선택 조회", description = "코멘트 선택 조회 / commentId가 필요하다.")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentGetDto> getCommentSelect(@PathVariable Long commentId){
        CommentGetDto selectedComment = commentService.getCommentSelect(commentId);
        return ResponseEntity.ok(selectedComment);
    }

    @Operation(summary = "코멘트 생성", description = "코멘트 생성, 리뷰 기준으로 생성되기 때문에 reveiwId 필요.")
    @PostMapping
    public ResponseEntity<Long> createComment(@RequestBody CommentPostDto commentCreateDto){
        Long createCommentId = commentService.createComment(commentCreateDto);
        return ResponseEntity.ok(createCommentId);
    }

    @Operation(summary = "코멘트 수정", description = "코멘트 수정, commentId 필요")
    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto reviewUpdateDto){
        Long updateCommentId = commentService.updateComment(commentId, reviewUpdateDto);
        return ResponseEntity.ok(updateCommentId);
    }

    @Operation(summary = "코멘트 삭제", description = "코멘트 삭제, commentId 필요")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteReview(commentId);
        return ResponseEntity.ok().build();
    }
}
