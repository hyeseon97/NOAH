package com.noah.backend.domain.travel.repository.custom;


import com.noah.backend.domain.datailPlan.dto.responseDto.DetailPlanListGetFromPlanDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetFromTravelDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.datailPlan.entity.QDetailPlan.detailPlan;
import static com.noah.backend.domain.groupaccount.entity.QGroupAccount.groupAccount;
import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;
import static com.noah.backend.domain.notification.entity.QNotification.notification;
import static com.noah.backend.domain.plan.entity.QPlan.plan;
import static com.noah.backend.domain.ticket.entity.QTicket.ticket;
import static com.noah.backend.domain.travel.entity.QTravel.travel;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class TravelRepositoryImpl implements TravelRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Optional<List<TravelGetListDto>> getTravelList() {
        List<TravelGetListDto> travelDtos = query
                .select(constructor(TravelGetListDto.class,
//                        travel.id,
                        travel.title,
                        travel.isEnded
//                        travel.memberTravelList,
//                        travel.notificationList,
//                        travel.groupAccount.id,
//                        travel.plan.id
//                        travel.ticketList
                        ))
//                .leftJoin(notification)
//                .on(travel.id.eq(notification.travel.id))
//                .leftJoin(memberTravel).on(travel.id.eq(memberTravel.travel.id))
//                .leftJoin(ticket).on(travel.id.eq(ticket.travel.id))
                .from(travel)
                .fetch();

        return Optional.ofNullable(travelDtos);
    }

    @Override
    public Optional<TravelGetDto> getTravelSelect(Long travelId) {
        TravelGetDto travelDto = query
                .select(constructor(TravelGetDto.class,
//                        travel.id,
                        travel.title,
                        travel.isEnded
//                        travel.memberTravelList,
//                        travel.notificationList,
//                        travel.ticketList
//                        travel.groupAccount.id,
//                        travel.plan.id
                        ))
//                .leftJoin(notification)
//                .on(travel.id.eq(notification.travel.id))
//                .leftJoin(memberTravel).on(travel.id.eq(memberTravel.travel.id))
//                .leftJoin(ticket).on(travel.id.eq(ticket.travel.id))
                .from(travel)
                .where(travel.id.eq(travelId))
                .fetchOne();

        if(travelDto != null){
            List<MemberTravelListGetFromTravelDto> memberTravelList = query
                    .select(Projections.constructor(MemberTravelListGetFromTravelDto.class,
                            memberTravel.payment_amount,
                            memberTravel.member.id
//                            memberTravel.member
                    ))
                    .from(memberTravel)
                    .where(memberTravel.travel.id.eq(travelId))
                    .fetch();
            travelDto.setMemberTravelList(memberTravelList);

//            List<NotificationListGetFromTravel> notificationListGetFromTravelList = query
//                    .select(Projections.constructor(NotificationListGetFromTravel.class,
//                            notification.type
//                            ))
//                    .from(notification)
//                    .where(notification.travel.id.eq(TravelId))
//                    .fetch();
//            travelDto.setNotificationList(notificationListGetFromTravelList);

            PlanGetDto planGetDto = query
                    .select(Projections.constructor(PlanGetDto.class,
                            plan.id,
                            plan.startDate,
                            plan.endDate,
                            plan.travelStart,
                            plan.country
                            ))
                    .from(plan)
                    .where(plan.travel.id.eq(travelId))
                    .fetchOne();
            if(planGetDto != null){
                List<DetailPlanListGetFromPlanDto> detailDtos = query
                        .select(Projections.constructor(DetailPlanListGetFromPlanDto.class,
                                detailPlan.day,
                                detailPlan.sequence,
                                detailPlan.place,
                                detailPlan.pinX,
                                detailPlan.pinY,
                                detailPlan.memo,
                                detailPlan.time
                                ))
                        .from(detailPlan)
                        .leftJoin(plan)
                        .on(detailPlan.plan.id.eq(planGetDto.getPlan_id()))
                        .fetch();
                planGetDto.setDetailPlanList(detailDtos);
            }
            travelDto.setPlan(planGetDto);

            List<TicketListGetFromTravelDto> ticketListGetFromTravelDtoList = query
                    .select(Projections.constructor(TicketListGetFromTravelDto.class,
                            ticket.departure,
                            ticket.arrival,
                            ticket.dAirport,
                            ticket.aAirport,
                            ticket.dGate
                    ))
                    .from(ticket)
                    .where(ticket.travel.id.eq(travelId))
                    .fetch();
            travelDto.setTicketList(ticketListGetFromTravelDtoList);
        }

        return Optional.ofNullable(travelDto);
    }

    @Override
    public Optional<List<Long>> findTravelPaymentDateIsToday(int todayDate) {
        return Optional.ofNullable(query.select(travel.id)
                                       .from(travel)
                                       .leftJoin(groupAccount).on(groupAccount.travel.id.eq(travel.id))
                                       .where(groupAccount.paymentDate.eq(todayDate).and(travel.isDeleted.eq(false)))
                                       .fetch());
    }

}
