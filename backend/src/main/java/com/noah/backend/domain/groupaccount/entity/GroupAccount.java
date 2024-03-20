package com.noah.backend.domain.groupaccount.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.trade.entity.Trade;
import com.noah.backend.domain.travel.entity.Travel;
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
public class GroupAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "owner")
    private Long ownerId;

    @Column(name = "bank")
    private String bank;

    @Setter
    @Column(name = "name")
    private String name;

    @Column(name = "account_number")
    private String accountNumber;

    @Setter
    @Column(name = "amount")
    private int amount;

    @Setter
    @Column(name = "used_amount")
    private int usedAmount;

    @Setter
    @Column(name = "target_amount")
    private int targetAmount;

    @Setter
    @Column(name = "per_amount")
    private int perAmount;

    @Setter
    @Column(name = "payment_date")
    private int paymentDate;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Travel travel;

    @Builder.Default
    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Trade> tradeList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Exchange exchange;
}
