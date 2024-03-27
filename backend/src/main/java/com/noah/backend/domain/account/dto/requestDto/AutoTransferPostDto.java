package com.noah.backend.domain.account.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoTransferPostDto {

    private Long travelId;
    private Long accountId;
    private boolean autoActivate;

}
