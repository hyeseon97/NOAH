package com.noah.backend.domain.travel.repository.custom;

import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.member.entity.QMember.member;
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
                        travel.account,
                        travel.plan,
                        travel.ticketList))
                .leftJoin(notification)
                .on(travel.id.eq(notification.id))
                .leftJoin(member).on(travel.id.eq(member.id))
                .leftJoin(ticket).on(travel.id.eq(ticket.id))
                .fetch();
        return Optional.ofNullable(travelDtos);
    }

    @Override
    public Optional<TravelGetDto> getTravelSelect(Long TravelId) {
        TravelGetDto travelDto = query
                .select(Projections.constructor(TravelGetDto.class,
                        travel.id,
                        travel.title,
                        travel.memberTravelList,
                        travel.notificationList,
                        travel.account,
                        travel.plan,
                        travel.ticketList))
                .leftJoin(notification)
                .on(travel.id.eq(notification.id))
                .leftJoin(member).on(travel.id.eq(member.id))
                .leftJoin(ticket).on(travel.id.eq(ticket.id))
                .fetchOne();
        return Optional.ofNullable(travelDto);
    }
}
