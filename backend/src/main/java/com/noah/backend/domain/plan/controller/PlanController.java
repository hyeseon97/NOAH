package com.noah.backend.domain.plan.controller;

import com.noah.backend.domain.plan.dto.requestDto.PlanPostDto;
import com.noah.backend.domain.plan.dto.requestDto.PlanUpdateDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan 컨트롤러", description = "Plan Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plan")
public class PlanController {

    private final PlanService planService;

    @Operation(summary = "계획 목록 조회", description = "계획 정보 목록 조회 / travelId 필요")
    @GetMapping("/list")
    public ResponseEntity<List<PlanListGetFromTravelDto>> getPlanList(@RequestParam(value = "travelId") Long travelId) {
        List<PlanListGetFromTravelDto> planList = planService.getPlanList(travelId);
        return ResponseEntity.ok(planList);
    }

    @Operation(summary = "계획 상세 조회", description = "계획 정보 상세 조회 / planId 필요")
    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlanSelect(@PathVariable(value = "planId") Long planId){
        PlanGetDto selectPlan = planService.getPlanSelect(planId);
        if (selectPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(selectPlan);
    }

    @Operation(summary = "계획 작성", description = "계획 작성 / TravelId 필요")
    @PostMapping
    public ResponseEntity<Long> createPlan( @RequestBody PlanPostDto planDto){
        Long createPlanId = planService.createPlan(planDto);
        return ResponseEntity.ok(createPlanId);
    }

    @Operation(summary = "계획 수정", description = "계획 수정 / planId 필요")
    @PutMapping("/update/{planId}")
    public ResponseEntity<Long> updatePlan(@PathVariable(value = "planId") Long planId, @RequestBody PlanUpdateDto planDto){
        Long updatePlan = planService.updatePlan(planId, planDto);
        return ResponseEntity.ok(updatePlan);
    }

    @Operation(summary = "계획 시작 변경", description = "계획 시작 변경 / planId 필요")
    @PutMapping("/change/{planId}")
    public ResponseEntity<Boolean> changePlanStart(@PathVariable(value = "planId") Long planId, @RequestBody PlanUpdateDto planDto){
        boolean updatePlan = planService.changeStart(planId, planDto);
        return ResponseEntity.ok(updatePlan);
    }

    @Operation(summary = "계획 삭제", description = "계획 삭제 / planId 필요")
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable(value = "planId") Long planId){
        planService.deletePlan(planId);
        return ResponseEntity.ok().build();
    }



}
