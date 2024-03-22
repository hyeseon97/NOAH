package com.noah.backend.domain.image.controller;

import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Image 컨트롤러", description = "Image Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "이미지 저장", description = "이미지 저장 / reviewId 필요 / 해당 id를 바탕으로 s2서버에서 리스트 형태로 불러옴")
    @PostMapping
    public ResponseEntity<Long> saveImage(@RequestParam Long reviewId){

        Long saveImageId = imageService.saveImage(reviewId);
        return ResponseEntity.ok(saveImageId);

    }

}
