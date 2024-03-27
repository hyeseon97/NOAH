package com.noah.backend.domain.apis.repository.custom;

import com.noah.backend.domain.apis.dto.AirportDto;
import java.util.Optional;

public interface AirportRepositoryCustom {
    Optional<AirportDto> findByName(String name);
}
