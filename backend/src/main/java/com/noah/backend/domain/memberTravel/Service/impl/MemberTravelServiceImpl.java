package com.noah.backend.domain.memberTravel.Service.impl;

import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.Service.MemberTravelService;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelInviteDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelPostDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelUpdateDto;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberTravelServiceImpl implements MemberTravelService {

    private final MemberRepository memberReopsitory;
    private final TravelRepository travelRepository;
    private final MemberTravelRepository memberTravelRepository;

    @Override
    public Long createMemberTravel(MemberTravelPostDto memberTravelPostDto) {

        Travel travel = travelRepository.findById(memberTravelPostDto.getTravel_id()).orElseThrow(() -> new RuntimeException("여행 id 안넣냐?"));
        Member member = memberReopsitory.findById(memberTravelPostDto.getMember_id()).orElseThrow(() -> new RuntimeException("멤버 id 안넣냐?"));

        MemberTravel memberTravel =  MemberTravel.builder()
                .payment_amount(memberTravelPostDto.getPayment_amount())
                .member(member)
                .travel(travel)
                .build();

        MemberTravel saveMemberTravel = memberTravelRepository.save(memberTravel);

        return saveMemberTravel.getId();
    }

    @Override
    public Long updateMemberTravel(Long memberTravelId, MemberTravelUpdateDto memberTravelUpdateDto) {
        MemberTravel updateMemberTravel = memberTravelRepository.findById(memberTravelId)
                .orElseThrow(() -> new NotFoundException("정보를 찾을 수 없는디"));
        updateMemberTravel.setPayment_amount(memberTravelUpdateDto.getPayment_amount());

        memberTravelRepository.save(updateMemberTravel);

        return updateMemberTravel.getId();
    }

    @Override
    public Long inviteMember(MemberTravelInviteDto memberTravelInviteDto) {

        Member inviteMember = memberReopsitory.findById(memberTravelInviteDto.getTravel_id())
                .orElseThrow(() -> new NotFoundException("멤버를 찾을 수 없으요"));

        Travel inviteTravel = travelRepository.findById(memberTravelInviteDto.getMember_id())
                .orElseThrow(() -> new NotFoundException("여행 정보를 찾을 수 없슈"));

        MemberTravel newMemberTravel = MemberTravel.builder()
                .member(inviteMember)
                .travel(inviteTravel)
                .payment_amount(0)
                .build();

        return memberTravelRepository.save(newMemberTravel).getId();
    }


    @Override
    public void deleteResistMember(Long memberTravelId) {

        memberTravelRepository.deleteById(memberTravelId);
    }
}
