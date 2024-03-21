package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderCheckDto {
	String bankCode;
	String bankName;
	String accountNo;
	String userName;

}
