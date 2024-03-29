package com.noah.backend.domain.suggest.dto.responseDto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestListResDto {
	private int expense; //경비
	private String Country; //여행국가
	private int people; //인원 수
	private Date startDate; //시작일
	private Date endDate; //종료일
	private List<Long> imageIdList; //이미지ID
}
