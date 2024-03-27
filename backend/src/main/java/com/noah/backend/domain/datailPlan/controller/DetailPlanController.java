package com.noah.backend.domain.datailPlan.controller;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanUpdateDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.noah.backend.domain.datailPlan.service.DetailPlanService;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DetailPlan 컨트롤러", description = "DetailPlan Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detailPlan")
public class DetailPlanController {

    private final DetailPlanService detailPlanService;
    private final ApiResponse response;

    @Operation(summary = "상세 계획 목록 조회", description = "상세 계획 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<?> getDetailPlanList(@RequestParam(value = "planId") Long planId){
        List<DetailPlanListGetFromPlanDto> detailPlanList = detailPlanService.getDetailPlanList(planId);
        return response.success(ResponseCode.DETAILPLAN_INFO_FETCHED, detailPlanList);
    }

    @Operation(summary = "상세 계획 조회", description = "상세 목록 조회")
    @GetMapping("/{detailPlanId}")
    public ResponseEntity<?> detailPlanSelect(@PathVariable(value = "detailPlanId") Long detailPlanId){
        DetailPlanGetDto detailPlan = detailPlanService.getDetailPlanSelect(detailPlanId);
        return response.success(ResponseCode.DETAILPLAN_INFO_FETCHED, detailPlan);
    }

    @Operation(summary = "상세 계획 작성", description = "상세 계획 작성 / PlanId 필요")
    @PostMapping
    public ResponseEntity<?> createDetailPlan(@RequestParam(value = "planId") Long planId, @RequestBody DetailPlanPostDto detailPlanDto){
        Long createDetailPlanId = detailPlanService.createDetailPlan(planId, detailPlanDto);
        return response.success(ResponseCode.DETAILPLAN_CREATED, createDetailPlanId);
    }


    @Operation(summary = "상세 계획 수정", description = "상세 계획 목록 수정 / detailPlanId 필요")
    @PutMapping("/{detailPlanId}")
    public ResponseEntity<?> updateDeiltePlan(@PathVariable(value = "detailPlanId") Long detailPlanId, @RequestBody DetailPlanUpdateDto detailPlanDto){
        Long updateDetailPlanId = detailPlanService.updateDetailPlan(detailPlanId, detailPlanDto);
        return response.success(ResponseCode.DETAILPLAN_INFO_UPDATED, updateDetailPlanId);
    }

    @Operation(summary = "상세 계획 삭제", description = "상세 계획 삭제")
    @DeleteMapping("/{detailPlanId}")
    public ResponseEntity<?> deleteDetailPlan(@PathVariable(value = "detailPlanId") Long detailPlanId){
        detailPlanService.deleteDetailPlan(detailPlanId);
        return response.success(ResponseCode.DETAILPLAN_DELETED);
    }


}
