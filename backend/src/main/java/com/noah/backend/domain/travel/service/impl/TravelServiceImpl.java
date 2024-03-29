package com.noah.backend.domain.travel.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelUpdateDto;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.domain.travel.service.TravelService;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import com.noah.backend.global.exception.travelmember.MemberTravelNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class TravelServiceImpl implements TravelService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;
    private final MemberTravelRepository memberTravelRepository;

    @Override
    public List<TravelGetListDto> getTravelList() {

        return travelRepository.getTravelList().orElseThrow(TravelNotFoundException::new);
    }

    @Override
    public TravelGetDto getTravelSelect(String email, Long travelId) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberTravelNotFound::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelId).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        TravelGetDto travelGetDto = travelRepository.getTravelSelect(travelId).orElseThrow(TravelNotFoundException::new);
        List<SimplePlan> simplePlanList = planRepository.getSimplePlan(travelGetDto.getPlanId()).orElse(null);
        return travelGetDto;
    }

//    @Override
//    public List<TravelGetListDto> getTravelMemberId(String email) {
//
//        Member member = memberRepository.findByEmail(email).orElseThrow(MemberTravelNotFound::new);
//
//        List<TravelGetListDto> travelList =
//        return travelRepository.getTravelListToMember(memberId).orElseThrow(TravelNotFoundException::new);
//    }

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
    public Long createTravelTest(TravelPostDto travelDto, @RequestParam Long memberId) {
        Travel travel = Travel.builder()
                .title(travelDto.getTitle())
                .build();

        Travel saveTravel = travelRepository.save(travel);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberTravelNotFound::new);


        MemberTravel memberTravel = MemberTravel.builder()
                .member(member)
                .travel(travel)
                .build();
        memberTravelRepository.save(memberTravel);

        return  saveTravel.getId();
    }

    @Override
    public Long updateTravel(Long travelId, TravelUpdateDto travelDto) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);
        travel.setTitle(travelDto.getTitle());
        travel.setEnded(travelDto.isEnded());

        return travelRepository.save(travel).getId();
    }

    @Override
    public void deleteTravel(Long travelId) {
        travelRepository.deleteById(travelId);
    }
}
