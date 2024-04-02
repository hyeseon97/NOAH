package com.noah.backend.domain.bank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.bank.dto.requestDto.*;
import com.noah.backend.domain.bank.dto.responseDto.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


public interface BankService {

	//관리자 키 발급(X)
	void adKeyRequest() throws IOException;
	//사용자 계정생성
	MemberCreateResDto memberCreate(MemberCreateReqDto memberCreateReqDto) throws IOException;
	//사용자 계정조회
	MemberCheckResDto memberCheck(MemberCheckReqDto memberCheckReqDto) throws IOException;
	//상품 조회(X)
	void productSelect() throws IOException;
	//계좌 생성
	BankAccountCreateResDto bankAccountCreate(BankAccountCreateReqDto bankAccountCreateReqDto) throws IOException;
	//예금주 조회
	BankHolderCheckResDto bankHolderCheck(BankHolderCheckReqDto bankHolderCheckReqDto) throws IOException;
	//계좌 목록 조회
	ArrayList<BankAccountListResDto> bankAccountList(BankAccountListReqDto bankAccountListReqDto) throws IOException;
	//계좌 잔액 조회
	BankAccountBalanceCheckResDto bankAccountBalanceCheck(BankAccountBalanceCheckReqDto bankAccountBalanceCheckReqDto) throws IOException;
	//계좌 입금
	void bankAccountDeposit(BankAccountDepositReqDto bankAccountDepositReqDto) throws IOException;
	//계좌 출금
	void bankAccountWithdraw(BankAccountWithdrawReqDto bankAccountWithdrawReqDto) throws IOException;
	//계좌 이체
	void bankAccountTransfer(BankAccountTransferReqDto bankAccountTransferReqDto) throws IOException;
	//계좌 거래 내역 조회
	ArrayList<TransactionHistoryResDto> transactionHistory(TransactionHistoryReqDto transactionHistoryReqDto) throws IOException;
	//QR 결제
	void qrWithdraw(QrWithdrawReqDto qrWithdrawReqDto) throws IOException;
}
