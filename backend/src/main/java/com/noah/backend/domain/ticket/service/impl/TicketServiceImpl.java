package com.noah.backend.domain.ticket.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;

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
