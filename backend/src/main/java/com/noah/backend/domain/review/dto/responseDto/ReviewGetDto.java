package com.noah.backend.domain.review.dto.responseDto;

import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.image.dto.requestDto.ImageGetDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewGetDto {
    private Long review_id;
    private int expense;
    private String country;
    private int people;
    private Date start_date;
    private Date end_date;

    @Setter
    private List<CommentListGetDto> commentList;
    @Setter
    private List<ImageGetDto> imageList;

}
