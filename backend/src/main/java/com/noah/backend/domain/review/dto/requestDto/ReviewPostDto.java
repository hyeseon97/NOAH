package com.noah.backend.domain.review.dto.requestDto;

import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.image.dto.requestDto.ImageGetDto;
import lombok.*;

import java.util.Date;
import java.util.List;

//@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPostDto {

    private Long travelId;
    private List<Long> imageIdList;

}
