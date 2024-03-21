package com.noah.backend.domain.apis.repository;

import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.apis.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("SELECT c FROM Currency c ORDER BY c.id DESC LIMIT 1")
    CurrencyDto getOne();
}
