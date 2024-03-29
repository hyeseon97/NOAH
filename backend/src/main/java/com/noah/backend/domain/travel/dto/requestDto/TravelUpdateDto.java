package com.noah.backend.domain.travel.dto.requestDto;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.ticket.entity.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelUpdateDto {
//    private Long id;
    private String title;
    private boolean isEnded;
//    private List<MemberTravel> memberTrabelList;
//    private List<Notification> notificationList;
//    private GroupAccount groupAccount;
//    private Plan plan;
//    private List<Ticket> ticketList;
}
