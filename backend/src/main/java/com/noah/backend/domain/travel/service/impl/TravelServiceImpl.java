package com.noah.backend.domain.travel.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class TravelServiceImpl implements TravelService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final TravelRepository travelRepository;

    @Override
    public List<TravelGetListDto> getTravelList() {
        return null;
    }

    @Override
    public TravelGetDto getTravelSelect(Long travelId) {
        return null;
    }

    @Override
    public Long createTravel(Travel travel) {
        return null;
    }

    @Override
    public Long updateTravel(Long travelId, Travel travel) {
        return null;
    }

    @Override
    public void deleteTravel(Long travelId) {

    }
}
