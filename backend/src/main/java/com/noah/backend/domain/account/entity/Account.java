package com.noah.backend.domain.account.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.trade.entity.Trade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE account SET is_deleted = TRUE WHERE account_id = ?")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "bank_name")
    private String bankName;        // 은행 이름

    @Column(name = "account_number")
    private String accountNumber;   // 계좌번호

    @Column(name = "type")
    private String type;            // 계좌 종류

    @Builder.Default
    @Setter
    @Column(name = "amount")
    private int amount = 0;         // 잔액

//    @Setter
//    @Column(name = "start_date")
//    private String startDate;       // 조회 시작 시점

    @Setter
    @Column(name = "end_date")
    private String endDate;         // 조회 종료 시점

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;          // 계좌 주인

    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Trade> tradeList = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<MemberTravel> memberTravelList = new ArrayList<>();
}
