package com.noah.backend.domain.ticket.service;

import java.util.List;
import com.noah.backend.domain.ticket.entity.Ticket;

public interface TicketService {

    List<Ticket> getTicketList(Long travelId);

    Ticket getTicketSelect(Long ticketId);

    Long createTicket(Long travelId, Ticket ticket);

    Long updateTicket(Long ticketId, Ticket ticket);

    void deleteTicket(Long ticketId);
}
