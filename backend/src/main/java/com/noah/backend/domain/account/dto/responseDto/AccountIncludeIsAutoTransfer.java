package com.noah.backend.domain.account.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountIncludeIsAutoTransfer {

    private Long accountId;
    private String bankName;
    private String accountNumber;
    private int amount;
    private boolean isAutoTransfer;

    public AccountIncludeIsAutoTransfer(Long accountId, String bankName, String accountNumber, int amount) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.isAutoTransfer = false;
    }
}
