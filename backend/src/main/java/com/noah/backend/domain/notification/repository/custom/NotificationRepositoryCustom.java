package com.noah.backend.domain.notification.repository.custom;

import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import java.util.List;
import java.util.Optional;

public interface NotificationRepositoryCustom {

   Optional<List<NotificationGetDto>> getNotification(Long memberId);

}
