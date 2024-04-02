package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrWithdrawReqDto {//qr출금 요청Dto
	Long memberId; //qr요청을 보낸 사용자의 memberId
	Long travelId; //qr요청을 보낸 사용자의 travelId;
	int transactionBalance; //출금 금액
	String transactionSummary; //출금 내용 요약(출금자 명 입력할것.)
	String consumeType; // 소비타입 (=가게타입, 식당, 숙박, 등등)
}
