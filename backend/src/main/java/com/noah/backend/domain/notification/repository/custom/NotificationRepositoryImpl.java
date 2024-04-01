package com.noah.backend.domain.notification.repository.custom;

import static com.noah.backend.domain.notification.entity.QNotification.notification;

import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import com.noah.backend.domain.notification.entity.Notification;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Optional<List<NotificationGetDto>> getNotification(Long memberId) {
        return Optional.ofNullable(query.select(Projections.constructor(NotificationGetDto.class, notification.id, notification.type, notification.travelId, notification.travelTitle, notification.currency, notification.exchangeRate, notification.createdAt))
                                       .from(notification)
                                       .where(notification.receiver.id.eq(memberId).and(notification.isDeleted.eq(false)))
                                       .fetch());
    }

    @Override
    public Optional<Notification> findInviteNotification(Long receiverId, Long travelId) {
        return Optional.ofNullable(query.select(notification)
                                       .from(notification)
                                       .where(notification.type.eq(1).and(notification.receiver.id.eq(receiverId)).and(notification.travelId.eq(travelId)).and(notification.isDeleted.eq(false)))
                                       .fetchOne());
    }
}
