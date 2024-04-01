package com.noah.backend.domain.suggest.dto.responseDto;

import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.suggest.dto.requestDto.SuggestImageGetDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestListResDto {
	private Long id;
	private int expense;
	private String country;
	private int people;
	private Date startDate;
	private Date endDate;
	private List<SuggestImageGetDto> imageList; //이미지

	public SuggestListResDto(Long id, int expense, String country, int people, Date startDate, Date endDate) {
		this.id = id;
		this.expense = expense;
		this.country = country;
		this.people = people;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
