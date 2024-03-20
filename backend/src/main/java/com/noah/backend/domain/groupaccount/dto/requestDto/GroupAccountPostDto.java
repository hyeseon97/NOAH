package com.noah.backend.domain.groupaccount.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupAccountPostDto {

    private Long travelId;          // 여행 정보
    private Long ownerId;           // 소유주 정보
    private String bank;            // 은행명
    private String accountNumber;   // 계좌번호
}
