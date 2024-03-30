package com.noah.backend.domain.travel.dto.requestDto;

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
//    private Long id;
    //    private Long id;
    private String title;
    private boolean isEnded;
//    private List<MemberTravel> memberTravelList;
//    private List<Notification> notificationList;
//    private List<Ticket> ticketList;
//    private Account account;
//    private Plan plan;

    private Long account_id;
    private Long groupAccount_id;
    private Long plan_id;

}

