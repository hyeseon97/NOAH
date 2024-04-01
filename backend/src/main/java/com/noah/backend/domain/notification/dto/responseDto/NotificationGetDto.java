package com.noah.backend.domain.notification.dto.responseDto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationGetDto {

    private Long id;
    private int type;
    private Long travelId;
    private String travelTitle;
    private String currency;
    private Double exchangeRate;
    private LocalDateTime createAt;

}
