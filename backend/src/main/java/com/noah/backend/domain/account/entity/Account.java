package com.noah.backend.domain.account.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.trade.entity.Trade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE account SET is_deleted = TRUE WHERE account_id = ?")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "bank")
    private String bank;

    @Column(name = "name")
    private String name;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "deposit")
    private int deposit;

    @Column(name = "withdraw")
    private int withdraw;

    @Column(name = "target_amount")
    private int targetAmount;

    @Column(name = "per_amount")
    private int perAmount;

    @Column(name = "payment_date")
    private int paymentDate;

    @OneToOne(fetch = FetchType.LAZY)
//    private Travel travel;

    @Builder.Default
    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Trade> tradeList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Exchange exchange;
}
