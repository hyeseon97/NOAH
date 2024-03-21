package com.noah.backend.domain.bank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.bank.dto.requestDto.memberCreateReqDto;

public interface BankService {

	void adKeyRequest() throws JsonProcessingException;
	memberCreateReqDto memberCreate(memberCreateReqDto memberCreateReqDto) throws JsonProcessingException;

}
