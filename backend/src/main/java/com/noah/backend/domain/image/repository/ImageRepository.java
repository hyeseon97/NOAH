package com.noah.backend.domain.image.repository;

import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.repository.custom.ImageRepositoryCustom;
import com.noah.backend.domain.suggest.dto.requestDto.SuggestImageGetDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom{

}
