package com.noah.backend.domain.apis.dto;

import com.noah.backend.domain.apis.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {
    private String iata;
    public AirportDto(Airport airport) {
        this.iata = airport.getIata();
    }
}
