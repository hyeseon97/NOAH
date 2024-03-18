package com.noah.backend.domain.travel.service;

import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.entity.Travel;

import java.util.List;

public interface TravelService {

    List<TravelGetListDto> getTravelList();

    TravelGetDto getTravelSelect(Long travelId);

    Long createTravel(Travel travel);

    Long updateTravel(Long travelId, Travel travel);

    void deleteTravel(Long travelId);
}
