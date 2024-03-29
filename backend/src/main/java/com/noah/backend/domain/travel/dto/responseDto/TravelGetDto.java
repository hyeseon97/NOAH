package com.noah.backend.domain.travel.dto.responseDto;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetFromTravelDto;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import com.noah.backend.domain.ticket.entity.Ticket;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelGetDto {

    private Long travelId;
    private String title;
    private boolean isEnded;
//    @Setter
//    private GroupAccount account;
//    @Setter
    private PlanGetDto plan;
//    private Long account_id;
//    private Long plan_id;
//    @Setter
    @Setter
    private List<MemberTravelListGetFromTravelDto> memberTravelList;
//    @Setter
//    private List<NotificationGetDto> notificationList;
    @Setter
    private List<TicketListGetFromTravelDto> ticketList;


    public TravelGetDto(String title, Boolean isEnded) { // Boolean으로 변경
        this.title = title;
        this.isEnded = isEnded;
    }

}
