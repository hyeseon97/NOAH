package com.noah.backend.domain.travel.dto.responseDto;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.ticket.entity.Ticket;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelGetListDto {

    private Long travelId;
    private String travelTitle;

    private Long groupAccountId;
    private Long groupAccountNumber;
    private int groupAccountAmount;
    private int groupAccountTargetAmount;

    private String currency;
    private int exchangeRate;
    private LocalDateTime createdAt;

    private Long reviewId;
    private String country;
    private int expense;

}
