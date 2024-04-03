package com.noah.backend.domain.ticket.repository.custom;

import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.ticket.entity.QTicket.ticket;
import static com.noah.backend.domain.travel.entity.QTravel.travel;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<TicketListGetFromTravelDto>> getTicketList(Long travelId) {
        List<TicketListGetFromTravelDto> ticketDtos = query
                .select(constructor(TicketListGetFromTravelDto.class,
                        ticket.id,
                        ticket.departure,
                        ticket.arrival,
                        ticket.aAirport,
                        ticket.dAirport,
                        ticket.dGate
//                        ticket.travel.id
                )).from(ticket).leftJoin(ticket.travel).where(ticket.travel.id.eq(travelId)).orderBy(ticket.createdAt.asc()).fetch();
        return Optional.ofNullable(ticketDtos.isEmpty() ? null : ticketDtos);
    }

    @Override
    public Optional<TicketGetDto> getTicketSelect(Long ticketId) {
        TicketGetDto ticketDto = query.select(Projections.constructor(TicketGetDto.class,
                ticket.departure,
                ticket.arrival,
                ticket.dAirport,
                ticket.aAirport,
                ticket.dGate
//                ticket.travel
        )).from(ticket).where(ticket.id.eq(ticketId)).fetchOne();

        return Optional.ofNullable(ticketDto);
    }
}
