package com.noah.backend.domain.csv.service;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.text.ParseException;

public interface CsvService {
    //리뷰,이미지 더미데이터 저장
    void readCsvAndSaveReviewAndImage() throws CsvValidationException, IOException, ParseException;


}
