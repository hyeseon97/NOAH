package com.noah.backend.domain.trade.entity;

import com.noah.backend.domain.account.entity.Account;
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
@SQLDelete(sql = "UPDATE trade SET is_deleted = TRUE WHERE trade_id = ?")
public class Trade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long id;

    @Column(name = "type")
    private int type;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private int money;

    @Column(name = "balance")
    private int balance;

    @Column(name = "is_contained")
    private boolean isContained;

    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
