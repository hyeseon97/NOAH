package com.noah.backend.domain.apis.dto;

import com.noah.backend.domain.apis.entity.Currency;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
public class CurrencyDto {
    private Double buyYuan;
    private Double sellYuan;
    private Double buyEuro;
    private Double sellEuro;
    private Double buyYen;
    private Double sellYen;
    private Double buyDollar;
    private Double sellDollar;

    public CurrencyDto(Currency currency) {
        this.buyYuan = currency.getBuyYuan();
        this.sellYuan = currency.getSellYuan();
        this.buyEuro = currency.getBuyEuro();
        this.sellEuro = currency.getSellEuro();
        this.buyYen = currency.getBuyYen();
        this.sellYen = currency.getSellYen();
        this.buyDollar = currency.getBuyDollar();
        this.sellDollar = currency.getSellDollar();
    }
}
