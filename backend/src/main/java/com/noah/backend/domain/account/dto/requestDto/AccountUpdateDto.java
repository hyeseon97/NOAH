package com.noah.backend.domain.account.dto.requestDto;

import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.trade.entity.Trade;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountUpdateDto {

    private Long id;
    private String name;
    private int deposit;
    private int withdraw;
    private int targetAmount;
    private int perAmount;
    private int paymentDate;

}
