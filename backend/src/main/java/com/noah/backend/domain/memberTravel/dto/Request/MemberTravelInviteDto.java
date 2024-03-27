package com.noah.backend.domain.memberTravel.dto.Request;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTravelInviteDto {

    private Long member_id;

    private Long travel_id;

}
