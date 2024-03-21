package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPostDto {

    private String name;        // 계좌 이름
    private int targetAmount;   // 목표 금액
    private int perAmount;      // 납입금
    private int paymentDate;    // 납입일

}
