package com.noah.backend.domain.account.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoDto {

    private Long accountId;
    private String bankName;
    private String accountNumber;
    private String type;
    private int amount;

    public AccountInfoDto(Long accountId, String bankName, String accountNumber) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}
