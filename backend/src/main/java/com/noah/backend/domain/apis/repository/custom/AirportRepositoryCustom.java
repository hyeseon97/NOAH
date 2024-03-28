package com.noah.backend.domain.apis.repository.custom;

import com.noah.backend.domain.apis.entity.Airport;
import java.util.Optional;

public interface AirportRepositoryCustom {
    Optional<Airport> findByName(String name);
}
