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
    private final TravelRepository travelRepository;

    @Override
    public List<TicketListGetFromTravelDto> getTicketList(Long travelId) {
        return ticketRepository.getTicketList(travelId).orElseThrow(() -> new RuntimeException("여행 ID에 등록된 티켓이 없습니다."));
    }

    @Override
    public TicketGetDto getTicketSelect(Long ticketId) {
        return ticketRepository.getTicketSelect(ticketId).orElseThrow(() -> new RuntimeException("해당 티켓 ID에 대한 정보가 없습니다."));
    }

    @Override
    public Long createTicket(Long travelId, TicketPostDto ticketDto) {
        Travel foundTravel = travelRepository.findById(ticketDto.getTravel_id()).orElseThrow(() -> new RuntimeException("travelID가 입력되지않았어요"));

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
        Ticket currentTicket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("업데이트할 티켓 정보가 없어요"));
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
