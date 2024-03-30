package com.noah.backend.domain.comment.dto.responseDto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListGetDto {

    private Long commentId;
    private Long memberId;
    private String content;

}
