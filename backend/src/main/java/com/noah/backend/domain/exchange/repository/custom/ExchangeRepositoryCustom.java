package com.noah.backend.domain.exchange.repository.custom;

import java.util.Optional;
import java.util.OptionalLong;

public interface ExchangeRepositoryCustom {
    Long getExchangeIdByTravelId(Long travelId);
}
