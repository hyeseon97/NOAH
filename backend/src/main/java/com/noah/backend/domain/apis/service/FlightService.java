package com.noah.backend.domain.apis.service;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {
    public JSONObject getFlightOffers(String acess) {
        String originLocationCode = "ICN";
        String destinationLocationCode = "FUK";
        String departureDate = "2024-09-14";
        String returnDate = "2024-09-18";
        String adults = "2";
        String travelClass = "ECONOMY";
        String nonStop = "true";
        String currencyCode = "KRW";
        String max = "250";

        String url = "https://test.api.amadeus.com/v2/shopping/flight-offers"
            + "?originLocationCode=" + originLocationCode
            + "&destinationLocationCode=" + destinationLocationCode
            + "&departureDate=" + departureDate
            + "&returnDate=" + returnDate
            + "&adults=" + adults
            + "&travleClass=" + travelClass
            + "&nonStop=" + nonStop
            + "&currencyCode=" + currencyCode
            + "&max=" + max;

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/vnd.amadeus+json");
        headers.add("Authorization", "Bearer "+acess);

        WebClient webClient = WebClient.create();
        JSONObject response = webClient.get()
            .uri(url)
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class)
            .map(JSONObject::new)
            .block();
        log.info(response.toString());

        JSONArray jsonArray = response.getJSONArray("data");
        int cnt = 0;
        JSONObject jsonObject = new JSONObject();
        for (Object o : jsonArray) {
            jsonObject.append(cnt++ +"", o);
        }

        return jsonObject;
    }
}
