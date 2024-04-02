package com.noah.backend.domain.suggest.service;
import com.noah.backend.domain.suggest.dto.responseDto.MainSuggestGetDto;
import com.noah.backend.domain.suggest.dto.responseDto.SuggestListResDto;
import java.util.List;

public interface SuggestService {
	List<SuggestListResDto> getSuggestList(Long travelId);

	//메인페이지에서 띄울 내가 포함된 여행의 대표 추천 여행
	List<MainSuggestGetDto> getSuggestMain(String email);

	//둘러보기할때 메인페이지에서 띄울 대표 추천 여행
	List<MainSuggestGetDto> nonLoginGetSuggestMain();
}
