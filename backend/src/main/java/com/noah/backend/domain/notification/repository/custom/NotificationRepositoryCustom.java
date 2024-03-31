package com.noah.backend.domain.notification.repository.custom;

import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import com.noah.backend.domain.notification.entity.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationRepositoryCustom {

   Optional<List<NotificationGetDto>> getNotification(Long memberId);

    Optional<Notification> findInviteNotification(Long receiverId, Long travelId);
}
