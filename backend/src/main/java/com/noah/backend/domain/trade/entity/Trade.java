package com.noah.backend.domain.trade.entity;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan
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

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "cost")
    private int cost;

    @Column(name = "amount")
    private int amount;

    @Column(name = "consume_type")
    private String consumeType;

    @Column(name = "is_contained")
    private boolean isContained;

    @ManyToOne
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private GroupAccount groupAccount;

}