package com.noah.backend.domain.trade.repository;

import com.noah.backend.domain.trade.entity.Trade;
import com.noah.backend.domain.trade.repository.custom.TradeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long>, TradeRepositoryCustom {
}
