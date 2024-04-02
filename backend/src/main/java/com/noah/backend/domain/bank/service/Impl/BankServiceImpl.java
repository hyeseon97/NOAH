package com.noah.backend.domain.bank.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.admin.dto.requestDto.AdminKeyRequestDto;
import com.noah.backend.domain.bank.dto.requestDto.*;
import com.noah.backend.domain.bank.dto.responseDto.*;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.groupaccount.repository.GroupAccountRepository;
import com.noah.backend.domain.member.dto.requestDto.UserKeyRequestDto;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.bank.*;
import com.noah.backend.global.exception.groupaccount.GroupAccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class BankServiceImpl implements BankService {
	static String adminEmail = "dldnwls009@ssafy.co.kr";
	static String adminKey = "2971f57e01a54fd0a91161ec5c59c3cd";
	static ObjectMapper objectMapper = new ObjectMapper();

	private final MemberRepository memberRepository;
	private final TravelRepository travelRepository;
	private final GroupAccountRepository groupAccountRepository;


	//관리자 키 발급
	@Override
	public void adKeyRequest() throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/app/issuedApiKey";
		Map result = objectMapper.convertValue(new AdminKeyRequestDto(), Map.class);
		String jsonMessage = objectMapper.writeValueAsString(result);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
			System.out.println(result);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(jsonMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
			} else {
				errorCheck(response);
			}

	}

	//사용자 계정생성
	@Override
	public MemberCreateResDto memberCreate(MemberCreateReqDto memberCreateReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/member/";
		UserKeyRequestDto userKeyRequestDto = new UserKeyRequestDto();
		userKeyRequestDto.setApiKey(adminKey);
		userKeyRequestDto.setUserId(memberCreateReqDto.getEmail());
		Map result = objectMapper.convertValue(userKeyRequestDto, Map.class);
		String jsonMessage = objectMapper.writeValueAsString(result);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
//			System.out.println(result);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(jsonMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> payload = (Map<String, Object>) responseJson.get("payload");
				System.out.println("유저 키 발급완료 SUCCESS : " + payload.get("userKey"));
				MemberCreateResDto memberCreateResDto = new MemberCreateResDto();
				memberCreateResDto.setUserKey((String) payload.get("userKey"));
				return memberCreateResDto;
			} else {
				//오류가 발생하면 이미 존재하는 사용자입니다.(회원가입 불가) null값 대신 나중에 예외처리로 변경
				errorCheck(response);
				return null;
			}

	}

	//사용자 계정조회
	@Override
	public MemberCheckResDto memberCheck(MemberCheckReqDto memberCheckReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/member/search";
		UserKeyRequestDto userKeyRequestDto = new UserKeyRequestDto();
		userKeyRequestDto.setApiKey(adminKey);
		userKeyRequestDto.setUserId(memberCheckReqDto.getEmail());
		Map result = objectMapper.convertValue(userKeyRequestDto, Map.class);
		String jsonMessage = objectMapper.writeValueAsString(result);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
//			System.out.println(result);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(jsonMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> payload = (Map<String, Object>) responseJson.get("payload");
//				System.out.println("유저 키 발급완료 SUCCESS : " + payload.get("userKey"));
				MemberCheckResDto memberCheckResDto = new MemberCheckResDto();
				memberCheckResDto.setUserKey((String) payload.get("userKey"));
				return memberCheckResDto;
			} else {
				//오류가 발생하면 이미 존재하는 사용자입니다.(회원가입 불가) null값 대신 나중에 예외처리로 변경
				errorCheck(response);
				return null;
			}

	}

	//상품 조회
	@Override
	public void productSelect() throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireBankAccountTypes";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireBankAccountTypes");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(headerMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
			} else {
				errorCheck(response);
			}

	}

	//계좌 생성
	@Override
	public BankAccountCreateResDto bankAccountCreate(BankAccountCreateReqDto bankAccountCreateReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/openAccount";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("openAccount");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(bankAccountCreateReqDto.getUserKey());
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = selectBank(bankAccountCreateReqDto.getBankType());
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				BankAccountCreateResDto bankAccountCreateResDto = new BankAccountCreateResDto();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("REC");
				bankAccountCreateResDto.setBankName(bankName((String)REC.get("bankCode")));
				bankAccountCreateResDto.setAccountNumber((String)REC.get("accountNo"));
				return bankAccountCreateResDto;
			} else {
				errorCheck(response);
				return null;
			}

	}

	//예금주 조회
	@Override
	public BankHolderCheckResDto bankHolderCheck(BankHolderCheckReqDto bankHolderCheckReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireDepositorAccountNumber";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireDepositorAccountNumber");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		String userKey = "0c00c0f6-b093-4d4b-bce3-c7730567584e"; //어차피 의미없어서 dldnwls12test의 userKey로 해놓음
		requestHeaderDto.setUserKey(userKey);
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankHolderCheckReqDto.getBankCode());
		bodyHm.put("accountNo",bankHolderCheckReqDto.getAccountNo());
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				BankHolderCheckResDto bankHolderCheckResDto = new BankHolderCheckResDto();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("REC");
				bankHolderCheckResDto.setBankCode((String)REC.get("bankCode"));
				bankHolderCheckResDto.setBankName(bankName((String)REC.get("bankCode")));
				bankHolderCheckResDto.setAccountNo((String)REC.get("accountNo"));
				bankHolderCheckResDto.setUserName((String)REC.get("userName"));
				return bankHolderCheckResDto;
			} else {
				errorCheck(response);
				return null;
			}

	}

	//계좌 목록 조회
	@Override
	public ArrayList<BankAccountListResDto> bankAccountList(BankAccountListReqDto bankAccountListReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireAccountList";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireAccountList");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(bankAccountListReqDto.getUserKey());
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(headerMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				BankAccountCreateResDto bankAccountCreateResDto = new BankAccountCreateResDto();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				ArrayList<HashMap<String,String>> REC = (ArrayList<HashMap<String,String>>) responseJson.get("REC");
//				RECView(REC);
				ArrayList<BankAccountListResDto> RECextractionList = RECextraction(REC);
//				RECextractionListView(RECextractionList);
				return RECextractionList;
			} else {
				errorCheck(response);
				return null;
			}

	}

	//계좌 잔액 조회
	@Override
	public BankAccountBalanceCheckResDto bankAccountBalanceCheck(BankAccountBalanceCheckReqDto bankAccountBalanceCheckReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireAccountBalance";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireAccountBalance");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(bankAccountBalanceCheckReqDto.getUserKey()); //확인하려는 계좌의 주인의 유저키가 필요
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankAccountBalanceCheckReqDto.getBankCode());
		bodyHm.put("accountNo",bankAccountBalanceCheckReqDto.getAccountNo());
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

		HttpResponse response = null;

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("REC");
				BankAccountBalanceCheckResDto bankAccountBalanceCheckResDto = new BankAccountBalanceCheckResDto();
				bankAccountBalanceCheckResDto.setAccountBalance(Integer.parseInt((String)REC.get("accountBalance")));
				return bankAccountBalanceCheckResDto;
			} else {
				errorCheck(response);
				return null; //오류나면 null
			}

	}

	//계좌 입금
	@Override
	public void bankAccountDeposit(BankAccountDepositReqDto bankAccountDepositReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/receivedTransferAccountNumber";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("receivedTransferAccountNumber");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(bankAccountDepositReqDto.getUserKey()); //확인하려는 계좌의 주인의 유저키가 필요
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankAccountDepositReqDto.getBankCode()); //은행 코드
		bodyHm.put("accountNo",bankAccountDepositReqDto.getAccountNo()); //계좌 번호
		bodyHm.put("transactionBalance",bankAccountDepositReqDto.getTransactionBalance()); //입금 금액
		bodyHm.put("transactionSummary",bankAccountDepositReqDto.getTransactionSummary()); //입금 계좌 요약
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("Header");
				System.out.println("계좌 입금 제대로되는지 확인");
				System.out.println("처리 결과 : " + (String)REC.get("responseMessage"));
			} else {
				errorCheck(response);
			}

	}

	//계좌 출금
	@Override
	public void bankAccountWithdraw(BankAccountWithdrawReqDto bankAccountWithdrawReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/drawingTransfer";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("drawingTransfer");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(bankAccountWithdrawReqDto.getUserKey()); //확인하려는 계좌의 주인의 유저키가 필요
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankAccountWithdrawReqDto.getBankCode()); //은행 코드
		bodyHm.put("accountNo",bankAccountWithdrawReqDto.getAccountNo()); //계좌 번호
		bodyHm.put("transactionBalance",bankAccountWithdrawReqDto.getTransactionBalance()); //출금 금액
		bodyHm.put("transactionSummary",bankAccountWithdrawReqDto.getTransactionSummary()); //출금 계좌 요약
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("Header");
				System.out.println("계좌 출금 제대로되는지 확인");
				System.out.println("처리 결과 : " + (String)REC.get("responseMessage"));
			} else {
				errorCheck(response);
			}

	}

	//계좌 이체
	@Override
	public void bankAccountTransfer(BankAccountTransferReqDto bankAccountTransferReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/accountTransfer";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("accountTransfer");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(bankAccountTransferReqDto.getUserKey()); //확인하려는 계좌의 주인의 유저키가 필요
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("depositBankCode",bankAccountTransferReqDto.getDepositBankCode()); //입금계좌은행코드
		bodyHm.put("depositAccountNo",bankAccountTransferReqDto.getDepositAccountNo()); //입금계좌번호
		bodyHm.put("transactionBalance",bankAccountTransferReqDto.getTransactionBalance()); //거래금액
		bodyHm.put("withdrawalBankCode",bankAccountTransferReqDto.getWithdrawalBankCode()); //출금계좌은행코드
		bodyHm.put("withdrawalAccountNo",bankAccountTransferReqDto.getWithdrawalAccountNo()); //출금계좌번호
		bodyHm.put("depositTransactionSummary",bankAccountTransferReqDto.getDepositTransactionSummary()); //거래 요약내용(입금계좌)
		bodyHm.put("withdrawalTransactionSummary",bankAccountTransferReqDto.getWithdrawalTransactionSummary()); //거래 요약내용(출금계좌)
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("Header");
				System.out.println("계좌 이체 제대로되는지 확인");
				System.out.println("처리 결과 : " + (String)REC.get("responseMessage"));
			} else {
				errorCheck(response);
			}

	}

	//계좌 거래 내역 조회
	@Override
	public ArrayList<TransactionHistoryResDto> transactionHistory(TransactionHistoryReqDto transactionHistoryReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireAccountTransactionHistory";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireAccountTransactionHistory");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(transactionHistoryReqDto.getUserKey()); //확인하려는 계좌의 주인의 유저키가 필요
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",transactionHistoryReqDto.getBankCode()); //은행코드
		bodyHm.put("accountNo",transactionHistoryReqDto.getAccountNo()); //계좌번호
		bodyHm.put("startDate",transactionHistoryReqDto.getStartDate()); //조회시작일자
		bodyHm.put("endDate",transactionHistoryReqDto.getEndDate()); //조회종료일자
		bodyHm.put("transactionType",transactionHistoryReqDto.getTransactionType()); //거래구분
		bodyHm.put("orderByType",transactionHistoryReqDto.getOrderByType()); //정렬순서
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
//				System.out.println(body);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				HashMap<String,ArrayList<HashMap<String,String>>> REC = (HashMap<String, ArrayList<HashMap<String, String>>>) responseJson.get("REC");
				ArrayList<HashMap<String,String>> REClist = REC.get("list");
