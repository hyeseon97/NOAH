package com.noah.backend.domain.notification.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.noah.backend.domain.apis.dto.CurrencyDto;
import com.noah.backend.domain.apis.service.ForeignCurrencyService;
import com.noah.backend.domain.exchange.dto.responseDto.TargetExchangeRate;
import com.noah.backend.domain.exchange.repository.ExchangeRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.notification.repository.NotificationRepository;
import com.noah.backend.domain.notification.service.NotificationService;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.notification.NotificationAccessException;
import com.noah.backend.global.exception.notification.NotificationNotFoundException;
import com.noah.backend.global.exception.notification.NotificationSendFailedException;
import com.noah.backend.global.exception.travel.TravelMemberNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.N;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
    private final TravelRepository travelRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final FirebaseMessaging firebaseMessaging;
    private final ForeignCurrencyService foreignCurrencyService;
    private final ExchangeRepository exchangeRepository;

    @Override
    public List<NotificationGetDto> getNotification(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        List<NotificationGetDto> notificationList = notificationRepository.getNotification(member.getId()).orElse(null);
        if(notificationList == null || notificationList.size()==0){
            return null;
        }
        return notificationList;
    }

    @Transactional
    @Scheduled(cron = "${schedule.notify_pay}")
    @Override
    public void paymentNotify() {
        System.out.println("납입일 알림을 보낼 시간");

        int todayDate = LocalDate.now().getDayOfMonth();
        List<Long> travelList = travelRepository.findTravelPaymentDateIsToday(todayDate).orElse(null);

        if(travelList == null){
            System.out.println("납입일이 오늘인 여행이 없음");
            return;
        }

        // 납입일이 오늘인 여행 반복
        for(int i = 0;i<travelList.size();i++){
            Long travelId = travelList.get(i);
            Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);
            List<Long> memberList = memberRepository.findByTravelId(travelId).orElseThrow(TravelMemberNotFoundException::new);

            // 여행에 포함되어 있는 멤버 반복
            for(int j = 0;j<memberList.size();j++){
                Member member = memberRepository.findById(memberList.get(j)).orElseThrow(MemberNotFoundException::new);

                // 알림 테이블에 데이터 저장
                Notification notification = Notification.builder()
                    .type(2)
                    .travelId(travelId)
                    .travelTitle(travel.getTitle())
                    .receiver(member)
                    .build();

                notificationRepository.save(notification);

                // 파이어베이스로 푸쉬알림
                String title = "NOAH";
                String body = "[ " + travel.getTitle() + " ] 여행의 납입일 입니다.";
                if(!sendNotificationByToken(member.getNotificationToken(), title, body)) {
                    throw new NotificationSendFailedException();
                }

            }
        }
    }

    @Transactional
    @Scheduled(cron = "${schedule.notify_exchange}")
    @Override
    public void exchangeNotify() {

        // 현재 환율 정보
        CurrencyDto currency = foreignCurrencyService.getExchangeRate();

        // 목표환율이 현재 환율에 도달한 여행&멤버 리스트 조회
        List<TargetExchangeRate> list = exchangeRepository.getTargetExchangeRateTravel(currency).orElse(null);

        for(TargetExchangeRate t : list){
            System.out.println("목표환율에 도달한 여행이 있어");
            Member receiver = memberRepository.findById(t.getMemberId()).orElseThrow(MemberNotFoundException::new);


            /* 알림 DB에 저장 */
            Notification notification = Notification.builder()
                .receiver(receiver)
                .type(3)
                .travelId(t.getTravelId())
                .travelTitle(t.getTravelTitle())
                .build();

            String body = null;

            if(t.getTargetExchangeCurrency().equals("USD")){
                notification.setCurrency("USD");
                notification.setExchangeRate(currency.getBuyDollar());
                body = "[ " + t.getTravelTitle() + " ] 여행의 목표 환율에 도달했습니다.  USD:" + currency.getBuyDollar();
            } else if(t.getTargetExchangeCurrency().equals("JPY")){
                notification.setCurrency("JPY");
                notification.setExchangeRate(currency.getBuyYen());
                body = "[ " + t.getTravelTitle() + " ] 여행의 목표 환율에 도달했습니다.  JPY:" + currency.getBuyYen();
            } else if(t.getTargetExchangeCurrency().equals("CNY")){
                notification.setCurrency("CNY");
                notification.setExchangeRate(currency.getBuyYuan());
                body = "[ " + t.getTravelTitle() + " ] 여행의 목표 환율에 도달했습니다.  CNY:" + currency.getBuyYuan();
            } else if(t.getTargetExchangeCurrency().equals("EUR")){
                notification.setCurrency("EUR");
                notification.setExchangeRate(currency.getBuyEuro());
                body = "[ " + t.getTravelTitle() + " ] 여행의 목표 환율에 도달했습니다.  EUR:" + currency.getBuyEuro();
            }

            notificationRepository.save(notification);

            /* 푸시알림 보내기 */
            String title = "NOAH";
            if(!sendNotificationByToken(receiver.getNotificationToken(), title, body)) {
                throw new NotificationSendFailedException();
            }
        }

    }

    @Transactional
    @Override
    public Long inviteAccept(String email, Long notificationId) {

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        // 알림 아이디로 알림 찾아와서 여행 아이디 구하기
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);

        /* 접근권한 */
        if(!notification.getReceiver().getId().equals(member.getId())){
            throw new NotificationAccessException();
        }
        /* ------ */


        // 멤버랑 여행 엔티티 가져와서 멤버트래블 만들고 저장하기
        Travel travel = travelRepository.findById(notification.getTravelId()).orElseThrow(TravelNotFoundException::new);
        MemberTravel memberTravel = MemberTravel.builder()
            .member(member)
            .travel(travel)
            .payment_amount(0)
            .build();
        memberTravelRepository.save(memberTravel);

        // 초대알림은 삭제
        notificationRepository.delete(notification);

        return travel.getId();
    }

    @Transactional
    @Override
    public void inviteRefuse(String email, Long notificationId) {
        // 알림 아이디로 알림 엔티티 찾아와서 삭제하기
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        if(!notification.getReceiver().getId().equals(member.getId())){
            throw new NotificationAccessException();
        }
        /* ------ */

        notificationRepository.delete(notification);

    }

    // 파이어베이스로 푸시알림
    // 알림을 보낼 사용자의 파이어베이스토큰 - token
    // 푸시알림의 제목이랑 내용 - title, body
    @Transactional
    @Override
    public boolean sendNotificationByToken(String token, String title, String body) {

        // 알람 허용을 안한 사용자는 토큰이 null 이기 때문에 바로 종료
        if(token == null) return true;

        com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification.builder()
                                                                                                            .setTitle(title)
                                                                                                            .setBody(body).build();


        Message message = Message.builder()
                                 .setToken(token)
                                 .setNotification(notification)
                                 .build();

        try {
            firebaseMessaging.send(message);
            return true;
        } catch (FirebaseMessagingException e) {
            return false;
        }

    }

    @Transactional
    @Override
    public void deleteNotification(String email, Long notificationId) {
        // 알림 아이디로 알림 엔티티 찾아와서 삭제하기
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        if(!notification.getReceiver().getId().equals(member.getId())){
            throw new NotificationAccessException();
        }
        /* ------ */
        notificationRepository.delete(notification);
    }

}
