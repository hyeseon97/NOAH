package com.noah.backend.domain.exchange.repository.custom;

import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.exchange.dto.responseDto.TargetExchangeRate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface ExchangeRepositoryCustom {
    Long getExchangeIdByTravelId(Long travelId);

    Optional<List<TargetExchangeRate>> getTargetExchangeRateTravel(CurrencyDto currency);
}
