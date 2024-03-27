package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeaderDto { //싸피 금융망 공통 헤더 요청Dto
	@Setter
	private String apiName;
	private String transmissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	private String transmissionTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
	private String institutionCode = "00100";
	private String fintechAppNo = "001";
	@Setter
	private String apiServiceCode; //apiName과 동일
	private String institutionTransactionUniqueNo = transmissionDate + transmissionTime + String.format("%06d", new Random().nextInt(1000000));
	@Setter
	private String apiKey;
	@Setter
	private String userKey;

}
