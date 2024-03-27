package com.noah.backend.domain.datailPlan.service;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanUpdateDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.noah.backend.domain.datailPlan.entity.DetailPlan;

import java.util.List;

public interface DetailPlanService {
    //DTO 수정 필요
    List<DetailPlanListGetFromPlanDto> getDetailPlanList(Long PlanId);

    //DTO 수정 필요
    DetailPlanGetDto getDetailPlanSelect(Long DetailPlanId);

    Long createDetailPlan(Long travelId, DetailPlanPostDto detailPlan);

    //DTO 수정 필요
    Long updateDetailPlan(Long detailPlanId, DetailPlanUpdateDto detailPlan);

    void deleteDetailPlan(Long detailId);

}
