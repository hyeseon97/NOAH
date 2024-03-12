package com.noah.backend.domain.plan.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plan 컨트롤러", description = "Plan Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Plan")
public class PlanController {

}
