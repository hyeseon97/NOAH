package com.noah.backend.domain.groupaccount.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccountPostDto {
    private Long travelId;      // 여행
    private Long accountId;     // 계좌
}
