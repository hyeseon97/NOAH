package com.noah.backend.domain.comment.dto.responseDto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListGetDto {

    private Long id;
    @Setter
    private String content;
    @Setter
    private Long member;
    @Setter
    private Long review;

}
