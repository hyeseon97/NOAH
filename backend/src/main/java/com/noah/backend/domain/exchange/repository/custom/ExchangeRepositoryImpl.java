package com.noah.backend.domain.exchange.repository.custom;

import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.exchange.dto.responseDto.TargetExchangeRate;
import com.noah.backend.domain.exchange.entity.Exchange;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.noah.backend.domain.exchange.entity.QExchange.exchange;
import static com.noah.backend.domain.groupaccount.entity.QGroupAccount.groupAccount;
import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;
import static com.noah.backend.domain.travel.entity.QTravel.travel;


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

    @Override
    public Optional<List<TargetExchangeRate>> getTargetExchangeRateTravel(CurrencyDto currency) {
        return Optional.ofNullable(query.select(Projections.constructor(TargetExchangeRate.class, travel.id, travel.title, memberTravel.member.id, exchange.targetExchangeCurrency, exchange.targetExchangeRate))
                                       .from(exchange)
                                       .leftJoin(groupAccount).on(exchange.groupAccount.id.eq(groupAccount.id))
                                       .leftJoin(travel).on(groupAccount.travel.id.eq(travel.id))
                                       .leftJoin(memberTravel).on(memberTravel.travel.id.eq(travel.id))
                                       .where((exchange.currency.eq("USD").and(exchange.targetExchangeRate.loe(currency.getBuyDollar())))
                                                  .or((exchange.currency.eq("JPY").and(exchange.targetExchangeRate.loe(currency.getBuyYen()))))
                                                  .or((exchange.currency.eq("CNY").and(exchange.targetExchangeRate.loe(currency.getBuyYuan()))))
                                                  .or((exchange.currency.eq("EUR").and(exchange.targetExchangeRate.loe(currency.getBuyEuro())))))
                                       .fetch());
    }

    @Override
    public Optional<Exchange> getExchangeByTravelId(Long travelId) {
        return Optional.ofNullable(query.select(exchange)
                                       .from(exchange)
                                       .leftJoin(groupAccount).on(exchange.groupAccount.id.eq(groupAccount.id))
                                       .where(groupAccount.travel.id.eq(travelId))
                                       .fetchOne());
    }
}
