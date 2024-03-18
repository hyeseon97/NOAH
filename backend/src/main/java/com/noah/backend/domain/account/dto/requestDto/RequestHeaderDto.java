package com.noah.backend.domain.account.dto.requestDto;

import lombok.*;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
public class RequestHeaderDto {
	@Getter @Setter
	private String apiName;
	private String transmissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	private String transmissionTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
	private String institutionCode = "00100";
	private String fintechAppNo = "001";
	private String apiServiceCode = apiName;
	private String institutionTransactionUniqueNo = transmissionDate + transmissionTime + String.format("%06d", new Random().nextInt(1000000));
	private String apiKey = "관리자apiKey 필요";
	@Getter @Setter
	private String userKey;

}
