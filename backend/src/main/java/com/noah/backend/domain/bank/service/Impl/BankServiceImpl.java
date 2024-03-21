package com.noah.backend.domain.bank.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noah.backend.domain.admin.dto.requestDto.AdminKeyRequestDto;
import com.noah.backend.domain.bank.dto.requestDto.memberCreateReqDto;
import com.noah.backend.domain.bank.service.BankService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BankServiceImpl implements BankService {
	static String adminEmail = "dldnwls009@ssafy.co.kr";
	static String adminKey = "2971f57e01a54fd0a91161ec5c59c3cd";
	static ObjectMapper objectMapper = new ObjectMapper();

	//관리자 키 발급 메소드
	@Override
	public void adKeyRequest() throws JsonProcessingException {
		String requestURL = "https://finapi.p.ssafy.io/ssafy/api/v1/edu/app/issuedApiKey";
		Map result = objectMapper.convertValue(new AdminKeyRequestDto(), Map.class);
		String jsonMessage = objectMapper.writeValueAsString(result);
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
			System.out.println(result);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(jsonMessage, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))); //json 메시지 입력
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

	@Override
	public memberCreateReqDto memberCreate(memberCreateReqDto memberCreateReqDto) throws JsonProcessingException {
		return null;
	}
}
