package com.noah.backend.domain.travel.repository.custom;

import com.noah.backend.domain.travel.dto.responseDto.MyTravelGetDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;

import java.util.List;
import java.util.Optional;

public interface TravelRepositoryCustom  {

    Optional<List<TravelGetListDto>> getTravelList();

    Optional<List<TravelGetListDto>> getTravelListToMember(Long memberId);

//    Optional<TravelGetDtoJun> getTravelSelect(Long TravelId);

    Optional<List<Long>> findTravelPaymentDateIsToday(int todayDate);

    Optional<TravelGetDto> getTravelSelect(Long travelId);

    Optional<List<MyTravelGetDto>> getTravelAll(Long memberId);
}
