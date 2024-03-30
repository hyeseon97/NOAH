package com.noah.backend.domain.plan.dto.responseDto;

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
public class SimplePlan {

    private int day;
    private int sequence;
    private String place;
    private Long imageId;
    private String imageUrl;
    
}
