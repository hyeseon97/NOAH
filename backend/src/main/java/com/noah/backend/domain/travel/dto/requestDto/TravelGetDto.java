package com.noah.backend.domain.travel.dto.requestDto;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelGetDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private boolean isEnded;
    @Setter
    private GroupAccount account;
    @Setter
    private Plan plan;
//    @Setter
//    private List<memberTravelDto> memberTrabelList;
//    @Setter
//    private List<NotificationGetDto> notificationList;
    @Setter
    private List<TicketListGetFromTravelDto> ticketList;
}
