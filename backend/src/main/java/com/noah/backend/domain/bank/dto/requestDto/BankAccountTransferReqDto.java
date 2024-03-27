package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountTransferReqDto {//계좌 이체 요청Dto
	String userKey; //SSAFY금융망에서 발급된 userKey
	String depositBankCode; //이체할 계좌의 은행코드
	String depositAccountNo; //이체할 계좌의 계좌번호
	int transactionBalance; //이체 금액
	String withdrawalBankCode; //이체받을 계좌의 은행코드
	String withdrawalAccountNo; //이체받을 계좌의 계좌번호
	String depositTransactionSummary; //이체한 사람에게 표시되는 내용 요약(출금자 명 입력할것.)
	String withdrawalTransactionSummary; //이체받는 사람에게 표시되는 내용 요약(입금자 명 입력할것.)

	@Override
	public String toString() {
		return "BankAccountTransferReqDto{" +
				"userKey='" + userKey + '\'' +
				", depositBankCode='" + depositBankCode + '\'' +
				", depositAccountNo='" + depositAccountNo + '\'' +
				", transactionBalance='" + transactionBalance + '\'' +
				", withdrawalBankCode='" + withdrawalBankCode + '\'' +
				", withdrawalAccountNo='" + withdrawalAccountNo + '\'' +
				", depositTransactionSummary='" + depositTransactionSummary + '\'' +
				", withdrawalTransactionSummary='" + withdrawalTransactionSummary + '\'' +
				'}';
	}
}
