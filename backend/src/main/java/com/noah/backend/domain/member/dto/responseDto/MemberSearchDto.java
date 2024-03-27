package com.noah.backend.domain.member.dto.responseDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSearchDto {
    private Long memberId;
    private String userKey;
}
