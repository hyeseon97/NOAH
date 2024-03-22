package com.noah.backend.domain.account.dto.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPostDto {
    private Long memberId;
    private Long travelId;
    private String bankName;
    private String accountNumber;
}
