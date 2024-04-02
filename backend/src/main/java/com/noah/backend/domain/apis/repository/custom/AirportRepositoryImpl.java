package com.noah.backend.domain.apis.repository.custom;

import static com.noah.backend.domain.apis.entity.QAirport.airport;
import static com.querydsl.core.types.Projections.constructor;

import com.noah.backend.domain.apis.entity.Airport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AirportRepositoryImpl implements AirportRepositoryCustom {

    private final JPAQueryFactory query;


//    @Override
//    public Optional<AirportDto> findByName(String name) {
//        return Optional.ofNullable(
//            query.select(Projections.constructor(AirportDto.class, airport)).from(airport).where(airport.airportKo.like(name)).fetchOne());
//    }
@Override
public Optional<Airport> findByName(String name) {
    return Optional.ofNullable(
        query.selectFrom(airport)
            .where(airport.airportKo.like("%"+name+"%"))
<<<<<<< HEAD
                .limit(1)
=======
            .limit(1)
>>>>>>> 540f63570b9f423246c6421aac88511d3ef84bb9
            .fetchOne()
    );
}
}
