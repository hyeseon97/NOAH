package com.noah.backend.domain.memberTravel.dto.Response;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTravelGetDto {

    private Long member_id;

    private Long travel_id;

}
