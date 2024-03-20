package com.noah.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noah.backend.domain.groupaccount.dto.requestDto.RequestHeaderDto;
import com.noah.backend.domain.admin.dto.requestDto.AdminKeyRequestDto;
import com.noah.backend.domain.member.dto.requestDto.SignUpDto;
import com.noah.backend.domain.member.dto.requestDto.UserKeyRequestDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {
	static String adminEmail = "dldnwls009@ssafy.co.kr";
	static String adminKey = "2971f57e01a54fd0a91161ec5c59c3cd";
	static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
//		adKeyRequestRun(); //관리자 키 발급 메소드
//		memberCreateRun(); //사용자 계정 생성 메소드
		productSelectRun();
	}
	//관리자 키 발급 메소드
	public static void adKeyRequestRun() throws JsonProcessingException {//adKeyRequest 실행
		adKeyRequest(new AdminKeyRequestDto());
	}
	//관리자 키 발급
	public static void adKeyRequest(AdminKeyRequestDto adminKeyRequestDto) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/app/issuedApiKey";
		Map result = objectMapper.convertValue(adminKeyRequestDto, Map.class);
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
		memberCreate(new SignUpDto(), new UserKeyRequestDto());
	}

	//사용자 계정 생성
	public static SignUpDto memberCreate(SignUpDto signUpDto, UserKeyRequestDto userKeyRequestDto) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/member/";
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
//				System.out.println("테스트 해보겠습니다 " + payload.get("userKey"));
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
		productSelect(new RequestHeaderDto());
	}

	//상품 조회
	public static void productSelect(RequestHeaderDto requestHeaderDto) throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/account/inquireBankAccountTypes";
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




	//result를 넣으면 공통Header를 만들어주는 메소드
	public static String makeHeader(HashMap<String,String> result) throws JsonProcessingException {
		HashMap<String,HashMap<String,String>> header = new HashMap<>();
		HashMap<String,String> headerOption = new HashMap<>();
		for(Object key: result.keySet()){
			if(!key.equals("userKey")) {
				System.out.println("key : " + key + " value : " + result.get(key));
				headerOption.put((String) key, (String) result.get(key));
			}
		}
		header.put("Header",headerOption);
		return objectMapper.writeValueAsString(header);
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
