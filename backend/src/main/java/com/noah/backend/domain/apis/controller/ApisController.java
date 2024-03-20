package com.noah.backend.domain.apis.controller;

import com.noah.backend.domain.apis.service.FlightService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
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
    private final String acess = "ANm0C8t5BJAdphXwunDoj4h8DAlx";

    @GetMapping("flight-offers")
    public ResponseEntity getFlightOffers() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\flight-offers.json");
        byte[] fileBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fileBytes);
    }
//    @GetMapping("flight-offers")
//    public JSONObject getFlightOffers() {
//        return flightService.getFlightOffers(acess);
//    }

// 22.311492, 114.172291
}
