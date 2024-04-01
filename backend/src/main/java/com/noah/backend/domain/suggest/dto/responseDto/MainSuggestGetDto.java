package com.noah.backend.domain.suggest.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainSuggestGetDto {

    private Long travelId;
    private Long reviewId;
    private String country;
    private int expense;
    private Long imageId;
    private String imageUrl;

}
