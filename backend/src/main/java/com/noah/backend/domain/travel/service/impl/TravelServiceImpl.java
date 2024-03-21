package com.noah.backend.domain.travel.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelUpdateDto;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class TravelServiceImpl implements TravelService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final TravelRepository travelRepository;

    @Override
    public List<TravelGetListDto> getTravelList() {

        return travelRepository.getTravelList().orElseThrow(() -> new RuntimeException("여행 정보를 찾을 수 없습니다."));
    }

    @Override
    public TravelGetDto getTravelSelect(Long travelId) {
        return travelRepository.getTravelSelect(travelId).orElseThrow(() -> new RuntimeException("선택하신 여행 정보를 찾을 수 없습니다."));
    }

    @Override
    public Long createTravel(TravelPostDto travelDto) {
        Travel travel = Travel.builder()
                .title(travelDto.getTitle())
                .build();

//        Travel saveTravel = travelRepository.save(travel);
//
//        MemberTravel memberTravel = MemberTravel.builder()
//                .member(member)
//                .travel(saveTravel)
//                .build();
//        MemberTravelRepository.save(memberTravel);

        return  travelRepository.save(travel).getId();
    }

    @Override
    public Long updateTravel(Long travelId, TravelUpdateDto travelDto) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new RuntimeException("수정할 여행 정보가 없습니다."));
        travel.setTitle(travelDto.getTitle());
        travel.setEnded(travelDto.isEnded());

        return travelRepository.save(travel).getId();
    }

    @Override
    public void deleteTravel(Long travelId) {
        travelRepository.deleteById(travelId);
    }
}
