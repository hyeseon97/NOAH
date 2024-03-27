package com.noah.backend.domain.apis.repository.custom;

import static com.noah.backend.domain.apis.entity.QAirport.airport;
import static com.querydsl.core.types.Projections.constructor;

import com.noah.backend.domain.apis.dto.AirportDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AirportRepositoryImpl implements AirportRepositoryCustom {

    private final JPAQueryFactory query;


    @Override
    public Optional<AirportDto> findByName(String name) {
        return Optional.ofNullable(
                    query.select(constructor(AirportDto.class,
                        airport.iata
                    )).from(airport).where(airport.airportKo.like(name)).fetchOne()
                );
    }
}
