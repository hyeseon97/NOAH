package com.noah.backend.domain.datailPlan.service;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;

public interface DetailPlanService {

    DetailPlanListDto getDetailPlanList(String email, Long planId);

    Long createDetailPlan(String email, DetailPlanPostDto detailPlan);

    //DTO 수정 필요
    Long updateDetailPlan(String email, DetailPlanListDto detailPlanDto);

    void deleteDetailPlan(String email, Long detailId);

}
