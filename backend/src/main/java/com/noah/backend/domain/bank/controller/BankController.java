package com.noah.backend.domain.bank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountDepositReqDto;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountTransferReqDto;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountWithdrawReqDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/bank")
public class BankController {//계좌 입금, 계좌 출금, 계좌 이체 기능 구현

	private final ApiResponse response;
	private  final BankService bankService;
	//계좌 입금
	@PostMapping("/deposit")
	@Operation(summary = "계좌 입금", description = "계좌 입금")
	public ResponseEntity<?> bankDeposit(@RequestBody BankAccountDepositReqDto bankAccountDepositReqDto) throws JsonProcessingException {
		bankService.bankAccountDeposit(bankAccountDepositReqDto);
		return response.success(ResponseCode.BANK_DEPOSIT_SUCCESS);
	}
	//계좌 출금
	@PostMapping("/withdraw")
	@Operation(summary = "계좌 출금", description = "계좌 출금")
	public ResponseEntity<?> bankWithdraw(@RequestBody BankAccountWithdrawReqDto bankAccountWithdrawReqDto) throws JsonProcessingException {
		bankService.bankAccountWithdraw(bankAccountWithdrawReqDto);
		return response.success(ResponseCode.BANK_WITHDRAW_SUCCESS);
	}
	//계좌 이체
	@PostMapping("/transfer")
	@Operation(summary = "계좌 이체", description = "계좌 이체")
	public ResponseEntity<?> bankTransfer(@RequestBody BankAccountTransferReqDto bankAccountTransferReqDto) throws JsonProcessingException {
		bankService.bankAccountTransfer(bankAccountTransferReqDto);
		return response.success(ResponseCode.BANK_TRANSFER_SUCCESS);
	}
}
