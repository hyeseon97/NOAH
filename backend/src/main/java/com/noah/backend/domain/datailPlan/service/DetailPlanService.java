package com.noah.backend.domain.datailPlan.service;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;
import java.util.List;

public interface DetailPlanService {

    List<DetailPlanDto> getDetailPlanList(String email, Long planId);

    Long createDetailPlan(String email, Long planId, DetailPlanPostDto detailPlan);

    //DTO 수정 필요
    Long updateDetailPlan(String email, DetailPlanListDto detailPlanDto);

    void deleteDetailPlan(String email, Long detailId);

}
