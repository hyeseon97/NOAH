package com.noah.backend.domain.suggest.service;
import com.noah.backend.domain.suggest.dto.responseDto.SuggestListResDto;
import java.util.List;

public interface SuggestService {
	List<SuggestListResDto> getSuggestList(Long travelId);

}
