package com.noah.backend.domain.suggest.service.Impl;

import com.noah.backend.domain.groupaccount.repository.GroupAccountRepository;
import com.noah.backend.domain.image.repository.ImageRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.suggest.dto.requestDto.SuggestImageGetDto;
import com.noah.backend.domain.suggest.dto.responseDto.MainSuggestGetDto;
import com.noah.backend.domain.suggest.dto.responseDto.SuggestListResDto;
import com.noah.backend.domain.suggest.service.SuggestService;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.review.ReviewNotFound;
import com.noah.backend.global.exception.suggest.SuggestNotExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@RequiredArgsConstructor
@Service
public class SuggestServiceImpl implements SuggestService {

	private final MemberRepository memberRepository;
	private final MemberTravelRepository memberTravelRepository;
	private final GroupAccountRepository groupAccountRepository;
	private final ReviewRepository reviewRepository;
	private final ImageRepository imageRepository;

	@Override
	public List<SuggestListResDto> getSuggestList(Long travelId) {

		int total = memberTravelRepository.totalPeople(travelId).orElse(0);
		//이우진 교보재
		//int balance = groupAccountRepository.findBalance(suggestListReqDto.getTravelId()).orElse(0);
		Integer targetAmount = groupAccountRepository.findTargetAmount(travelId).orElse(null);
		System.out.println("targetAmount : " + targetAmount);

		if(targetAmount == null){//목표금액이 null이면 랜덤으로 여행 후기 보여주기
			int reviewCount = reviewRepository.getRandomSuggestId().orElse(0);
			return makeRandomSuggestList(reviewCount);
		} else{//목표금액이 존재하면 targetAmount/total로 인당 가격을 환산하여 여행후기 추천
			int priceOfPerson = targetAmount/total;
			List<SuggestListResDto> reviewlist = reviewRepository.getSuggestReviewList(priceOfPerson).orElse(null);

			if(reviewlist == null || reviewlist.size()==0){
				System.out.println("왜왜왜");
				int reviewCount = reviewRepository.getRandomSuggestId().orElse(0);
				return makeRandomSuggestList(reviewCount);
			}

			List<SuggestListResDto> returnlist = new ArrayList<>();
			for(SuggestListResDto s : reviewlist){
				Long reviewId = s.getId();
				List<SuggestImageGetDto> imageList = imageRepository.getImageList(reviewId).orElse(null);
				SuggestListResDto suggestListResDto = SuggestListResDto.builder()
						.id(s.getId())
						.expense(s.getExpense())
						.country(s.getCountry())
						.people(s.getPeople())
						.startDate(s.getStartDate())
						.endDate(s.getEndDate())
						.imageList(imageList)
						.build();
				returnlist.add(suggestListResDto);
			}
			return returnlist;
		}
	}

	//메인페이지에서 띄울 내가 포함된 여행의 대표 추천 여행
	@Override
	public List<MainSuggestGetDto> getSuggestMain(String email) {

		Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
		List<Long> travelIdList = memberTravelRepository.findByMemberId(member.getId()).orElse(null);

		List<MainSuggestGetDto> result = new ArrayList<>();
		for(Long travelId : travelIdList){

			SuggestListResDto suggest = getSuggestOne(travelId);

			MainSuggestGetDto mainSuggestGetDto = MainSuggestGetDto.builder()
				.travelId(travelId)
				.reviewId(suggest.getId())
				.country(suggest.getCountry())
				.expense(suggest.getExpense())
				.imageId(suggest.getImageList().get(0).getImageId())
				.imageUrl(suggest.getImageList().get(0).getImageUrl())
				.build();

			result.add(mainSuggestGetDto);
		}

		return result;
	}

	//둘러보기할때 메인페이지에서 띄울 대표 추천 여행
	@Override
	public List<MainSuggestGetDto> nonLoginGetSuggestMain() {

		List<MainSuggestGetDto> result = new ArrayList<>();
			int reviewCount = reviewRepository.getRandomSuggestId().orElse(0);
			SuggestListResDto suggest = makeRandomSuggestOne(reviewCount);

			MainSuggestGetDto mainSuggestGetDto = MainSuggestGetDto.builder()
					.travelId(null)
					.reviewId(suggest.getId())
					.country(suggest.getCountry())
					.expense(suggest.getExpense())
					.imageId(suggest.getImageList().get(0).getImageId())
					.imageUrl(suggest.getImageList().get(0).getImageUrl())
					.build();

			result.add(mainSuggestGetDto);

		return result;
	}

