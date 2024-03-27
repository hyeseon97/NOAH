package com.noah.backend.domain.apis.dto;

import com.noah.backend.domain.apis.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AirportDto {
    private String iata;
    public AirportDto(String iata) {
        this.iata = iata;
    }

    public static AirportDto transform(Airport airport) {
        return AirportDto.builder()
            .iata(airport.getIata())
            .build();
    }
}
