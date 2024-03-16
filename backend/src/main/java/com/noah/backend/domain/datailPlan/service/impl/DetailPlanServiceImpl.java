package com.noah.backend.domain.datailPlan.service.impl;

import com.noah.backend.domain.datailPlan.entity.DetailPlan;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.datailPlan.service.DetailPlanService;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class DetailPlanServiceImpl implements DetailPlanService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<DetailPlan> getDetailPlanList(Long PlanId) {
        return null;
    }

    @Override
    public DetailPlan getDetailPlanSelect(Long DetailPlanId) {
        return null;
    }

    @Override
    public Long updateDetailPlan(DetailPlan detailPlan) {
        return null;
    }

    @Override
    public void deleteDetailPlan(Long detailId) {

    }

    @Override
    public Long createDetailPlan(Long travelId, DetailPlan detailPlan) {
        return null;
    }
}
