package com.noah.backend.domain.ticket.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.dto.requestDto.TicketPostDto;
import com.noah.backend.domain.ticket.dto.requestDto.TicketUpdateDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.ticket.service.TicketService;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.ticket.TicketNotFound;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final TravelRepository travelRepository;

    @Override
    public List<TicketListGetFromTravelDto> getTicketList(Long travelId) {
        return ticketRepository.getTicketList(travelId).orElseThrow(TicketNotFound::new);
    }

    @Override
    public TicketGetDto getTicketSelect(Long ticketId) {
        return ticketRepository.getTicketSelect(ticketId).orElseThrow(TicketNotFound::new);
    }

    @Override
    public Long createTicket(Long travelId, TicketPostDto ticketDto) {
        Travel foundTravel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);

        Ticket ticket = Ticket.builder()
                .departure(ticketDto.getDeparture())
                .arrival(ticketDto.getArrival())
                .dAirport(ticketDto.getD_airport())
                .aAirport(ticketDto.getA_airport())
                .dGate(ticketDto.getD_gate())
                .travel(foundTravel)
                .build();
        return ticketRepository.save(ticket).getId();
    }

    @Override
    public Long updateTicket(Long ticketId, TicketUpdateDto ticketDto) {
        Ticket currentTicket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFound::new);
        currentTicket.setDeparture(ticketDto.getDeparture());
        currentTicket.setArrival(ticketDto.getArrival());
        currentTicket.setDAirport(ticketDto.getD_airport());
        currentTicket.setAAirport(ticketDto.getA_airport());
        currentTicket.setDGate(ticketDto.getD_gate());

        return ticketRepository.save(currentTicket).getId();
    }

    @Override
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
