package com.noah.backend.domain.exchange.entity;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE excahnge SET is_deleted = TRUE WHERE exchange_id = ?")
public class Exchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_id")
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Setter
    @Column(name = "exchange_amount")
    private int exchangeAmount;

    @Setter
    @Column(name = "target_exchange_currency")
    public String targetExchangeCurrency;

    @Setter
    @Column(name = "target_exchange_rate", nullable = true)
    private Double targetExchangeRate;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "group_account_id")
    private GroupAccount groupAccount;
}
