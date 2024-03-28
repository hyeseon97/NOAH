package com.noah.backend.domain.travel.repository.custom;

import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetListDto;

import java.util.List;
import java.util.Optional;

public interface TravelRepositoryCustom  {

    Optional<List<TravelGetListDto>> getTravelList();

    Optional<List<TravelGetListDto>> getTravelListToMember(Long memberId);

    Optional<TravelGetDto> getTravelSelect(Long TravelId);

    Optional<List<Long>> findTravelPaymentDateIsToday(int todayDate);
}
