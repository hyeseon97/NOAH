package com.noah.backend.domain.ticket.dto.requestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketPostDto {
    private Date departure;
    private Date arrival;
    private String d_airport;
    private String a_airport;
    private int d_gate;
}
