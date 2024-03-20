package com.noah.backend.domain.apis.controller;

import com.noah.backend.domain.apis.service.FlightService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
@Slf4j
public class ApisController {
    private final FlightService flightService;
    private static final String ACESSTOKEN = "VcKGmr3DmsOu4eZC8cczuQiAikqG";

    @GetMapping("/mock/flight-offers")
    public ResponseEntity<byte[]> getMockFlightOffers() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\flight-offers.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }
    @GetMapping("flight-offers")
    public JSONObject getFlightOffers() {
        return flightService.getFlightOffers(ACESSTOKEN);
    }

    @GetMapping("/mock/flight-price-analysis")
    public ResponseEntity<byte[]> getMockFlightPriceAnalysis() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\flight-price-analysis.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }

    @GetMapping("/mock/airport-nearest-relevant")
    public ResponseEntity<byte[]> getMockAirportNearestRelevant() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airport-nearest-relevant.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }


    @GetMapping("/mock/airport-routes")
    public ResponseEntity<byte[]> getMockAirportRoute() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airport-routes.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }


    @GetMapping("/mock/airline-codes")
    public ResponseEntity<byte[]> getMockAirlineCode() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airline-code.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }


    @GetMapping("/mock/airline-routes")
    public ResponseEntity<byte[]> getMockAirlineRoute() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\airline-routes.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }

// 22.311492, 114.172291
}
