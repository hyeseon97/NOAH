package com.noah.backend.domain.csv.controller;

import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.csv.service.CsvService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

//리뷰,이미지에 더미데이터를 저장하는 컨트롤러
@Tag(name = "Csv 컨트롤러", description = "Csv Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/csv")
public class CsvController {

    private final ApiResponse response;
    private final CsvService csvService;

    @Operation(summary = "Review,Image DB에 더미데이터 생성", description = "Review,Image DB에 더미데이터 생성")
    @PostMapping("")
    public ResponseEntity<?> createReviewAndImageDummy() throws CsvValidationException, IOException, ParseException {
            csvService.readCsvAndSaveReviewAndImage();
        return response.success(ResponseCode.DUMMY_SUCCESS);
    }

}
