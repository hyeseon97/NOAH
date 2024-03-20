package com.noah.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.noah.backend.domain.account.dto.requestDto.RequestHeaderDto;
import com.noah.backend.domain.account.dto.responseDto.AccountHolderCheckDto;
import com.noah.backend.domain.account.dto.responseDto.AccountListDto;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.admin.dto.requestDto.AdminKeyRequestDto;
import com.noah.backend.domain.bank.dto.responseDto.AccountCreateDto;
import com.noah.backend.domain.member.dto.requestDto.SignUpDto;
import com.noah.backend.domain.member.dto.requestDto.UserKeyRequestDto;
import net.bytebuddy.description.type.TypeList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {
	static String adminEmail = "dldnwls009@ssafy.co.kr";
	static String adminKey = "2971f57e01a54fd0a91161ec5c59c3cd";
	static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
//		adKeyRequestRun(); //관리자 키 발급 메소드
//		memberCreateRun(); //사용자 계정 생성 메소드
//		productSelectRun(); //상품 조회 메소드
//		accountCreateRun(); //계좌 생성 메소드
//		accountHolderCheckRun(); //예금주 조회 메소드
//		accountListRun(); //계좌 목록 조회
//		accountBalanceCheckRun(); //계좌 잔액 조회
	}

	//관리자 키 발급 메소드
	public static void adKeyRequestRun() throws JsonProcessingException {//adKeyRequest 실행
		adKeyRequest();
	}
	//관리자 키 발급
	public static void adKeyRequest() throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/app/issuedApiKey";
		Map result = objectMapper.convertValue(new AdminKeyRequestDto(), Map.class);
		String jsonMessage = objectMapper.writeValueAsString(result);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
				System.out.println(result);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(jsonMessage)); //json 메시지 입력
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

	//사용자 계정 생성 메소드
	public static void memberCreateRun() throws JsonProcessingException {
		memberCreate(new SignUpDto());
	}

	//사용자 계정 생성
	public static SignUpDto memberCreate(SignUpDto signUpDto) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/member/";
		UserKeyRequestDto userKeyRequestDto = new UserKeyRequestDto();
		userKeyRequestDto.setApiKey(adminKey);
		userKeyRequestDto.setUserId(signUpDto.getEmail());
		Map result = objectMapper.convertValue(userKeyRequestDto, Map.class);
		String jsonMessage = objectMapper.writeValueAsString(result);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
			System.out.println(result);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(jsonMessage)); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> payload = (Map<String, Object>) responseJson.get("payload");
				System.out.println("유저 키 발급완료 SUCCESS : " + payload.get("userKey"));
				signUpDto.setUserKey((String) payload.get("userKey"));
				return signUpDto;
			} else {
				//오류가 발생하면 이미 존재하는 사용자입니다.(회원가입 불가) null값 대신 나중에 예외처리로 변경
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e){
			System.err.println(e.toString());
			return null;
		}
	}

	//상품 조회 메소드
	public static void productSelectRun() throws JsonProcessingException {
		productSelect();
	}

	//상품 조회
	public static void productSelect() throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireBankAccountTypes";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireBankAccountTypes");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(headerMessage)); //json 메시지 입력
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

	//계좌 생성 메소드
	public static void accountCreateRun() throws JsonProcessingException {
		String testUserKey = "06c7432c-09cc-4190-a119-ff5128072c6f"; //dldnwlstest11@ssafy.co.kr
		String testBankType = "004";
		accountCreate(testUserKey,testBankType);
	}

	//계좌 생성 //필요: MEMBER테이블의 userKey, 프론트엔드에서 유저가 선택한 은행 bankType
	public static AccountCreateDto accountCreate(String userKey, String bankType) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/openAccount";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("openAccount");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(userKey);
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,String> bodyHm = selectBank(bankType);
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage)); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
				AccountCreateDto accountCreateDto = new AccountCreateDto();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("REC");
				accountCreateDto.setBankName(bankName((String)REC.get("bankCode")));
				accountCreateDto.setAccountNumber((String)REC.get("accountNo"));
				System.out.println("계좌 잘 생성되었는지 테스트");
				System.out.println("개설 은행 : " + accountCreateDto.getBankName());
				System.out.println("개설 계좌 번호 : " + accountCreateDto.getAccountNumber());
				return accountCreateDto;
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e){
			System.err.println(e.toString());
			return null;
		}
	}
	//계좌 목록 조회 메소드
	public static void accountListRun() throws JsonProcessingException {
		String userKey = "06c7432c-09cc-4190-a119-ff5128072c6f";
		accountList(userKey);
	}

	//계좌 목록 조회
	public static ArrayList<AccountListDto> accountList(String userKey) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireAccountList";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireAccountList");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(userKey);
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(headerMessage)); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
				AccountCreateDto accountCreateDto = new AccountCreateDto();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				ArrayList<HashMap<String,String>> REC = (ArrayList<HashMap<String,String>>) responseJson.get("REC");
				RECView(REC);
				ArrayList<AccountListDto> RECextractionList = RECextraction(REC);
				RECextractionListView(RECextractionList);
				return RECextractionList;
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e){
			System.err.println(e.toString());
			return null;
		}
	}
	//예금주 조회 메소드
	public static void accountHolderCheckRun() throws JsonProcessingException {
		String bankCode = "004";
		String accountNo = "0047501884450113";
		accountHolderCheck(bankCode,accountNo);
	}

	//예금주 조회
	public static AccountHolderCheckDto accountHolderCheck (String bankCode, String accountNo) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireDepositorAccountNumber";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireDepositorAccountNumber");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		String userKey = "0c00c0f6-b093-4d4b-bce3-c7730567584e"; //어차피 의미없어서 dldnwls12test의 userKey로 해놓음
		requestHeaderDto.setUserKey(userKey);
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,String> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankCode);
		bodyHm.put("accountNo",accountNo);
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage)); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
				AccountHolderCheckDto accountHolderCheckDto = new AccountHolderCheckDto();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("REC");
				accountHolderCheckDto.setBankCode((String)REC.get("bankCode"));
				accountHolderCheckDto.setBankName(bankName((String)REC.get("bankCode")));
				accountHolderCheckDto.setAccountNo((String)REC.get("accountNo"));
				accountHolderCheckDto.setUserName((String)REC.get("userName"));
				System.out.println("예금주 조회 잘 되었는지 테스트");
				System.out.println("예금주 은행 코드: " + accountHolderCheckDto.getBankCode());
				System.out.println("예금주 은행 명: " + accountHolderCheckDto.getBankName());
				System.out.println("예금주 계좌 번호: " + accountHolderCheckDto.getAccountNo());
				System.out.println("예금주 성함: " + accountHolderCheckDto.getUserName());
				return accountHolderCheckDto;
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e){
			System.err.println(e.toString());
			return null;
		}
	}

	//계좌 잔액 메소드
	public static void accountBalanceCheckRun() throws JsonProcessingException {
		String userKey = "06c7432c-09cc-4190-a119-ff5128072c6f";
		String bankCode = "002";
		String accountNo = "0027546213312878";
		accountBalanceCheck(userKey,bankCode,accountNo);
	}

	//계좌 잔액 조회
	public static int accountBalanceCheck(String userKey,String bankCode, String accountNo) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireAccountBalance";
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiKey(adminKey);
		requestHeaderDto.setApiName("inquireAccountBalance");
		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
		requestHeaderDto.setUserKey(userKey); //확인하려는 계좌의 주인의 유저키가 필요
		HashMap<String,String> result = objectMapper.convertValue(requestHeaderDto, HashMap.class);
		String headerMessage = makeHeader(result);
		HashMap<String,String> bodyHm = new HashMap<>();
		bodyHm.put("bankCode",bankCode);
		bodyHm.put("accountNo",accountNo);
		String bodyMessage = makeBody(bodyHm);
		String mergeMessage = makeMerge(headerMessage,bodyMessage);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성

			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(mergeMessage)); //json 메시지 입력
			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
				Map<String, Object> responseJson = objectMapper.readValue(body, typeReference);
				Map<String, Object> REC = (Map<String, Object>) responseJson.get("REC");
				System.out.println("잔액 출력 제대로되는지 확인");
				System.out.println("잔액 출력 : " + Integer.parseInt((String)REC.get("accountBalance")));
				return Integer.parseInt((String)REC.get("accountBalance"));
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
				return -100; //오류나면 -100
			}
		} catch (Exception e){
			System.err.println(e.toString());
			return -100;
		}
	}





