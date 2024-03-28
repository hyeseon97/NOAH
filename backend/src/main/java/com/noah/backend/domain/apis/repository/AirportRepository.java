package com.noah.backend.domain.apis.repository;

import com.noah.backend.domain.apis.entity.Airport;
import com.noah.backend.domain.apis.repository.custom.AirportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer>,
    AirportRepositoryCustom {
}
