package com.noah.backend.domain.comment.dto.requestDto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateDto {

    private Long id;
    @Setter
    private String content;
    @Setter
    private Long member_id;
    @Setter
    private Long review_id;

}
