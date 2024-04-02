package com.noah.backend.domain.memberTravel.dto.Response;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTravelListGetDto {

    private int payment_amount;

    private Long member_id;

    private String memberNickname;

    private String memberName;
//    private Long travel_id;

}
