package com.noah.backend.domain.ticket.repository.custom;

import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepositoryCustom{

    private final JPAQueryFactory query;
    @Override
    public Optional<List<TicketListGetFromTravelDto>> getTicketList(Long travelId) {
        return Optional.empty();
    }

    @Override
    public Optional<TicketGetDto> getTicketSelect(Long TicketId) {
        return Optional.empty();
    }
}
