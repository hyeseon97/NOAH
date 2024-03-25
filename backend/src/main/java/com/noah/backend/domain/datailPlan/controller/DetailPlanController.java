package com.noah.backend.domain.datailPlan.controller;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanUpdateDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanGetDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.noah.backend.domain.datailPlan.service.DetailPlanService;
import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.global.format.code.ApiResponse;
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

    @Operation(summary = "상세 계획 목록 조회", description = "상세 계획 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<List<DetailPlanListGetFromPlanDto>> getDetailPlanList(@RequestParam(value = "planId") Long planId){
        List<DetailPlanListGetFromPlanDto> detailPlanList = detailPlanService.getDetailPlanList(planId);
        return ResponseEntity.ok(detailPlanList);
    }

    @Operation(summary = "상세 계획 조회", description = "상세 목록 조회")
    @GetMapping("/{detailPlanId}")
    public ResponseEntity<DetailPlanGetDto> detailPlanSelect(@PathVariable(value = "detailPlanId") Long detailPlanId){
        DetailPlanGetDto detailPlan = detailPlanService.getDetailPlanSelect(detailPlanId);
        return ResponseEntity.ok(detailPlan);
    }

    @Operation(summary = "상세 계획 작성", description = "상세 계획 작성 / PlanId 필요")
    @PostMapping
    public ResponseEntity<Long> createDetailPlan(@RequestParam(value = "planId") Long planId, @RequestBody DetailPlanPostDto detailPlanDto){
        Long createDetailPlanId = detailPlanService.createDetailPlan(planId, detailPlanDto);
        return ResponseEntity.ok(createDetailPlanId);
    }


    @Operation(summary = "상세 계획 수정", description = "상세 계획 목록 수정 / detailPlanId 필요")
    @PutMapping("/{detailPlanId}")
    public ResponseEntity<Long> updateDeiltePlan(@PathVariable(value = "detailPlanId") Long detailPlanId, @RequestBody DetailPlanUpdateDto detailPlanDto){
        Long updateDetailPlanId = detailPlanService.updateDetailPlan(detailPlanId, detailPlanDto);
        return ResponseEntity.ok(updateDetailPlanId);
    }

    @Operation(summary = "상세 계획 삭제", description = "상세 계획 삭제")
    @DeleteMapping("/{detailPlanId}")
    public ResponseEntity<Void> deleteDetailPlan(@PathVariable(value = "detailPlanId") Long detailPlanId){
        detailPlanService.deleteDetailPlan(detailPlanId);
        return ResponseEntity.ok().build();
    }


}
