package com.noah.backend.domain.suggest.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestImageGetDto {//제안기능에서 쓰이는 이미지Dto
    private Long imageId;
    private String imageUrl;

}
