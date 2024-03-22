package com.noah.backend.domain.bank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountListReqDto;
import com.noah.backend.domain.bank.dto.requestDto.RequestHeaderDto;
import com.noah.backend.domain.bank.dto.responseDto.BankAccountListResDto;
import com.noah.backend.domain.bank.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class BankController {

	private final BankService bankService;

	//회원이 있다고 가정
	@GetMapping("/test/account")
	@Operation(summary = "계좌 생성", description = "유저 계좌 생성")
	public ResponseEntity<?> createAccount(String email){

		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		//email로 userKey를 얻었다고 가정
		requestHeaderDto.setApiName("openAccount");
		requestHeaderDto.setUserKey("유저userKey필요");
		return new ResponseEntity<>(requestHeaderDto, HttpStatus.OK);
	}

	@Operation
	@GetMapping("/all")
	public ArrayList<BankAccountListResDto> getAllAccount(@RequestBody BankAccountListReqDto bankAccountListReqDto) throws JsonProcessingException {
		ArrayList<BankAccountListResDto> result = bankService.bankAccountList(bankAccountListReqDto);
		return result;
	}
}
