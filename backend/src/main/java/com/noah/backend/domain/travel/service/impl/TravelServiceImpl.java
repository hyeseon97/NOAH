package com.noah.backend.domain.travel.service.impl;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.datailPlan.repository.DetailPlanRepository;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.memberTravel.Repository.MemberTravelRepository;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.plan.dto.responseDto.SimplePlan;
import com.noah.backend.domain.plan.repository.PlanRepository;
import com.noah.backend.domain.review.repository.ReviewRepository;
import com.noah.backend.domain.ticket.repository.TicketRepository;
import com.noah.backend.domain.trade.repository.TradeRepository;
import com.noah.backend.domain.trade.service.TradeService;
import com.noah.backend.domain.trade.service.impl.TradeServiceImpl;
import com.noah.backend.domain.travel.dto.responseDto.MyTravelGetDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelUpdateDto;
import com.noah.backend.domain.travel.entity.Travel;
import com.noah.backend.domain.travel.repository.TravelRepository;
import com.noah.backend.domain.travel.service.TravelService;
import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import com.noah.backend.global.exception.travelmember.MemberTravelNotFound;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Service
public class TravelServiceImpl implements TravelService {

    private final TicketRepository ticketRepository;
    private final PlanRepository planRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final ReviewRepository reviewRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;
    private final MemberTravelRepository memberTravelRepository;
    private final TradeServiceImpl tradeService;
    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;

    @Override
    public List<TravelGetListDto> getTravelList() {

        return travelRepository.getTravelList().orElseThrow(TravelNotFoundException::new);
    }

    @Override
    public TravelGetDto getTravelSelect(String email, Long travelId) throws IOException {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberTravelNotFound::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelId).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);
        Account account = accountRepository.findAccountBytravelId(travelId)
                                           .orElseThrow(AccountNotFoundException::new);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

        String createdDate = account.getCreatedAt().format(dateTimeFormatter);
        String createdTime = account.getCreatedAt().format(timeFormatter);

        // 조회 시점
        String endDate = LocalDateTime.now().format(dateTimeFormatter);

//        tradeService.fetchAndSaveTradeHistory(account, createdDate, endDate);

        TravelGetDto travelGetDto = travelRepository.getTravelSelect(travelId).orElseThrow(TravelNotFoundException::new);

        int depositTotal = tradeRepository.getTotalDeposit(account.getId()).orElse(0);
        travelGetDto.setDepositTotal(depositTotal);

//        // 계획이 생성되지 않았을 땐 바로 반환
//        if(travelGetDto.getPlanId() == null) {
//            return travelGetDto;
//        }
//
//        List<SimplePlan> simplePlanList = planRepository.getSimplePlan(travelGetDto.getPlanId()).orElse(null);

//        List<List<SimplePlan>> res = new ArrayList<>();
//        int index = -1;
//        int day = 0;
//        int seq = 0;
//        for(SimplePlan s : simplePlanList){
//            if(day != s.getDay()){
//                res.add(new ArrayList<>());
//                index++;
//                day++;
//                seq = 0;
//            }
//            if(seq == s.getSequence()){
//                continue;
//            }
//
//            res.get(index).add(SimplePlan.builder()
//                                   .day(s.getDay())
//                                   .sequence(s.getSequence())
//                                   .place(s.getPlace())
//                                   .imageId(s.getImageId())
//                                   .imageUrl(s.getImageUrl())
//                                   .build());
//            seq++;
//        }
//
//        travelGetDto.setSimplePlanList(res);

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
    public Long updateTravel(String email, TravelUpdateDto travelDto) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberTravelNotFound::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelDto.getTravelId()).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        Travel travel = travelRepository.findById(travelDto.getTravelId()).orElseThrow(TravelNotFoundException::new);
        travel.setTitle(travelDto.getTitle());

        return travel.getId();
    }

    @Override
    public void deleteTravel(String email, Long travelId) {

        /* 접근권한 */
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberTravelNotFound::new);
        MemberTravel memberTravel = memberTravelRepository.findByTravelIdAndMemberId(member.getId(), travelId).orElseThrow(
            MemberTravelAccessException::new);
        /* ------ */

        travelRepository.deleteById(travelId);
    }

    @Override
    public List<MyTravelGetDto> getTravelAll(String email) {

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberTravelNotFound::new);
        List<MyTravelGetDto> list = travelRepository.getTravelAll(member.getId()).orElse(null);
        for(int i=0; i<list.size(); i++){
            MyTravelGetDto currentMyTravelGetDto = list.get(i);
            if(currentMyTravelGetDto.getReviewId()==null){
                Long currentTravelId = currentMyTravelGetDto.getTravelId();
                currentMyTravelGetDto.setPeople(memberTravelRepository.getTotalPeople(currentTravelId).orElse(0));
            }
        }
        return list;
    }
}
