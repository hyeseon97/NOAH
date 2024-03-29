package com.noah.backend.domain.suggest.dto.requestDto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestListReqDto {
	private Long travelId; // 여행 id;
}
