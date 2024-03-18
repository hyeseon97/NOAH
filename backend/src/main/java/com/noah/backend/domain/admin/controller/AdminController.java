package com.noah.backend.domain.admin.controller;

import com.noah.backend.domain.admin.dto.responseDto.AdminApiKey;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	//관리자 APIKEY 발급
	@PostMapping("/ssafy/api/v1/edu/app/issuedApiKey")
	@Operation(summary = "관리자 Key 발급", description = "관리자 Key 발급")
	public ResponseEntity<?> productInquiry(@RequestBody String managerId){
		AdminApiKey adminApiKey = new AdminApiKey();
		return new ResponseEntity<>(adminApiKey, HttpStatus.OK);
	}
}