//--------------------------------------------------------------------------------------------------------------
	//result를 넣으면 공통Header를 만들어주는 메소드
	public static String makeHeader(HashMap<String,String> result) throws JsonProcessingException {
		HashMap<String,HashMap<String,String>> header = new HashMap<>();
		HashMap<String,String> headerOption = new HashMap<>();
		for(Object key: result.keySet()){
			System.out.println("key : " + key + " value : " + result.get(key));
			headerOption.put((String) key, (String) result.get(key));
		}
		header.put("Header",headerOption);
		return objectMapper.writeValueAsString(header);
	}

	//result를 넣으면 Body를 만들어주는 메소드
	public static String makeBody(HashMap<String,String> result) throws JsonProcessingException {
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

	//은행 정보 제공 메소드 //필요: 프론트엔드에서 유저가 선택한 은행 type
	public static HashMap<String, String> selectBank(String bankType){
		HashMap<String,String> returnHm = new HashMap<>();
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
	public static ArrayList<AccountListDto> RECextraction(ArrayList<HashMap<String,String>> REC){
		ArrayList<AccountListDto> list = new ArrayList<>();
		for(int i=0; i<REC.size(); i++){
			AccountListDto accountListDto = new AccountListDto();
			accountListDto.setBankCode(REC.get(i).get("bankCode"));
			accountListDto.setBankName(REC.get(i).get("bankName"));
			accountListDto.setUsername(REC.get(i).get("userName"));
			accountListDto.setAccountNo(REC.get(i).get("accountNo"));
			accountListDto.setAccountBalance(Integer.parseInt(REC.get(i).get("accountBalance")));
			list.add(accountListDto);
		}
		return list;
	}

	public static void RECextractionListView(ArrayList<AccountListDto> list){
		for(int i=0; i<list.size(); i++){
			System.out.print("은행 코드 : " + list.get(i).getBankCode());
			System.out.print(" / 은행 명칭 : " + list.get(i).getBankName());
			System.out.print(" / 사용자 이름 : " + list.get(i).getUsername());
			System.out.print(" / 계좌 번호 : " + list.get(i).getAccountNo());
			System.out.print(" / 남은 잔액 : " + list.get(i).getAccountBalance());
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
				postRequest.setEntity(new StringEntity(jsonMessage)); //json 메시지 입력
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
}
