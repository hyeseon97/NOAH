package com.noah.backend.domain.notification.service;

import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import java.io.IOException;
import java.util.List;

public interface NotificationService {

    List<NotificationGetDto> getNotification(String email);

    void paymentNotify();

    void exchangeNotify();

    Long inviteAccept(String email, Long notificationId);

    void inviteRefuse(String email, Long notificationId);

    boolean sendNotificationByToken(String token, String title, String body);

    void deleteNotification(String email, Long notificationId);
}
