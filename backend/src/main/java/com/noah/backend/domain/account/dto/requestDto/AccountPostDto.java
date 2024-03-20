package com.noah.backend.domain.account.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPostDto {

    private Long ownerId;           // 소유주 정보
    private String bank;            // 은행명
    private String accountNumber;   // 계좌번호
}
