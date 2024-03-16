package com.noah.backend.domain.plan.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.plan.service.PlanService;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<PlanListGetFromTravelDto> getPlanList(Long travelId) {
        return null;
    }

    @Override
    public PlanGetDto getPlanSelect(Long PlanId) {
        return null;
    }

    @Override
    public Long createPlan(Long travelId, Plan plan) {
        return null;
    }

    @Override
    public Long updatePlan(Long PlanId, Plan plan) {
        return null;
    }

    @Override
    public void deletePlan(Long PlanId) {

    }
}
