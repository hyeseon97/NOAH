package com.noah.backend.domain.review.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review 컨트롤러", description = "Review Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Review")
public class ReviewController {
}
