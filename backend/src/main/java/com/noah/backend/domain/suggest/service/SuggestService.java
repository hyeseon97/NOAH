package com.noah.backend.domain.suggest.service;
import com.noah.backend.domain.suggest.dto.responseDto.MainSuggestGetDto;
import com.noah.backend.domain.suggest.dto.responseDto.SuggestListResDto;
import java.util.List;

public interface SuggestService {
	List<SuggestListResDto> loginGetSuggestList(Long travelId);

	List<MainSuggestGetDto> getSuggestMain(String email);

	SuggestListResDto nonLoginGetSuggestList();
}
