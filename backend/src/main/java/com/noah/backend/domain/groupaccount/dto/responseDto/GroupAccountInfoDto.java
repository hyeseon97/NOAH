package com.noah.backend.domain.groupaccount.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupAccountInfoDto {
    private Long groupAccountId;    // id
    private String title;           // 여행 제목
    private String bankName;        // 은행 이름
    private String accountNumber;   // 계좌 번호
    private int amount;             // 잔액
    private int usedAmount;         // 사용 금액
    private int targetAmount;       // 목표 금액
    private int targetDate;         // 목표 기간
    private int perAmount;          // 월 납입금
    private int paymentDate;        // 납부일
}
