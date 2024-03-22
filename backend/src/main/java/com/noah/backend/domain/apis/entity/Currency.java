package com.noah.backend.domain.apis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Currency_id")
    private Long id;
    private Double buyYuan;
    private Double sellYuan;
    private Double buyEuro;
    private Double sellEuro;
    private Double buyYen;
    private Double sellYen;
    private Double buyDollar;
    private Double sellDollar;
    private LocalDateTime createTime;
}
