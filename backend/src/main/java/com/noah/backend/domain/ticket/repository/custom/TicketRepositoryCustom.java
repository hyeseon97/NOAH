package com.noah.backend.domain.ticket.repository.custom;

import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;

import java.util.List;
import java.util.Optional;

public interface TicketRepositoryCustom {

    Optional<List<TicketListGetFromTravelDto>> getTicketList(Long travelId);

    Optional<TicketGetDto> getTicketSelect(Long TicketId);


}
