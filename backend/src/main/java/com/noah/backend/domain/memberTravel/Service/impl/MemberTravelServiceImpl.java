package com.noah.backend.domain.memberTravel.Service.impl;

import com.noah.backend.domain.account.dto.requestDto.AutoTransferPostDto;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.Service.MemberTravelService;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelInviteDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelPostDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelUpdateDto;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.notification.repository.NotificationRepository;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelNotFoundException;
import com.noah.backend.global.exception.travel.TravelMemberNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import com.noah.backend.global.exception.travelmember.MemberTravelNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberTravelServiceImpl implements MemberTravelService {

    private final MemberRepository memberReopsitory;
    private final TravelRepository travelRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    @Override
    public Long createMemberTravel(MemberTravelPostDto memberTravelPostDto) {

        Travel travel = travelRepository.findById(memberTravelPostDto.getTravel_id()).orElseThrow(TravelNotFoundException::new);
        Member member = memberReopsitory.findById(memberTravelPostDto.getMember_id()).orElseThrow(MemberTravelNotFound::new);

        MemberTravel memberTravel = MemberTravel.builder()
                                                .payment_amount(memberTravelPostDto.getPayment_amount())
                                                .autoTransfer(false)
                                                .member(member)
                                                .travel(travel)
                                                .account(null)
                                                .build();

        MemberTravel saveMemberTravel = memberTravelRepository.save(memberTravel);

        return saveMemberTravel.getId();
    }

    @Override
    public Long updateMemberTravel(Long memberTravelId, MemberTravelUpdateDto memberTravelUpdateDto) {
        MemberTravel updateMemberTravel = memberTravelRepository.findById(memberTravelId)
                                                                .orElseThrow(MemberTravelNotFound::new);
        updateMemberTravel.setPayment_amount(memberTravelUpdateDto.getPayment_amount());

        memberTravelRepository.save(updateMemberTravel);

        return updateMemberTravel.getId();
    }

    @Transactional
    @Override
    public Long inviteMember(MemberTravelInviteDto memberTravelInviteDto) {

        // 초대 요청을 보내기 = 알림 보내기
        // 멤버트래블 테이블에 데이터를 저장하는건 요청 받은 사람이 수락하면 저장할 것임
        Member receiver = memberReopsitory.findById(memberTravelInviteDto.getMember_id())
                                          .orElseThrow(MemberNotFoundException::new);
        Travel travel = travelRepository.findById(memberTravelInviteDto.getTravel_id())
                                        .orElseThrow(TravelMemberNotFoundException::new);

        Notification notification = Notification.builder()
                                                .receiver(receiver)
                                                .type(1)
                                                .travelId(memberTravelInviteDto.getTravel_id())
                                                .travelTitle(travel.getTitle())
                                                .build();

        Notification savedNotification = notificationRepository.save(notification);

        // 파이어베이스 푸쉬 알림


        return savedNotification.getId();
    }


    @Override
    public void deleteResistMember(Long memberTravelId) {

        memberTravelRepository.deleteById(memberTravelId);
    }

    @Transactional
    @Override
    public void setAutoTransfer(String email, AutoTransferPostDto autoTransferPostDto) {

        Member member = memberReopsitory.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(autoTransferPostDto.getTravelId(), member.getId()).orElseThrow(
            MemberTravelNotFoundException::new);

        Account account = accountRepository.findById(autoTransferPostDto.getAccountId()).orElseThrow(AccountNotFoundException::new);

        memberTravel.setAccount(account);
        memberTravel.setAutoTransfer(autoTransferPostDto.isAutoActivate());

    }

    @Transactional
    @Override
    public void deleteAutoTransfer(String email, Long travelId) {

        Member member = memberReopsitory.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(travelId, member.getId()).orElseThrow(
            MemberTravelNotFoundException::new);

        memberTravel.setAccount(null);

    }
}
