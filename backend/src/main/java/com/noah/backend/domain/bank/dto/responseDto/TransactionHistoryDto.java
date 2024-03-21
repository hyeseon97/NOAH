package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDto { //계좌 거래 내역 조회 응답Dto
	int type; //타입(1.입금 2.출금)
	String name; //거래내용 요약
	String date; //거래 날짜
	String time; //거래 일시
	int cost; //거래 금액
	int amount; //거래 후 잔액
}
