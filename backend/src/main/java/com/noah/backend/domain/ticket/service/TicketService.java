package com.noah.backend.domain.ticket.service;

import java.util.List;
import com.noah.backend.domain.ticket.entity.Ticket;

public interface TicketService {

    List<Ticket> getTicketList(Long ticketId);

    Long createTicket(String travelId, Ticket ticket);

    Ticket getTicketDetail(Long ticketId);



}
