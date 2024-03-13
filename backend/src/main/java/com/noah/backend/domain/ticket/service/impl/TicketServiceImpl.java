package com.noah.backend.domain.ticket.service.impl;

import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import com.noah.backend.domain.ticket.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    @Override
    public List<TicketListGetFromTravelDto> getTicketList(Long travelId) {
        return null;
    }

    @Override
    public TicketGetDto getTicketSelect(Long ticketId) {
        return null;
    }

    @Override
    public Long createTicket(Long travelId, Ticket ticket) {
        return null;
    }

    @Override
    public Long updateTicket(Long ticketId, Ticket ticket) {
        return null;
    }

    @Override
    public void deleteTicket(Long ticketId) {

    }
}
