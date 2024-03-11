package com.noah.backend.domain.amount.entity;

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
@SQLDelete(sql = "UPDATE amount SET is_deleted = TRUE WHERE amount_id = ?")
public class Amount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amount_id")
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

    @Column(name = "travel_id")
    private Long travelId;

}
