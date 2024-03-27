package com.noah.backend.domain.notification.controller;


import com.noah.backend.domain.notification.dto.requestDto.SaveTokenDto;
import com.noah.backend.domain.notification.dto.responseDto.NotificationGetDto;
import com.noah.backend.domain.notification.service.NotificationService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification 컨트롤러", description = "Notification Controller API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final ApiResponse response;
    private final NotificationService notificationService;

    @Operation(summary = "파이어베이스 토큰 저장", description = "알림을 위한 파이어베이스 토큰 저장")
    @PostMapping("/token")
    public ResponseEntity<?> saveToken(@Parameter(hidden = true) Authentication authentication,
                                       @RequestBody SaveTokenDto saveTokenDto) {
        notificationService.saveToken(authentication.getName(), saveTokenDto.getToken());
        return response.success(ResponseCode.NOTIFICATION_TOKEN_SAVED);
    }

    @Operation(summary = "알림 조회", description = "나에게 온 알림 리스트 조회")
    @GetMapping
    public ResponseEntity<?> getNotification(@Parameter(hidden = true) Authentication authentication){
        List<NotificationGetDto> notificationList = notificationService.getNotification(authentication.getName());
        return response.success(ResponseCode.NOTIFICATION_LIST_FETCHED, notificationList);
    }

    @Operation(summary = "여행 초대 알림 수락", description = "여행 초대 알림 수락")
    @GetMapping("/accept/{notificationId}")
    public ResponseEntity<?> inviteAccept(@Parameter(hidden = true) Authentication authentication,
                                          @PathVariable(name = "notificationId") Long notificationId) {
        Long travelId = notificationService.inviteAccept(authentication.getName(), notificationId);
        return response.success(ResponseCode.INVITE_ACCEPT, travelId);
    }

    @Operation(summary = "여행 초대 알림 거절", description = "여행 초대 알림 거절")
    @GetMapping("/refuse/{notificationId}")
    public ResponseEntity<?> inviteRefuse(@Parameter(hidden = true) Authentication authentication,
                                          @PathVariable(name = "notificationId") Long notificationId) {
        notificationService.inviteRefuse(authentication.getName(), notificationId);
        return response.success(ResponseCode.INVITE_REFUSE);
    }



}
