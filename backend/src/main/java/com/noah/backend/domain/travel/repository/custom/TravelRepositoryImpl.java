package com.noah.backend.domain.travel.repository.custom;


import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.memberTravel.entity.QMemberTravel.memberTravel;
import static com.noah.backend.domain.notification.entity.QNotification.notification;
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
                        travel.id,
                        travel.title,
                        travel.memberTravelList,
                        travel.notificationList,
                        travel.groupAccount,
                        travel.plan,
                        travel.ticketList
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
    public Optional<TravelGetDto> getTravelSelect(Long TravelId) {
        TravelGetDto travelDto = query
                .select(constructor(TravelGetDto.class,
                        travel.id,
                        travel.title,
                        travel.memberTravelList,
                        travel.notificationList,
                        travel.groupAccount,
                        travel.plan,
                        travel.ticketList))
                .leftJoin(notification)
                .on(travel.id.eq(notification.travel.id))
                .leftJoin(memberTravel).on(travel.id.eq(memberTravel.travel.id))
                .leftJoin(ticket).on(travel.id.eq(ticket.travel.id))
                .fetchOne();

        if(travelDto != null){
//            List<MemberTravelListGetFromTravel> memberTravelList = query
//                    .select(Projections.constructor(MemberTravelListGetFromTravel.class,
//                            memberTravel.payment_amount
//                    ))
//                    .from(memberTravel)
//                    .where(memberTravel.travel.id.eq(TravelId))
//                    .fetch();
//            travelDto.setMemberTrabelList(memberTravelList);

//            List<NotificationListGetFromTravel> notificationListGetFromTravelList = query
//                    .select(Projections.constructor(NotificationListGetFromTravel.class,
//                            notification.type
//                            ))
//                    .from(notification)
//                    .where(notification.travel.id.eq(TravelId))
//                    .fetch();
//            travelDto.setNotificationList(notificationListGetFromTravelList);

            List<TicketListGetFromTravelDto> ticketListGetFromTravelDtoList = query
                    .select(Projections.constructor(TicketListGetFromTravelDto.class,
                            ticket.departure,
                            ticket.arrival,
                            ticket.dAirport,
                            ticket.aAirport,
                            ticket.dGate
                    ))
                    .from(ticket)
                    .where(ticket.travel.id.eq(TravelId))
                    .fetch();
            travelDto.setTicketList(ticketListGetFromTravelDtoList);
        }

        return Optional.ofNullable(travelDto);
    }
}
