package com.noah.backend.domain.exchange.repository;

import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.exchange.repository.custom.ExchangeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>, ExchangeRepositoryCustom {
}
