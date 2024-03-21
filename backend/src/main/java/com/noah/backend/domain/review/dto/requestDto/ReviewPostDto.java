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

    private Long review_id;
    @Setter
    private Integer expense;
    @Setter
    private String country;
    @Setter
    private Integer people;
    @Setter
    private Date start_date;
    @Setter
    private Date end_date;
    @Setter
    private List<CommentListGetDto> commentList;
    @Setter
    private List<ImageGetDto> imageList;

}
