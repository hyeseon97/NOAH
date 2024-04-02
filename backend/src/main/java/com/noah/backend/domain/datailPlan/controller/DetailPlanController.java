package com.noah.backend.domain.datailPlan.controller;

import com.google.rpc.context.AttributeContext.Auth;
import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;
import com.noah.backend.domain.datailPlan.service.DetailPlanService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DetailPlan 컨트롤러", description = "DetailPlan Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detailPlan")
public class DetailPlanController {

    private final DetailPlanService detailPlanService;
    private final ApiResponse response;

    @Operation(summary = "상세 계획 목록 조회", description = "상세 계획 목록 조회")
    @GetMapping("/{planId}")
    public ResponseEntity<?> getDetailPlanList(@Parameter(hidden = true) Authentication authentication, @PathVariable(value = "planId") Long planId){
        List<DetailPlanDto> detailPlanList = detailPlanService.getDetailPlanList(authentication.getName(), planId);
        return response.success(ResponseCode.DETAILPLAN_INFO_FETCHED, detailPlanList);
    }

    @Operation(summary = "상세 계획 추가", description = "상세 계획 추가 / PlanId 필요")
    @PostMapping
    public ResponseEntity<?> createDetailPlan(@Parameter(hidden = true) Authentication authentication, @RequestParam Long planId, @RequestBody DetailPlanPostDto detailPlanDto){
        Long createDetailPlanId = detailPlanService.createDetailPlan(authentication.getName(), planId, detailPlanDto);
        return response.success(ResponseCode.DETAILPLAN_CREATED, createDetailPlanId);
    }

    @Operation(summary = "상세 계획 수정", description = "상세 계획 목록 수정(순서 수정) / detailPlanId 필요")
    @PutMapping()
    public ResponseEntity<?> updateDetailPlan(@Parameter(hidden = true) Authentication authentication, @RequestBody DetailPlanListDto detailPlanList){
        Long updateDetailPlanId = detailPlanService.updateDetailPlan(authentication.getName(), detailPlanList);
        return response.success(ResponseCode.DETAILPLAN_INFO_UPDATED, updateDetailPlanId);
    }

    @Operation(summary = "상세 계획 삭제", description = "상세 계획 삭제")
    @DeleteMapping("/{detailPlanId}")
    public ResponseEntity<?> deleteDetailPlan(@Parameter(hidden = true) Authentication authentication, @PathVariable(value = "detailPlanId") Long detailPlanId){
        detailPlanService.deleteDetailPlan(authentication.getName(), detailPlanId);
        return response.success(ResponseCode.DETAILPLAN_DELETED);
    }


}
