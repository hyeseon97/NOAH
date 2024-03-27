package com.noah.backend.domain.image.service;

import com.noah.backend.domain.image.service.impl.ImageServiceImpl;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    Long saveImage(Long reviewId);

}
