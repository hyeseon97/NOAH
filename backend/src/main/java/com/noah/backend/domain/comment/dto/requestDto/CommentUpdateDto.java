package com.noah.backend.domain.comment.dto.requestDto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateDto {

    private Long reviewId;
    private String content;

}
