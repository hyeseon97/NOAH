package com.noah.backend.domain.account.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfoDto {
    private Long id;            // 아이디
    private String name;        // 통장 이름
    private String ownerName;   // 소유주
    private String bank;        // 은행
    private String accountNumber;   // 계좌번호
    private int amount;        // 현재 금액
    private int usedAmount;       // 사용 금액
    private int targetAmount;    // 목표금액
    private int perAmount;       // 월 납입금
    private int paymentDate;     // 납부일
}
