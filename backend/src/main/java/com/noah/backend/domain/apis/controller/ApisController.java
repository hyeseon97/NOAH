package com.noah.backend.domain.apis.controller;

import static com.noah.backend.global.format.response.ErrorCode.REQUIRED_FIELD_FAILED;
import static com.noah.backend.global.format.response.ResponseCode.AIRLINE_CODES_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.AIRLINE_ROUTES_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.AIRPORT_RELEVANT_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.AIRPORT_ROUTES_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.FLIGHT_OFFERS_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.FLIGHT_PRICE_ANALYSIS_SUCCESS;

import com.noah.backend.domain.apis.dto.AirlineCodeDto;
import com.noah.backend.domain.apis.dto.AirlineDto;
import com.noah.backend.domain.apis.dto.AirlineRouteDto;
import com.noah.backend.domain.apis.dto.AirportNearestDto;
import com.noah.backend.domain.apis.dto.AirportRouteDto;
import com.noah.backend.domain.apis.dto.FlightOffersDto;
import com.noah.backend.domain.apis.dto.FlightPriceDto;
import com.noah.backend.domain.apis.service.FlightService;
import com.noah.backend.global.exception.flight.RequiredFilledException;
import com.noah.backend.global.format.code.ApiResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
@Slf4j
public class ApisController {
    private final ApiResponse apiResponse;
    private final FlightService flightService;
    private String accesstoken = "GlwKzrVNzci18GaWU8SClQ71bc8K";

//    @Scheduled(fixedDelay = 1500000)
//    private void updateAcesstoken() {
//        accesstoken = "a";
//    }

    @GetMapping("/mock/flight-offers")
    public byte[] getMockFlightOffers() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\flight-offers.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
    @GetMapping("flight-offers")
    public ResponseEntity getFlightOffers(FlightOffersDto flightOffersDto) throws IOException, InterruptedException {
        // 프론트 dto 임시 제작
        List<String> a = new ArrayList<>();
        a.add("T6");
        a.add("Q5");
        flightOffersDto =
            FlightOffersDto
            .builder()
                .originLocationCode("SYD")
                .destinationLocationCode("BKK")
                .departureDate(LocalDate.of(2024, 5, 2))
                .returnDate(LocalDate.of(2024, 5, 12))
                .adults(2)
                .children(1)
                .infants(1)
                .travelClass("ECONOMY")
                .excludedAirlineCodes(a)
//                .includedAirlineCodes(비워뒀음)
                .nonStop(true)
                .currencyCode("KRW")
                .maxPrice(5000000)
                .max(250)
            .build();
        JSONObject jsonObject;
        // dto
        try {
            jsonObject = flightService.getFlightOffers(accesstoken, flightOffersDto);
        }
        catch (RequiredFilledException e) {
            e.printStackTrace();
            return apiResponse.fail(REQUIRED_FIELD_FAILED);
        }
        log.info("jsonobject: " + jsonObject);
        return apiResponse.success(FLIGHT_OFFERS_SUCCESS, jsonObject.toString());
    }

    @GetMapping("/mock/flight-price-analysis")
    public byte[] getMockFlightPriceAnalysis() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\flight-price-analysis.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
    @GetMapping("/flight-price-analysis")
    public ResponseEntity getFlightPriceAnalysis(FlightPriceDto flightPriceDto)
        throws IOException, InterruptedException {
        //
        flightPriceDto = FlightPriceDto.builder()
                .originLocationCode("ICN")
                .destinationLocationCode("FUK")
                .departureDate(LocalDate.of(2024, 9, 14))
                .currencyCode("KRW")
                .oneWay(false)
                .build();
        //
        JSONObject jsonObject;
        try {
            jsonObject = flightService.getFlightPriceAnalysis(accesstoken, flightPriceDto);
        }
        catch (RequiredFilledException e) {
            e.printStackTrace();
            return apiResponse.fail(REQUIRED_FIELD_FAILED);
        }
        return apiResponse.success(FLIGHT_PRICE_ANALYSIS_SUCCESS, jsonObject.toString());
    }

    @GetMapping("/mock/airport-nearest-relevant")
    public byte[] getMockAirportNearestRelevant() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airport-nearest-relevant.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
    @GetMapping("/airport-nearest-relevant")
    public ResponseEntity getAirportNearestRelevant(AirportNearestDto airportNearestDto)
        throws IOException, InterruptedException {
        //
        airportNearestDto = AirportNearestDto.builder()
            .latitude(51.57285)
            .longitude(-0.44161)
            .radius(500)
            .pageLimit(500)
            .pageOffset(0)
            .sort("relevance")
            .build();
        //
        JSONObject jsonObject;
        try {
            jsonObject = flightService.getAirportNearestRelevant(accesstoken, airportNearestDto);
        }
        catch (RequiredFilledException e) {
            e.printStackTrace();
            return apiResponse.fail(REQUIRED_FIELD_FAILED);
        }
        return apiResponse.success(AIRPORT_RELEVANT_SUCCESS, jsonObject.toString());
    }

    @GetMapping("/mock/airport-routes")
    public byte[] getMockAirportRoute() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airport-routes.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
    @GetMapping("/airport-routes")
    public ResponseEntity getAirportRoutes(AirportRouteDto airportRouteDto)
        throws IOException, InterruptedException {
        //
        airportRouteDto = AirportRouteDto
            .builder()
            .departureAirportCode("ICN")
            .max(20)
            .arrivalCountryCode("JP")
            .build();
        //
        JSONObject jsonObject;
        try {
            jsonObject = flightService.getAirportRoutes(accesstoken, airportRouteDto);
        }
        catch (RequiredFilledException e) {
            e.printStackTrace();
            return apiResponse.fail(REQUIRED_FIELD_FAILED);
        }
        return apiResponse.success(AIRPORT_ROUTES_SUCCESS, jsonObject.toString());
    }

    @GetMapping("/mock/airline-codes")
    public byte[] getMockAirlineCode() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airline-code.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
    @GetMapping("/airline-codes")
    public ResponseEntity getAirlineCodes(AirlineCodeDto airlineCodeDto)
        throws IOException, InterruptedException {
        //
        List<String> list = new ArrayList<>();
        list.add("4R");
        list.add("BA");
        //
        return apiResponse.success(AIRLINE_CODES_SUCCESS, flightService.getAirlineCodes(accesstoken,
            list).toString());
    }

    @GetMapping("/mock/airline-routes")
    public byte[] getMockAirlineRoute() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airline-routes.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
    @GetMapping("/airline-routes")
    public ResponseEntity getAirlineRoutes(AirlineRouteDto airlineRouteDto)
        throws IOException, InterruptedException {
        //
        airlineRouteDto =
            AirlineRouteDto.builder()
                .airlineCode("BA")
                .max(20)
                .arrivalCountryCode("JP")
                .build();
        //
        JSONObject jsonObject;
        try {
            jsonObject = flightService.getAirlineRoutes(accesstoken, airlineRouteDto);
        }
        catch (RequiredFilledException e) {
            e.printStackTrace();
            return apiResponse.fail(REQUIRED_FIELD_FAILED);
        }
        return apiResponse.success(AIRLINE_ROUTES_SUCCESS, jsonObject.toString());
    }

// 22.311492, 114.172291
}
