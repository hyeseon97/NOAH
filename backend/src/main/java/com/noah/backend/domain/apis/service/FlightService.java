package com.noah.backend.domain.apis.service;

import com.noah.backend.domain.apis.dto.AirlineRouteDto;
import com.noah.backend.domain.apis.dto.AirportDto;
import com.noah.backend.domain.apis.dto.AirportNearestDto;
import com.noah.backend.domain.apis.dto.AirportRouteDto;
import com.noah.backend.domain.apis.dto.FlightOffersDto;
import com.noah.backend.domain.apis.dto.FlightPriceDto;
import com.noah.backend.domain.apis.entity.Airport;
import com.noah.backend.domain.apis.repository.AirportRepository;
import com.noah.backend.global.exception.flight.AirportNotFoundException;
import com.noah.backend.global.exception.flight.RequiredFilledException;
import com.noah.backend.global.format.response.ErrorCode;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {
    private final AirportRepository airportRepository;

    public JSONObject findFlightOffers(String accessToken, FlightOffersDto dto)
        throws IOException, InterruptedException {
        if (dto.getOriginLocationCode() == null
        || dto.getDestinationLocationCode() == null
        || dto.getDepartureDate() == null
        || dto.getAdults() == null) throw  new RequiredFilledException();

        Airport location = airportRepository.findByName(dto.getOriginLocationCode())
            .orElseThrow(() -> new AirportNotFoundException(ErrorCode.AIRPORT_NOT_FOUND_DEP));
        Airport destinationLocation = airportRepository.findByName(dto.getDestinationLocationCode())
            .orElseThrow(() -> new AirportNotFoundException(ErrorCode.AIRPORT_NOT_FOUND_DES));

        String url = "https://test.api.amadeus.com/v2/shopping/flight-offers"
            + "?originLocationCode=" + location.getIata()
            + "&destinationLocationCode=" + destinationLocation.getIata()
            + "&departureDate=" + dto.getDepartureDate().toString().substring(0, 10)
            + "&adults=" + dto.getAdults()
            + "&nonStop=true"
            + "&currencyCode=KRW"
            + "&max=20";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        return new JSONObject(response.body());

//         json 데이터 조작하는 부분, 나중에 필요하면 수정해서 반영하기
        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        int cnt = 0;
        JSONObject result = new JSONObject();
        for (Object o : jsonArray) {
            result.append(cnt++ +"", o);
        }
        return result;
    }
    public JSONObject getFlightOffers(String accessToken, FlightOffersDto dto)
        throws IOException, InterruptedException {
        if (dto.getOriginLocationCode() == null
        || dto.getDestinationLocationCode() == null
        || dto.getDepartureDate() == null
        || dto.getAdults() == null) throw new RequiredFilledException();
        String url = "https://test.api.amadeus.com/v2/shopping/flight-offers"
            + "?originLocationCode=" + dto.getOriginLocationCode()
            + "&destinationLocationCode=" + dto.getDestinationLocationCode()
            + "&departureDate=" + dto.getDepartureDate().toString().substring(0, 10);
        if (dto.getReturnDate() != null) {
            url += "&returnDate=" + dto.getReturnDate().toString().substring(0, 10);
        }
        url += "&adults=" + dto.getAdults();
        if (dto.getChildren() != null) {
            url += "&children=" + dto.getChildren();
        }
        if (dto.getInfants() != null) {
            url += "&infants=" + dto.getInfants();
        }
        if (dto.getTravelClass() != null) {
            url += "&travelClass=" + dto.getTravelClass();
        }
        if (dto.getIncludedAirlineCodes() != null) {
            url += "&includedAirlineCodes=";
            for (String airline : dto.getIncludedAirlineCodes()) {
                url += airline+"%2C";
            }
            url = url.substring(0, url.length()-3);
        }
        if (dto.getExcludedAirlineCodes() != null) {
            url += "&excludedAirlineCodes=";
            for (String airline : dto.getExcludedAirlineCodes()) {
                url += airline+"%2C";
            }
            url = url.substring(0, url.length()-3);
        }
        if (dto.getNonStop() != null) {
            url += "&nonStop=" + dto.getNonStop();
        }
        if (dto.getCurrencyCode() != null) {
            url += "&currencyCode=" + dto.getCurrencyCode();
        }
        if (dto.getMaxPrice() != null) {
            url += "&maxPrice=" + dto.getMaxPrice();
        }
        if (dto.getMax() != null) {
            url += "&max=" + dto.getMax();
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());

        // json 데이터 조작하는 부분, 나중에 필요하면 수정해서 반영하기
//        JSONObject jsonObject = new JSONObject(response.body());
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        int cnt = 0;
//        JSONObject result = new JSONObject();
//        for (Object o : jsonArray) {
//            result.append(cnt++ +"", o);
//        }
//        return result;
    }

    public JSONObject getFlightPriceAnalysis(String accessToken, FlightPriceDto dto)
        throws IOException, InterruptedException {
        if (dto.getOriginLocationCode() == null
        || dto.getDestinationLocationCode() == null
        || dto.getDepartureDate() == null) throw new RequiredFilledException();
        String url = "https://test.api.amadeus.com/v1/analytics/itinerary-price-metrics"
            + "?originIataCode=" + dto.getOriginLocationCode()
            + "&destinationIataCode=" + dto.getDestinationLocationCode()
            + "&departureDate=" + dto.getDepartureDate();
        if (dto.getCurrencyCode() != null) {
            url += "&currencyCode=" + dto.getCurrencyCode();
        }
        if (dto.getOneWay() != null) {
            url += "&oneWay=" + dto.getOneWay();
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    public JSONObject getAirportNearestRelevant(String accessToken, AirportNearestDto dto)
        throws IOException, InterruptedException {
        if (dto.getLatitude() == null || dto.getLongitude() == null) throw new RequiredFilledException();
        String url = "https://test.api.amadeus.com/v1/reference-data/locations/airports"
            + "?latitude=" + dto.getLatitude()
            + "&longitude=" + dto.getLongitude();
        if (dto.getRadius() != null) {
            url += "&radius=" + dto.getRadius();
        }
        if (dto.getPageLimit() != null) {
            url += "&page%5Blimit%5D=" + dto.getPageLimit();
        }
        if (dto.getPageOffset() != null) {
            url += "&page%5Boffset%5D=" + dto.getPageOffset();
        }
        if (dto.getSort() != null) {
            url += "&sort=" + dto.getSort();
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    public JSONObject getAirportRoutes(String accessToken, AirportRouteDto dto)
        throws IOException, InterruptedException {
        if (dto.getDepartureAirportCode() == null) throw new RequiredFilledException();
        String url = "https://test.api.amadeus.com/v1/airport/direct-destinations"
            + "?departureAirportCode=" + dto.getDepartureAirportCode();

        if (dto.getMax() != null) {
            url += "&max=" + dto.getMax();
        }
        if (dto.getArrivalCountryCode() != null) {
            url += "&arrivalCountryCode=" + dto.getArrivalCountryCode();
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    public JSONObject getAirlineCodes(String accessToken, List<String> code)
        throws IOException, InterruptedException {

        String url = "https://test.api.amadeus.com/v1/reference-data/airlines?airlineCodes=";
        if (code != null) {
            for (String str : code) {
                url += str+"%2C";
            }
            url = url.substring(0, url.length()-3);
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    public JSONObject getAirlineRoutes(String accessToken, AirlineRouteDto dto)
        throws IOException, InterruptedException {
        if (dto.getAirlineCode() == null) throw new RequiredFilledException();

        String url = "https://test.api.amadeus.com/v1/airline/destinations"
            + "?airlineCode=" + dto.getAirlineCode();
        if (dto.getMax() != null) {
            url += "&max=" +dto.getMax();
        }
        if (dto.getArrivalCountryCode() != null) {
            url += "&arrivalCountryCode=" + dto.getArrivalCountryCode();
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("accept", "application/vnd.amadeus+json")
            .header("Authorization", "Bearer "+accessToken)
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}
