package com.noah.backend.domain.travel.repository;

import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.custom.TravelRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long>, TravelRepositoryCustom {
}
