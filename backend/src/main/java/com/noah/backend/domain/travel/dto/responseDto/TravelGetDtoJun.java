package com.noah.backend.domain.travel.dto.responseDto;

import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetFromTravelDto;
import com.noah.backend.domain.plan.dto.responseDto.PlanGetDto;
import com.noah.backend.domain.ticket.dto.responseDto.TicketListGetFromTravelDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelGetDtoJun {

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


    public TravelGetDtoJun(String title, Boolean isEnded) { // Boolean으로 변경
        this.title = title;
        this.isEnded = isEnded;
    }

}
