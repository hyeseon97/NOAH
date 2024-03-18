package com.noah.backend.domain.account.controller;

import com.noah.backend.domain.account.dto.requestDto.RequestHeaderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Account 컨트롤러", description = "Account Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

	//상품 조회
	@GetMapping("/productlist")
	@Operation(summary = "은행 별 계좌생성 가능목록", description = "계좌 상품 조회")
	public ResponseEntity<?> productInquiry(){

		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiName("inquireBankAccountTypes");
		return new ResponseEntity<>(requestHeaderDto, HttpStatus.OK);
	}

	//계좌 생성
	@GetMapping("/create")
	@Operation(summary = "계좌 생성", description = "유저 계좌 생성")
	public ResponseEntity<?> createAccount(String email){

		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		//email로 userKey를 얻었다고 가정
		requestHeaderDto.setApiName("openAccount");
		requestHeaderDto.setUserKey("유저userKey필요");
		return new ResponseEntity<>(requestHeaderDto, HttpStatus.OK);
	}
}
