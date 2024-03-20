package com.noah.backend.domain.datailPlan.controller;

import com.noah.backend.domain.review.service.ReviewService;
import com.noah.backend.global.format.code.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "DetailPlan 컨트롤러", description = "DetailPlan Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/DetailPlan")
public class DetailPlanController {


}
