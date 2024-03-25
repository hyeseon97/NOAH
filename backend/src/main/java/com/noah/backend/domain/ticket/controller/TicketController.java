package com.noah.backend.domain.ticket.controller;

import com.noah.backend.domain.ticket.dto.requestDto.TicketPostDto;
import com.noah.backend.domain.ticket.dto.requestDto.TicketUpdateDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import com.noah.backend.domain.ticket.service.TicketService;
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

    @Operation(summary = "티켓 생성",description = "티켓 생성 / TravelId가 필요")
    @PostMapping
    public ResponseEntity<Long> createTicket(@RequestBody TicketPostDto ticketDto){
        Long createTicketId = ticketService.createTicket(ticketDto);

        return ResponseEntity.ok(createTicketId);
    }

    @Operation(summary = "티켓 수정",description = "티켓 수정 / TicketId가 필요")
    @PutMapping("/{ticketId}")
    public ResponseEntity<Long> updateTicket(@PathVariable(value = "ticketId") Long ticketId, @RequestBody TicketUpdateDto ticketDto){
        Long updateTicketId = ticketService.updateTicket(ticketId,ticketDto);

        return ResponseEntity.ok(updateTicketId);
    }

    @Operation(summary = "티켓 삭제",description = "티켓 삭제 / TicketId가 필요")
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Long> deleteTicket(@PathVariable(value = "ticketId") Long ticketId){
        ticketService.deleteTicket(ticketId);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "티켓 목록 조회",description = "티켓 목록 조회 / TravelId가 필요")
    @GetMapping("/list")
    public ResponseEntity<List<TicketListGetFromTravelDto>> getTicketList(@RequestParam(value = "travelId") Long travelId){
        List<TicketListGetFromTravelDto> ticketList = ticketService.getTicketList(travelId);
        return ResponseEntity.ok(ticketList);
    }


    @Operation(summary = "티켓 상세 조회",description = "티켓 상세 조회 / TicketId가 필요")
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketGetDto> getTicketSelect(@PathVariable(value = "ticketId") Long ticketId){
        TicketGetDto selectTicket = ticketService.getTicketSelect(ticketId);
        return ResponseEntity.ok(selectTicket);
    }
}