	//------------------------------------------------------------------------------------------------------
	//랜덤 제안리스트를 만드는 메소드
	public List<SuggestListResDto> makeRandomSuggestList(int reviewCount){
		if(reviewCount==0){
			throw new SuggestNotExists();
		}else{
			List<SuggestListResDto> list = new ArrayList<>();
			HashSet<Long> hm = new HashSet<>();
			while(hm.size()<=29){
				long num = ThreadLocalRandom.current().nextInt(1, reviewCount);
				hm.add(num);
			}
			for(long reviewId:hm){
				Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
				List<SuggestImageGetDto> imageIdList = imageRepository.findImageOfReview(reviewId).orElse(null);
				SuggestListResDto suggestListResDto = SuggestListResDto.builder()
						.id(review.getId())
						.expense(review.getExpense())
						.country(review.getCountry())
						.people(review.getPeople())
						.startDate(review.getStartDate())
						.endDate(review.getEndDate())
						.imageList(imageIdList)
						.build();
				list.add(suggestListResDto);
			}
			return list;
		}
	}


	//인당 가격보다 낮은 제안리스트를 만드는 메소드
	/*
	public List<SuggestListResDto> makePriceSuggestList(List<Long> reviewIdlist){
		if(reviewIdlist==null){
			throw new LowerThanPriceNotExists();
		}else{
			List<SuggestListResDto> list = new ArrayList<>();
			for(int i=0; i<reviewIdlist.size(); i++){
				Long reviewId = reviewIdlist.get(i);
				Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
				List<Long> imageIdList = imageRepository.findImageOfReview(reviewId).orElse(null);
				SuggestListResDto suggestListResDto = SuggestListResDto.builder()
						.expense(review.getExpense())
						.Country(review.getCountry())
						.people(review.getPeople())
						.startDate(review.getStartDate())
						.endDate(review.getEndDate())
						.imageIdList(imageIdList)
						.build();
				list.add(suggestListResDto);
			}
			return list;
		}
	}
	*/

	//추천할 여행 썸네일을 만드는 메소드
	public SuggestListResDto getSuggestOne(Long travelId) {

		int total = memberTravelRepository.totalPeople(travelId).orElse(0);
		//이우진 교보재
		//int balance = groupAccountRepository.findBalance(suggestListReqDto.getTravelId()).orElse(0);
		Integer targetAmount = groupAccountRepository.findTargetAmount(travelId).orElse(null);
		System.out.println("targetAmount : " + targetAmount);

		if(targetAmount == null){//목표금액이 null이면 랜덤으로 여행 후기 보여주기
			int reviewCount = reviewRepository.getRandomSuggestId().orElse(0);
			return makeRandomSuggestOne(reviewCount);
		} else{//목표금액이 존재하면 targetAmount/total로 인당 가격을 환산하여 여행후기 추천
			int priceOfPerson = targetAmount/total;
			SuggestListResDto review = reviewRepository.getSuggestReviewOne(priceOfPerson).orElse(null);

			if(review == null){
				int reviewCount = reviewRepository.getRandomSuggestId().orElse(0);
				return makeRandomSuggestOne(reviewCount);
			}


				List<SuggestImageGetDto> imageList = imageRepository.getImageList(review.getId()).orElse(null);
				SuggestListResDto suggestListResDto = SuggestListResDto.builder()
						.id(review.getId())
						.expense(review.getExpense())
						.country(review.getCountry())
						.people(review.getPeople())
						.startDate(review.getStartDate())
						.endDate(review.getEndDate())
						.imageList(imageList)
						.build();

			return suggestListResDto;
		}
	}

	//추천할 랜덤 여행 썸네일을 만드는 메소드
	public SuggestListResDto makeRandomSuggestOne(int reviewCount){
		if(reviewCount==0){
			throw new SuggestNotExists();
		}else{
			long reviewId = ThreadLocalRandom.current().nextInt(1, reviewCount);
				Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
				List<SuggestImageGetDto> imageIdList = imageRepository.findImageOfReview(reviewId).orElse(null);
				SuggestListResDto suggestListResDto = SuggestListResDto.builder()
						.id(review.getId())
						.expense(review.getExpense())
						.country(review.getCountry())
						.people(review.getPeople())
						.startDate(review.getStartDate())
						.endDate(review.getEndDate())
						.imageList(imageIdList)
						.build();
			return suggestListResDto;
		}
	}
}
