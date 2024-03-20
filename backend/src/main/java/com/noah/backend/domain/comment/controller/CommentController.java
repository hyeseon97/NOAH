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
@RequestMapping("/api/v1/Comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "코멘트 목록 조회", description = "코멘트 목록 조회")
    @GetMapping("/list/{travelId}")
    public ResponseEntity<List<CommentListGetDto>> getCommentList(@PathVariable Long travelId) {
        List<CommentListGetDto> commentList = commentService.getCommentList(travelId);
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentGetDto> getCommentSelect(@PathVariable Long commentId){
        CommentGetDto selectedComment = commentService.getCommentSelect(commentId);
        return ResponseEntity.ok(selectedComment);
    }

    @PostMapping
    public ResponseEntity<Long> createComment(@RequestBody CommentPostDto commentCreateDto){
        Long createCommentId = commentService.createComment(commentCreateDto);
        return ResponseEntity.ok(createCommentId);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto reviewUpdateDto){
        Long updateCommentId = commentService.updateComment(commentId, reviewUpdateDto);
        return ResponseEntity.ok(updateCommentId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteReview(commentId);
        return ResponseEntity.ok().build();
    }
}
