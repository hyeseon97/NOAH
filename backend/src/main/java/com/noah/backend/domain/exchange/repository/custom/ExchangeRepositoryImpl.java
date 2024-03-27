package com.noah.backend.domain.exchange.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExchangeRepositoryImpl implements ExchangeRepositoryCustom {

    private final JPAQueryFactory query;
}
