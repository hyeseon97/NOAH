package com.noah.backend.domain.datailPlan.service;

import com.noah.backend.domain.datailPlan.entity.DetailPlan;

import java.util.List;

public interface DetailPlanService {
    //DTO 수정 필요
    List<DetailPlan> getDetailPlanList(Long PlanId);

    //DTO 수정 필요
    DetailPlan getDetailPlanSelect(Long DetailPlanId);

    //DTO 수정 필요
    Long updateDetailPlan(DetailPlan detailPlan);

    void deleteDetailPlan(Long detailId);

    Long createDetailPlan(Long travelId, DetailPlan detailPlan);
}
