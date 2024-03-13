package com.noah.backend.domain.ticket.dto.requestDto;

import java.util.Date;

public class TicketUpdateDto {
    private Long ticket_id;
    private Date departure;
    private Date arrival;
    private String d_airport;
    private String a_airport;
    private int d_gate;

    private Long travel_id;
}
