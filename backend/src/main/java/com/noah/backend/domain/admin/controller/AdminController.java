package com.noah.backend.domain.admin.controller;

import com.noah.backend.domain.admin.dto.responseDto.AdminKeyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class AdminController {
	//관리자 APIKEY 발급
	@PostMapping("/ssafy/api/v1/edu/app/issuedApiKey")
	@Operation(summary = "관리자 Key 발급", description = "관리자 Key 발급")
	public ResponseEntity<?> productInquiry(@RequestBody String managerId){
		AdminKeyResponseDto adminKeyResponseDto = new AdminKeyResponseDto();
		return new ResponseEntity<>(adminKeyResponseDto, HttpStatus.OK);
	}
}
