package com.noah.backend.domain.ticket.controller;

import com.noah.backend.domain.ticket.dto.requestDto.TicketPostDto;
import com.noah.backend.domain.ticket.dto.requestDto.TicketUpdateDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import com.noah.backend.domain.ticket.service.TicketService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ticket 컨트롤러", description = "Ticket Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final ApiResponse response;

    @Operation(summary = "티켓 생성",description = "티켓 생성 / TravelId가 필요")
    @PostMapping
    public ResponseEntity<?> createTicket(@RequestParam Long travelId, @RequestBody TicketPostDto ticketDto){
        Long createTicketId = ticketService.createTicket(travelId, ticketDto);

        return response.success(ResponseCode.TICKET_CREATED, createTicketId);
    }

    @Operation(summary = "티켓 수정",description = "티켓 수정 / TicketId가 필요")
    @PutMapping("/{ticketId}")
    public ResponseEntity<?> updateTicket(@PathVariable(value = "ticketId") Long ticketId, @RequestBody TicketUpdateDto ticketDto){
        Long updateTicketId = ticketService.updateTicket(ticketId,ticketDto);

        return response.success(ResponseCode.TICKET_UPDATED, updateTicketId);
    }

    @Operation(summary = "티켓 삭제",description = "티켓 삭제 / TicketId가 필요")
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable(value = "ticketId") Long ticketId){
        ticketService.deleteTicket(ticketId);

        return response.success(ResponseCode.TICKET_DELETED);
    }

    @Operation(summary = "티켓 목록 조회",description = "티켓 목록 조회 / TravelId가 필요")
    @GetMapping("/list")
    public ResponseEntity<?> getTicketList(@RequestParam(value = "travelId") Long travelId){
        List<TicketListGetFromTravelDto> ticketList = ticketService.getTicketList(travelId);
        return response.success(ResponseCode.TICKET_FETCHED, ticketList);
    }


    @Operation(summary = "티켓 상세 조회",description = "티켓 상세 조회 / TicketId가 필요")
    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getTicketSelect(@PathVariable(value = "ticketId") Long ticketId){
        TicketGetDto selectTicket = ticketService.getTicketSelect(ticketId);
        return response.success(ResponseCode.TICKET_FETCHED, selectTicket);
    }
}
