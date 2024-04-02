package com.noah.backend.domain.datailPlan.service.impl;

import com.noah.backend.domain.datailPlan.dto.requestDto.DetailPlanPostDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanDto;
import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListDto;
import com.noah.backend.domain.datailPlan.entity.DetailPlan;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.datailPlan.service.DetailPlanService;
import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.image.repository.ImageRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.global.exception.detailplan.DetailPlanNotFound;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.plan.PlanNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Service
public class DetailPlanServiceImpl implements DetailPlanService {

    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final MemberRepository memberRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final ImageRepository imageRepository;

    @Override
    public DetailPlanListDto getDetailPlanList(String email, Long planId) {

        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFound::new);

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), plan.getTravel().getId())
                                                          .orElseThrow(
                                                              MemberTravelAccessException::new);
        /* ------ */

        List<DetailPlanDto> detailPlanDtoList = detailPlanRepository.getDetailPlanList(planId)
                                                                    .orElseThrow(DetailPlanNotFound::new);
        DetailPlanListDto detailPlanListDto = DetailPlanListDto.builder()
                                                               .planId(planId)
                                                               .detailPlanList(detailPlanDtoList).build();
        return detailPlanListDto;
    }

    @Override
    public Long createDetailPlan(String email, Long planId, DetailPlanPostDto detailPlan) {

        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFound::new);

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), plan.getTravel().getId())
                                                          .orElseThrow(
                                                              MemberTravelAccessException::new);
        /* ------ */


        DetailPlan NewDetailPlan = DetailPlan.builder()
                                             .plan(plan)
                                             .day(detailPlan.getDay())
                                             .sequence(detailPlan.getSequence())
                                             .place(detailPlan.getPlace())
                                             .pinX(detailPlan.getPinX())
                                             .pinY(detailPlan.getPinY())
                                             .memo(detailPlan.getMemo())
                                             .time(detailPlan.getTime())
                                             .build();
        DetailPlan savedDetailPlan = detailPlanRepository.save(NewDetailPlan);

        Image image = Image.builder()
                           .url(detailPlan.getImageUrl())
                           .detailPlan(savedDetailPlan)
                           .build();
        imageRepository.save(image);

        return savedDetailPlan.getId();
    }

    @Override
    public Long updateDetailPlan(String email, DetailPlanListDto detailPlanDto) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), detailPlanDto.getPlanId())
                                                          .orElseThrow(
                                                              MemberTravelAccessException::new);
        /* ------ */

        for (DetailPlanDto dPlan : detailPlanDto.getDetailPlanList()) {

            DetailPlan detailPlan = detailPlanRepository.findById(dPlan.getDetailPlanId()).orElseThrow(DetailPlanNotFound::new);
            detailPlan.setDay(dPlan.getDay());
            detailPlan.setSequence(dPlan.getSequence());
            detailPlan.setPlace(dPlan.getPlace());
            detailPlan.setPinX(dPlan.getPinX());
            detailPlan.setPinY(dPlan.getPinY());
            detailPlan.setMemo(dPlan.getMemo());
            detailPlan.setTime(dPlan.getTime());

        }

        return detailPlanDto.getPlanId();
    }

    @Override
    public void deleteDetailPlan(String email, Long detailId) {

        DetailPlan detailPlan = detailPlanRepository.findById(detailId).orElseThrow(DetailPlanNotFound::new);
        Plan plan = planRepository.findById(detailPlan.getPlan().getId()).orElseThrow(PlanNotFound::new);

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), plan.getTravel().getId())
                                                          .orElseThrow(
                                                              MemberTravelAccessException::new);
        /* ------ */

        detailPlanRepository.deleteById(detailId);
    }

}