//				RECView(REClist);
				ArrayList<TransactionHistoryResDto> RECextractionList = RECextractionTransactionHistory(REClist);
				return RECextractionList;
//				System.out.println("계좌거래내역조회 제대로되는지 확인");
//				System.out.println("처리 결과 : " + (String)REC.get("responseMessage"));
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
				errorCheck(response);
				return null;
			}

	}

	//Qr 결제
	@Override
	public void qrWithdraw(QrWithdrawReqDto qrWithdrawReqDto) throws IOException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/drawingTransfer";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("drawingTransfer");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		Member currentMember = memberRepository.findById(qrWithdrawReqDto.getMemberId()).orElseThrow(MemberNotFoundException::new);
		Travel currentTravel = travelRepository.findById(qrWithdrawReqDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
		AccountInfoDto currentAccountInfoDto = groupAccountRepository.findByTravleId(currentTravel.getId()).orElseThrow(GroupAccountNotFoundException::new);
		requestHeaderDto.setUserKey(currentMember.getUserKey()); //확인하려는 계좌의 주인의 유저키가 필요
		System.out.println("UserKey : " + currentMember.getUserKey());
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,Object> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankCodeExtract(currentAccountInfoDto.getBankName())); //은행 코드
		System.out.println("BankCode : " + bankCodeExtract(currentAccountInfoDto.getBankName()));
		bodyHm.put("accountNo",currentAccountInfoDto.getAccountNumber()); //계좌 번호
		System.out.println("AccountNumber : " + currentAccountInfoDto.getAccountNumber());
		bodyHm.put("transactionBalance",qrWithdrawReqDto.getTransactionBalance()); //출금 금액
		bodyHm.put("transactionSummary",qrWithdrawReqDto.getTransactionSummary()); //출금 계좌 요약
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);

		HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
		HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setEntity(new StringEntity(mergeMessage, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
		HttpResponse response = client.execute(postRequest);

		//Response 출력
		if (response.getStatusLine().getStatusCode() == 200) {
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
//				System.out.println(body);
			TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
			Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
			Map<String, Object> REC = (Map<String, Object>) responseJson.get("Header");
			System.out.println("계좌 출금 제대로되는지 확인");
			System.out.println("처리 결과 : " + (String)REC.get("responseMessage"));
		} else {
			errorCheck(response);
		}
	}


	//-----------------------------------------------------------------------------------------------------------------
//result를 넣으면 공통Header를 만들어주는 메소드
public static String makeHeader(HashMap<String,String> result) throws JsonProcessingException {
	HashMap<String,HashMap<String,String>> header = new HashMap<>();
	HashMap<String,String> headerOption = new HashMap<>();
	for(Object key: result.keySet()){
//			System.out.println("key : " + key + " value : " + result.get(key));
		headerOption.put((String) key, (String) result.get(key));
	}
	header.put("Header",headerOption);
	return objectMapper.writeValueAsString(header);
}

	//result를 넣으면 Body를 만들어주는 메소드
	public static String makeBody(HashMap<String,Object> result) throws JsonProcessingException {
		return objectMapper.writeValueAsString(result);
	}

	//Json합병 메소드
	public static String makeMerge(String a, String b){
		// JSON 문자열을 JsonObject로 변환
		JsonObject A = new Gson().fromJson(a, JsonObject.class);
		JsonObject B = new Gson().fromJson(b, JsonObject.class);

		// 첫 번째 JSON 객체에 두 번째 JSON 객체의 내용을 병합
		for (String key : B.keySet()) {
			A.add(key, B.get(key));
		}

		// JsonObject를 다시 JSON 문자열로 변환
		String mergedJsonString = new Gson().toJson(A);

		// 결과 출력
		System.out.println(mergedJsonString);
		return mergedJsonString;
	}

	//뱅크코드 해석해서 은행이름 알려주는 메소드
	public static String bankName(String bankCode){
		switch (bankCode){
			case "001"://한국은행
				return "한국은행";
			case "002"://산업은행
				return "산업은행";
			case "003"://기업은행
				return "기업은행";
			case "004"://국민은행
				return "국민은행";
			default:
				return null;
		}
	}

	//은행이름 해석해서 뱅크코드 알려주는 메소드
	public static String bankCodeExtract(String bankName){
		switch (bankName){
			case "한국은행"://한국은행
				return "001";
			case "산업은행"://산업은행
				return "002";
			case "기업은행"://기업은행
				return "003";
			case "국민은행"://국민은행
				return "004";
			default:
				return null;
		}
	}

	//은행 정보 제공 메소드 //필요: 프론트엔드에서 유저가 선택한 은행 type
	public static HashMap<String, Object> selectBank(String bankType){
		HashMap<String,Object> returnHm = new HashMap<>();
		switch (bankType){
			case "001"://한국은행
				returnHm.put("accountTypeUniqueNo","001-1-81fe2deafd1943");
				return returnHm;
			case "002"://산업은행
				returnHm.put("accountTypeUniqueNo","002-1-66fe2deafd9968");
				return returnHm;
			case "003"://기업은행
				returnHm.put("accountTypeUniqueNo","003-1-2156adeafd646e");
				return returnHm;
			case "004"://국민은행
				returnHm.put("accountTypeUniqueNo","004-1-74fe2deafd3277");
				return returnHm;
			default:
				return null;
		}
	}

	//계좌 목록 조회에서 REC 제대로 왔는지 출력
	public static void RECView(ArrayList<HashMap<String,String>> REC){
		System.out.println("계좌 목록 조회 잘 되었는지 테스트");
		for(int i=0; i<REC.size(); i++){
			System.out.println(REC.get(i));
		}
	}
	//계좌 목록 조회에서 REC 추출
	public static ArrayList<BankAccountListResDto> RECextraction(ArrayList<HashMap<String,String>> REC){
		ArrayList<BankAccountListResDto> list = new ArrayList<>();
		for(int i=0; i<REC.size(); i++){
			BankAccountListResDto bankAccountListResDto = new BankAccountListResDto();
			bankAccountListResDto.setBankCode(REC.get(i).get("bankCode"));
			bankAccountListResDto.setBankName(REC.get(i).get("bankName"));
			bankAccountListResDto.setUsername(REC.get(i).get("userName"));
			bankAccountListResDto.setAccountNo(REC.get(i).get("accountNo"));
			bankAccountListResDto.setAccountBalance(Integer.parseInt(REC.get(i).get("accountBalance")));
			list.add(bankAccountListResDto);
		}
		return list;
	}

	public static void RECextractionListView(ArrayList<BankAccountListResDto> list){
		for(int i=0; i<list.size(); i++){
			System.out.print("은행 코드 : " + list.get(i).getBankCode());
			System.out.print(" / 은행 명칭 : " + list.get(i).getBankName());
			System.out.print(" / 사용자 이름 : " + list.get(i).getUsername());
			System.out.print(" / 계좌 번호 : " + list.get(i).getAccountNo());
			System.out.print(" / 남은 잔액 : " + list.get(i).getAccountBalance());
			System.out.println();
		}
	}

	//계좌 거래 내역 조회에서 REC 추출
	public static ArrayList<TransactionHistoryResDto> RECextractionTransactionHistory(ArrayList<HashMap<String,String>> REC){
		ArrayList<TransactionHistoryResDto> list = new ArrayList<>();
		for(int i=0; i<REC.size(); i++){
			TransactionHistoryResDto transactionHistoryResDto = new TransactionHistoryResDto();
			transactionHistoryResDto.setType(Integer.parseInt((String)REC.get(i).get("transactionType"))); //타입
			transactionHistoryResDto.setName((String)REC.get(i).get("transactionSummary")); //거래 내용 요약
			transactionHistoryResDto.setDate(REC.get(i).get("transactionDate")); //거래 날짜
			transactionHistoryResDto.setTime(REC.get(i).get("transactionTime")); //거래 일시
			transactionHistoryResDto.setCost(Integer.parseInt((String)REC.get(i).get("transactionBalance"))); //거래 금액
			transactionHistoryResDto.setAmount(Integer.parseInt((String)REC.get(i).get("transactionAfterBalance"))); //거래 후 잔액
			list.add(transactionHistoryResDto);
		}
		return list;
	}

	//계좌 거래 내역 조회에서 추출한 REC 조회
	public static void RECextractionTransactionHistoryListView(ArrayList<TransactionHistoryResDto> list){
		for(int i=0; i<list.size(); i++){
			System.out.print("타입 : " + list.get(i).getType());
			System.out.print(" / 거래 내용 요약 : " + list.get(i).getName());
			System.out.print(" / 거래 날짜 : " + list.get(i).getDate());
			System.out.print(" / 거래 일시 : " + list.get(i).getTime());
			System.out.print(" / 거래 금액 : " + list.get(i).getCost());
			System.out.print(" / 거래 후 잔액 : " + list.get(i).getAmount());
			System.out.println();
		}
	}



	public static void post(String requestURL, RequestHeaderDto requestHeaderDto) {
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setApiKey(adminKey);
		ObjectMapper objectMapper = new ObjectMapper();
		Map result = objectMapper.convertValue(requestHeaderDto, Map.class);
		String jsonMessage = null;
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
			for(Object key: result.keySet()){
				System.out.println("key : " + key + " value : " + result.get(key));
				postRequest.addHeader((String) key, (String) result.get(key));
			}
			if(!jsonMessage.isEmpty()) {
				postRequest.setEntity(new StringEntity(jsonMessage,ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
			}
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e){
			System.err.println(e.toString());
		}
	}

	public static void dtoTest(){
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiName("이우진");
		requestHeaderDto.setUserKey("이우진");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		ObjectMapper objectMapper = new ObjectMapper();
		Map result = objectMapper.convertValue(requestHeaderDto, Map.class);
		for(Object key: result.keySet()){
			System.out.println(result.get(key));
		}
		System.out.println(result);
	}

	public static void errorCheck(HttpResponse response) throws IOException {
		System.out.println("response is error : " + response.getStatusLine().getStatusCode());
		String responseString = EntityUtils.toString(response.getEntity());
		Gson gson = new Gson();
		Map<String, Object> responseMap = gson.fromJson(responseString, Map.class);
		System.out.println(responseMap.get("responseCode"));
		String errorCode = (String) responseMap.get("responseCode");
		if(errorCode.equals("H1008")){
			throw new H1008Exception();
		}else if(errorCode.equals("H1009")){
			throw new H1009Exception();
		}else if(errorCode.equals("A1001")){
			throw new A1001Exception();
		}else if(errorCode.equals("A1003")){
			throw new A1003Exception();
		}else if(errorCode.equals("A1011")){
			throw new A1011Exception();
		}else if(errorCode.equals("A1014")){
			throw new A1014Exception();
		}else if(errorCode.equals("A1016")){
			throw new A1016Exception();
		}else if(errorCode.equals("A1017")){
			throw new A1017Exception();
		}else if(errorCode.equals("A1018")){
			throw new A1018Exception();
		}
	}
}
