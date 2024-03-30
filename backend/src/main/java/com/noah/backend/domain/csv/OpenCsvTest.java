package com.noah.backend.domain.csv;

import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
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
@RequiredArgsConstructor
@Service
public class OpenCsvTest {
	private final ReviewRepository reviewRepository;

	@Transactional
	public void readCsvAndSaveReviews() throws CsvValidationException, IOException, ParseException {
		ClassPathResource resource = new ClassPathResource("csv/review.csv");
		CSVReader csvReader = new CSVReader(new FileReader(resource.getFile()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String[] line;
		while ((line = csvReader.readNext()) != null) {
			Review review = Review.builder()
					.expense(Integer.parseInt(line[0]))
					.country(line[1])
					.people(Integer.parseInt(line[2]))
					.startDate(dateFormat.parse(line[3]))
					.endDate(dateFormat.parse(line[4]))
					.build();
			System.out.println(String.join(",", line));
			reviewRepository.save(review);
		}
	}
}
