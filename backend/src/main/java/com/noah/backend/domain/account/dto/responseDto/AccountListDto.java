package com.noah.backend.domain.account.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountListDto {
	String bankCode;
	String bankName;
	String username;
	String accountNo;
	int accountBalance;
}
