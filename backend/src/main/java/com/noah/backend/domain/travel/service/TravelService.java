package com.noah.backend.domain.travel.service;

import com.noah.backend.domain.travel.dto.responseDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetDtoJun;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelUpdateDto;

import java.util.List;

public interface TravelService {

    List<TravelGetListDto> getTravelList();

    TravelGetDto getTravelSelect(String email, Long travelId);

//    List<TravelGetListDto> getTravelMemberId(String email);

    Long createTravel(TravelPostDto travelDto);

    Long createTravelTest(TravelPostDto travelDto, Long memberId);

    Long updateTravel(String email, TravelUpdateDto travelDto);

    void deleteTravel(String email, Long travelId);
}
