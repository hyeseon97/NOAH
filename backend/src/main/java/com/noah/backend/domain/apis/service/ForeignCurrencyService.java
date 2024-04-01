package com.noah.backend.domain.apis.service;

import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.apis.entity.Currency;
import com.noah.backend.domain.apis.repository.CurrencyRepository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForeignCurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyDto getExchangeRate() {
        return currencyRepository.getOne();
    }
    @Scheduled(fixedRate = 5000) // 테스트용 5초마다
//    @Scheduled(cron = "0 * * * * *") // 매 분의 0초마다 실행
    @Scheduled(cron = "0 */2 * * * *") // 매 짝수 분에 실행됨
    public void saveExchangeRate() throws IOException, InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String timePart = now.format(timeFormatter);
        int time = Integer.parseInt(timePart);
        if (time < 1100) return;

        DayOfWeek day = now.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) return;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=ch9AVdDckbKrXoBMSufRNLHC2ITeqOgv&data=AP01"))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray jsonArray = new JSONArray(response.body());
        if (jsonArray.isEmpty()) {
            log.info("수출입은행 비영업일");
            return;
        }
        JSONObject yuan = (JSONObject) jsonArray.get(6);
        JSONObject euro = (JSONObject) jsonArray.get(8);
        JSONObject yen = (JSONObject) jsonArray.get(12);
        JSONObject dollar = (JSONObject) jsonArray.get(22);
        Currency currency = Currency
            .builder()
            .buyYuan(Double.parseDouble(yuan.get("ttb").toString().replaceAll(",", "").trim()))
            .sellYuan(Double.parseDouble(yuan.get("tts").toString().replaceAll(",", "").trim()))
            .buyEuro(Double.parseDouble(euro.get("ttb").toString().replaceAll(",", "").trim()))
            .sellEuro(Double.parseDouble(euro.get("tts").toString().replaceAll(",", "").trim()))
            .buyYen(Double.parseDouble(yen.get("ttb").toString().replaceAll(",", "").trim()))
            .sellYen(Double.parseDouble(yen.get("tts").toString().replaceAll(",", "").trim()))
            .buyDollar(Double.parseDouble(dollar.get("ttb").toString().replaceAll(",", "").trim()))
            .sellDollar(Double.parseDouble(dollar.get("tts").toString().replaceAll(",", "").trim()))
            .createTime(LocalDateTime.now())
            .build();
        currencyRepository.save(currency);
    }
}
