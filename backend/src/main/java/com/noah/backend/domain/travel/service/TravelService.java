package com.noah.backend.domain.travel.service;

import com.noah.backend.domain.travel.dto.responseDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelUpdateDto;

import java.util.List;

public interface TravelService {

    List<TravelGetListDto> getTravelList();

    TravelGetDto getTravelSelect(Long travelId);

    List<TravelGetListDto> getTravelMemberId(Long memberId);

    Long createTravel(TravelPostDto travelDto);

    Long createTravelTest(TravelPostDto travelDto, Long memberId);

    Long updateTravel(Long travelId, TravelUpdateDto travelDto);

    void deleteTravel(Long travelId);
}
