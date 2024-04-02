package com.noah.backend.domain.apis.controller;

import static com.noah.backend.global.format.response.ErrorCode.DEPARTURE_DATE_ERROR;
import static com.noah.backend.global.format.response.ErrorCode.REQUIRED_FIELD_FAILED;
import static com.noah.backend.global.format.response.ResponseCode.AIRLINE_CODES_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.AIRLINE_ROUTES_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.AIRPORT_RELEVANT_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.AIRPORT_ROUTES_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.FLIGHT_OFFERS_SUCCESS;
import static com.noah.backend.global.format.response.ResponseCode.FLIGHT_PRICE_ANALYSIS_SUCCESS;

import com.noah.backend.domain.apis.dto.AirlineCodeDto;
import com.noah.backend.domain.apis.dto.AirlineRouteDto;
import com.noah.backend.domain.apis.dto.AirportNearestDto;
import com.noah.backend.domain.apis.dto.AirportRouteDto;
import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.apis.dto.FlightOffersDto;
import com.noah.backend.domain.apis.dto.FlightPriceDto;
import com.noah.backend.domain.apis.dto.ResponseFlightOffersDto;
import com.noah.backend.domain.apis.service.FlightService;
import com.noah.backend.domain.apis.service.ForeignCurrencyService;
import com.noah.backend.global.exception.flight.DepartureDateException;
import com.noah.backend.global.exception.flight.RequiredFilledException;
import com.noah.backend.global.format.code.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
@Slf4j
public class ApisController {
    private final ApiResponse apiResponse;
    private final FlightService flightService;
    private final ForeignCurrencyService foreignCurrencyService;
    private String accesstoken = "BgvYFm1UjQPnyKOMdzrG1hZYI8nQ";

    @Scheduled(fixedDelay = 1500000)
    @Operation(summary = "아마데우스 토큰 갱신")
    private void updateAcesstoken() throws IOException, InterruptedException {
        String clientId = "OGMc8pKdyLwOtE5AvQNVQ7SpwWJmYyCi";
        String clientSecret = "KGhPd2Qsy8sG1gs9";
        String url = "https://test.api.amadeus.com/v1/security/oauth2/token";
        String requestBody = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            StringEntity entity = new StringEntity(requestBody);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseBody = EntityUtils.toString(responseEntity);
                JSONObject jsonResponse = new JSONObject(responseBody);
                accesstoken = jsonResponse.getString("access_token");
                System.out.println("success : " + accesstoken);
            } else {
                System.out.println("Empty response entity");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create(url))
//            .header("Content-Type", "application/x-www-form-urlencoded")
//            .POST(BodyPublishers.ofString(requestBody))
//            .build();
//        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//        JSONObject jsonObject = new JSONObject(response);
//        log.info(jsonObject.toString());
//        accesstoken = jsonObject.getString("access_token");
    }

    @GetMapping("flight-offers")
    @Operation(summary = "항공권 탐색")
    public ResponseEntity findFlightOffers(@RequestParam String originLocationCode,
                                            @RequestParam String destinationLocationCode,
                                            @RequestParam String departureDate) throws IOException, InterruptedException {

        FlightOffersDto flightOffersDto = FlightOffersDto.builder()
            .originLocationCode(originLocationCode)
            .destinationLocationCode(destinationLocationCode)
            .departureDate(LocalDate.parse(departureDate))
            .build();

        List<ResponseFlightOffersDto> list;
        try {
            list = flightService.findFlightOffers(accesstoken, flightOffersDto);
        }
        catch (RequiredFilledException e) {
            e.printStackTrace();
            return apiResponse.fail(REQUIRED_FIELD_FAILED);
        }
        catch (DepartureDateException e) {
            e.printStackTrace();
            return apiResponse.fail(DEPARTURE_DATE_ERROR);
        }
        for (ResponseFlightOffersDto dto : list) {
            log.info(dto.toString());
        }
        return apiResponse.success(FLIGHT_OFFERS_SUCCESS, list);
    }

    @Operation(summary = "환율 정보", description = "가장 최신의 환율 정보를 db에서 가져옴")
    @GetMapping("currency/exchage-rate")
    public CurrencyDto getExchangeRate() {
        return foreignCurrencyService.getExchangeRate();
    }

    @GetMapping("/mock/flight-offers")
    public byte[] getMockFlightOffers() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\SSAFY\\projectdir\\S10P22B106\\backend\\src\\main\\java\\com\\noah\\backend\\domain\\apis\\mock\\flight-offers.json");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
//    @GetMapping("flight-offers")
//    public ResponseEntity getFlightOffers(FlightOffersDto flightOffersDto) throws IOException, InterruptedException {
//        // 프론트 dto 임시 제작
//        List<String> a = new ArrayList<>();
//        a.add("T6");
//        a.add("Q5");
//        flightOffersDto =
//            FlightOffersDto
//            .builder()
//                .originLocationCode("SYD")
//                .destinationLocationCode("BKK")
//                .departureDate(LocalDate.of(2024, 5, 2))
//                .returnDate(LocalDate.of(2024, 5, 12))
//                .adults(2)
//                .children(1)
//                .infants(1)
//                .travelClass("ECONOMY")
//                .excludedAirlineCodes(a)
////                .includedAirlineCodes(비워뒀음)
//                .nonStop(true)
//                .currencyCode("KRW")
//                .maxPrice(5000000)
//                .max(250)
//            .build();
//        JSONObject jsonObject;
//        // dto
//        try {
//            jsonObject = flightService.getFlightOffers(accesstoken, flightOffersDto);
//        }
//        catch (RequiredFilledException e) {
//            e.printStackTrace();
//            return apiResponse.fail(REQUIRED_FIELD_FAILED);
//        }
//        log.info("jsonobject: " + jsonObject);
//        return apiResponse.success(FLIGHT_OFFERS_SUCCESS, jsonObject.toString());
//    }

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
