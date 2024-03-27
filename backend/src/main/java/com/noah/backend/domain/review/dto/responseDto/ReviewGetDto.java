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
//    private Long review_id;
    private int expense;
    private String country;

    public ReviewGetDto(int expense, String country, int people, Date start_date, Date end_date) {
        this.expense = expense;
        this.country = country;
        this.people = people;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    private int people;
    private Date start_date;
    private Date end_date;
    @Setter
    private List<CommentListGetDto> commentList;
    @Setter
    private List<ImageGetDto> imageList;

}
