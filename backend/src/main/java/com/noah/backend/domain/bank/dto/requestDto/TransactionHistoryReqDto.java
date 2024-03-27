package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryReqDto {//계좌 거래 내역 조회 조회Dto
	String userKey; //SSAFY금융망에서 발급된 userKey
	String bankCode; //확인할 계좌의 은행 코드
	String accountNo; //확인할 계좌의 계좌번호
	String startDate; //조회 시작 일자
	String endDate; //조회 종료 일자
	String transactionType; //조회 거래 구분(M:입금 D:출금 A: 전체) //일반적으로 transactionType = "A"로 보내면됩니다.
	String orderByType; //정렬 순서(ASC:오름차순, DESC:내림차순,default: 오름차순) //일반적으로 orderByType = "ASC"로 보내면됩니다.
}
