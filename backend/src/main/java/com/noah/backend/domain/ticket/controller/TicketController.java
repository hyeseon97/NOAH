package com.noah.backend.domain.ticket.controller;

import com.noah.backend.domain.ticket.entity.Ticket;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Ticket 컨트롤러", description = "Ticket Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Ticket")
public class TicketController {


}
