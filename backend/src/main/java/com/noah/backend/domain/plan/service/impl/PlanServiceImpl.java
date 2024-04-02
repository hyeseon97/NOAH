package com.noah.backend.domain.plan.service.impl;

import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.plan.dto.requestDto.PlanPostDto;
import com.noah.backend.domain.plan.dto.requestDto.PlanUpdateDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanListGetFromTravelDto;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.plan.service.PlanService;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.groupaccount.GroupAccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.plan.PlanNotFound;
import com.noah.backend.global.exception.plan.PlanUpdateFailed;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;
    private final MemberTravelRepository memberTravelRepository;

    @Override
    public List<PlanListGetFromTravelDto> getPlanList(String email, Long travelId) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelId).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        return planRepository.getPlanList(travelId).orElseThrow(PlanNotFound::new);
    }

    @Override
    public PlanGetDto getPlanSelect(String email, Long travelId) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelId).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        PlanGetDto planGetDto = planRepository.getPlanSelect(travelId).orElseThrow(PlanNotFound::new);

        return planGetDto;
    }

    @Transactional
    @Override
    public Long createPlan(String email, PlanPostDto planDto) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), planDto.getTravelId()).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        Travel travel = travelRepository.findById(planDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        Plan plan = Plan.builder()
                .startDate(planDto.getStartDate())
                .endDate(planDto.getEndDate())
                .travelStart(false)
                .country(planDto.getCountry())
                .travel(travel)
                .build();
        return planRepository.save(plan).getId();
    }

    @Transactional
    @Override
    public Long updatePlan(String email, PlanUpdateDto planDto) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        planRepository.isAccessPlan(member.getId(), planDto.getPlanId());
        /* ------ */

        Plan currentPlan = planRepository.findById(planDto.getPlanId()).orElseThrow(PlanUpdateFailed::new);
        currentPlan.setStartDate(planDto.getStart_date());
        currentPlan.setEndDate(planDto.getEnd_date());
        currentPlan.setCountry(planDto.getCountry());

        return currentPlan.getId();
    }

    @Transactional
    public boolean changeStart(Long planId, PlanUpdateDto planDto){
        Plan currentPlan = planRepository.findById(planId).orElseThrow(PlanNotFound::new);
        boolean changePlanStart = !currentPlan.isTravelStart();
        currentPlan.setTravelStart(changePlanStart);

        return currentPlan.isTravelStart();
    }

    @Transactional
    @Override
    public void deletePlan(Long planId) {
        planRepository.deleteById(planId);
    }
}
