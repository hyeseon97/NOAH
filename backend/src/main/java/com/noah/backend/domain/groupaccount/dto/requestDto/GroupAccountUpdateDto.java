package com.noah.backend.domain.groupaccount.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupAccountUpdateDto {

    private Long id;
    private String name;
    private int Amount;
    private int usedAmount;
    private int targetAmount;
    private int perAmount;
    private int paymentDate;

}
