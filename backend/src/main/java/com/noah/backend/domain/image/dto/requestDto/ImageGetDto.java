package com.noah.backend.domain.image.dto.requestDto;

import com.noah.backend.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageGetDto {

//    private Long Id;
    private String url;
//    private Long review_id;

}
