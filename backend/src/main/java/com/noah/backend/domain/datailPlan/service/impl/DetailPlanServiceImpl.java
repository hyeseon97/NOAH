package com.noah.backend.domain.datailPlan.service.impl;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanUpdateDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.noah.backend.domain.datailPlan.entity.DetailPlan;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.datailPlan.service.DetailPlanService;
import com.noah.backend.domain.plan.entity.Plan;
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
//    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<DetailPlanListGetFromPlanDto> getDetailPlanList(Long PlanId) {
        return detailPlanRepository.getDetailPlanList(PlanId).orElseThrow(() -> new RuntimeException("세부 계획이 없어용"));
    }

    @Override
    public DetailPlanGetDto getDetailPlanSelect(Long detailPlanId) {
        return detailPlanRepository.getDetailPlanSelect(detailPlanId).orElseThrow(() -> new RuntimeException("세부 계획이 없어용"));
    }

    @Override
    public Long createDetailPlan(Long PlanId, DetailPlanPostDto detailPlan) {
        DetailPlan NewDetailPlan = DetailPlan.builder()
                .day(detailPlan.getDay())
                .sequence(detailPlan.getSequence())
                .place(detailPlan.getPlace())
                .pinX(detailPlan.getPinX())
                .pinY(detailPlan.getPinY())
                .memo(detailPlan.getMemo())
                .time(detailPlan.getTime())
//                .plan(detailPlan.getPlan())
                .build();
        DetailPlan savedDetailPlan = detailPlanRepository.save(NewDetailPlan);
        return NewDetailPlan.getId();
    }

    @Override
    public Long updateDetailPlan(Long detailPlanId, DetailPlanUpdateDto detailPlan) {
        DetailPlan currentDetailPlan = detailPlanRepository.findById(detailPlanId).orElseThrow(() -> new RuntimeException("없어요 상세내역이"));

        currentDetailPlan.setDay(detailPlan.getDay());
        currentDetailPlan.setSequence(detailPlan.getSequence());
        currentDetailPlan.setPlace(detailPlan.getPlace());
        currentDetailPlan.setPinX(detailPlan.getPinX());
        currentDetailPlan.setPinY(detailPlan.getPinY());
        currentDetailPlan.setMemo(detailPlan.getMemo());
        currentDetailPlan.setTime(detailPlan.getTime());

        detailPlanRepository.save(currentDetailPlan);
        return currentDetailPlan.getId();
    }

    @Override
    public void deleteDetailPlan(Long detailId) {
        detailPlanRepository.deleteById(detailId);
    }

}
