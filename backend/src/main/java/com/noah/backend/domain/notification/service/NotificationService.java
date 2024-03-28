package com.noah.backend.domain.notification.service;

import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import java.io.IOException;
import java.util.List;

public interface NotificationService {

    void saveToken(String email, String token);

    List<NotificationGetDto> getNotification(String email);

    void paymentNotify();

    Long inviteAccept(String email, Long notificationId);

    void inviteRefuse(String email, Long notificationId);

    boolean sendNotificationByToken(String token, String title, String body);
}
