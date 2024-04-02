package com.noah.backend.domain.bank.controller;

import com.noah.backend.domain.bank.dto.requestDto.*;
import com.noah.backend.domain.bank.dto.responseDto.BankAccountBalanceCheckResDto;
import com.noah.backend.domain.bank.dto.responseDto.BankAccountListResDto;
import com.noah.backend.domain.bank.dto.responseDto.TransactionHistoryResDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.csv.service.impl.CsvServiceImpl;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank")
public class BankController {//계좌 입금, 계좌 출금, 계좌 이체 기능 구현

	private final ApiResponse response;
	private final BankService bankService;
	//계좌 목록 조회
	@PostMapping("/bankAccountList")
	@Operation(summary = "계좌 목록 조회", description = "계좌 목록 조회")
	public ResponseEntity<?> bankAccountList(@RequestBody BankAccountListReqDto bankAccountListReqDto) throws IOException, CsvValidationException, ParseException {
		ArrayList<BankAccountListResDto> bankAccountList= bankService.bankAccountList(bankAccountListReqDto);
		return response.success(ResponseCode.BANK_DEPOSIT_SUCCESS,bankAccountList);
	}

	//계좌 잔액 조회
	@PostMapping("/balanceCheck")
	@Operation(summary = "계좌 잔액 조회", description = "계좌 잔액 조회")
	public ResponseEntity<?> bankAccountBalanceCheck(@RequestBody BankAccountBalanceCheckReqDto bankAccountBalanceCheckReqDto) throws IOException {
		BankAccountBalanceCheckResDto bankAccountBalanceCheckResDto = bankService.bankAccountBalanceCheck(bankAccountBalanceCheckReqDto);
		return response.success(ResponseCode.BANK_DEPOSIT_SUCCESS,bankAccountBalanceCheckResDto);
	}

	//계좌 거래내역 조회
	@PostMapping("/bankAccountTransactionHistory")
	@Operation(summary = "계좌 거래내역 조회", description = "계좌 거래내역 조회")
	public ResponseEntity<?> transactionHistory(@RequestBody TransactionHistoryReqDto transactionHistoryReqDto) throws IOException {
		ArrayList<TransactionHistoryResDto> list = bankService.transactionHistory(transactionHistoryReqDto);
		return response.success(ResponseCode.TRANSACTION_HISTORY_SUCCESS,list);
	}

	//계좌 입금
	@PostMapping("/deposit")
	@Operation(summary = "계좌 입금", description = "계좌 입금")
	public ResponseEntity<?> bankDeposit(@RequestBody BankAccountDepositReqDto bankAccountDepositReqDto) throws IOException {
		bankService.bankAccountDeposit(bankAccountDepositReqDto);
		return response.success(ResponseCode.BANK_DEPOSIT_SUCCESS);
	}
	//계좌 출금
	@PostMapping("/withdraw")
	@Operation(summary = "계좌 출금", description = "계좌 출금")
	public ResponseEntity<?> bankWithdraw(@RequestBody BankAccountWithdrawReqDto bankAccountWithdrawReqDto) throws IOException {
		bankService.bankAccountWithdraw(bankAccountWithdrawReqDto);
		return response.success(ResponseCode.BANK_WITHDRAW_SUCCESS);
	}
	//계좌 이체
	@PostMapping("/transfer")
	@Operation(summary = "계좌 이체", description = "계좌 이체")
	public ResponseEntity<?> bankTransfer(@RequestBody BankAccountTransferReqDto bankAccountTransferReqDto) throws IOException {
		bankService.bankAccountTransfer(bankAccountTransferReqDto);
		return response.success(ResponseCode.BANK_TRANSFER_SUCCESS);
	}

	//QR결제
	@PostMapping("/qr/withdraw")
	@Operation(summary = "QR 결제", description = "QR 결제")
	public ResponseEntity<?> qrWithdraw(@RequestBody QrWithdrawReqDto qrWithdrawReqDto) throws IOException {
		bankService.qrWithdraw(qrWithdrawReqDto);
		return response.success(ResponseCode.QR_WITHDRAW_SUCCESS);
	}

}
