package com.noah.backend.domain.memberTravel.dto.Request;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTravelInviteDto {

    private String email;
    private Long travelId;

}
