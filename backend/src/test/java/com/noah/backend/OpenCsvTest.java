package com.noah.backend;

import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class OpenCsvTest {
	private final ReviewRepository reviewRepository;

	@Transactional
	public static void main(String[] args) throws CsvValidationException, IOException {
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
		}
	}
}
