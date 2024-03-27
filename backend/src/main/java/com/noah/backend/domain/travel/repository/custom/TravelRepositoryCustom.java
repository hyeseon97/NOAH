package com.noah.backend.domain.travel.repository.custom;

import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelRepositoryCustom  {

    Optional<List<TravelGetListDto>> getTravelList();

    Optional<TravelGetDto> getTravelSelect(Long TravelId);

    Optional<List<Long>> findTravelPaymentDateIsToday(int todayDate);
}
