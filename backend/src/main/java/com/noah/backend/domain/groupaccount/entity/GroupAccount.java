package com.noah.backend.domain.groupaccount.entity;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.exchange.entity.Exchange;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE group_account SET is_deleted = TRUE WHERE group_account_id = ?")
public class GroupAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_account_id")
    private Long id;

    @Setter
    @Column(name = "used_amount")
    private int usedAmount;

    @Setter
    @Column(name = "target_amount")
    private int targetAmount;

    @Setter
    @Column(name = "target_date")
    private int targetDate;

    @Setter
    @Column(name = "per_amount")
    private int perAmount;

    @Setter
    @Column(name = "payment_date")
    private int paymentDate;

    @OneToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(mappedBy = "groupAccount")
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;
}
