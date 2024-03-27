package com.noah.backend.domain.exchange.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.noah.backend.domain.exchange.entity.QExchange.exchange;


@RequiredArgsConstructor
public class ExchangeRepositoryImpl implements ExchangeRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Long getExchangeIdByTravelId(Long travelId) {
        Long exchangeId = query.select(exchange.id)
                .from(exchange)
                .where(exchange.groupAccount.travel.id.eq(travelId))
        .fetchOne();

        return exchangeId;
    }
}
