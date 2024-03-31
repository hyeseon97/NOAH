package com.noah.backend.domain.csv.service.impl;

import com.noah.backend.domain.csv.service.CsvService;
import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.repository.ImageRepository;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.global.exception.csv.CsvCreateFailedException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Log4j2
@Transactional
@RequiredArgsConstructor
@Service
public class CsvServiceImpl implements CsvService {
	private final ReviewRepository reviewRepository;
	private final ImageRepository imageRepository;

	//리뷰,이미지 더미데이터 저장
	public void readCsvAndSaveReviewAndImage() throws CsvValidationException, IOException, ParseException {
		ClassPathResource resource = new ClassPathResource("csv/review_and_image.csv");
		CSVReader csvReader = new CSVReader(new FileReader(resource.getFile()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String[] line;
		csvReader.readNext(); // 첫 번째 행을 읽어온 후 버림
		while ((line = csvReader.readNext()) != null) {
			Review review = Review.builder()
					.expense(Integer.parseInt(line[0]))
					.country(line[1])
					.people(Integer.parseInt(line[2]))
					.startDate(dateFormat.parse(line[3]))
					.endDate(dateFormat.parse(line[4]))
					.build();
			Image image = Image.builder()
					.review(review)
					.url(line[5])
					.build();
			System.out.println(String.join(",", line));
			try {
				reviewRepository.save(review);
				imageRepository.save(image);
			}catch (Exception e){
				throw new CsvCreateFailedException(); //리뷰 또는 이미지가 저장되지않았다면 예외 발생
			}
		}
	}

}
