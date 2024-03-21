package com.noah.backend.global.format.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noah.backend.domain.bank.dto.requestDto.RequestHeaderDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Map;

public class HttpClientRequest {
	static String adminEmail = "dldnwls009@ssafy.co.kr";
	static String adminKey = "2971f57e01a54fd0a91161ec5c59c3cd";
	public void get(String requestURL) {
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
			getRequest.addHeader("x-api-key", "RestTestCommon.API_KEY"); //KEY 입력

			HttpResponse response = client.execute(getRequest);

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

//	//Body에 넣을 값이 없다면 jsonMessage는 null //Header, Body 구성
//	public void post(String requestURL, RequestHeaderDto requestHeaderDto, String jsonMessage) {
//		requestHeaderDto.setApiServiceCode(requestHeaderDto.getApiName());
//		ObjectMapper objectMapper = new ObjectMapper();
//		Map result = objectMapper.convertValue(requestHeaderDto, Map.class);
//
//		try {
//			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
//			HttpPost postRequest = new HttpPost(requestURL); //전송방식 HttpPost 방식 //POST 메소드 URL 생성
//			for(Object key: result.keySet()){
//				System.out.println("key : " + key + " value : " + result.get(key));
//				postRequest.addHeader((String) key, (String) result.get(key));
//			}
//			if(!jsonMessage.isEmpty()) {
//				postRequest.setEntity(new StringEntity(jsonMessage)); //json 메시지 입력
//			}
//			HttpResponse response = client.execute(postRequest);
//
//			//Response 출력
//			if (response.getStatusLine().getStatusCode() == 200) {
//				ResponseHandler<String> handler = new BasicResponseHandler();
//				String body = handler.handleResponse(response);
//				System.out.println(body);
//			} else {
//				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
//			}
//		} catch (Exception e){
//			System.err.println(e.toString());
//		}
//	}

	/*참고
	https://ynzu-dev.tistory.com/entry/JAVA-VO%EB%A5%BC-JSON-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A1%9C-%EB%B3%80%ED%99%98-java-class-to-json
	 */

}
