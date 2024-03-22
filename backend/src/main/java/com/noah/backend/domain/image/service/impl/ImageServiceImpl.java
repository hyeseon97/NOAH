package com.noah.backend.domain.image.service.impl;

import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.repository.ImageRepository;
import com.noah.backend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Long saveImage(Long reviewId) {
        Image image = Image.builder()
                .id(reviewId)
                .build();

        return imageRepository.save(image).getId();
    }
}
