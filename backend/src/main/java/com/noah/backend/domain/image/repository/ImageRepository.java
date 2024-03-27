package com.noah.backend.domain.image.repository;

import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.repository.custom.ImageRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom{
}
