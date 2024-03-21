package com.noah.backend.domain.account.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;          // 계좌 주인

    @Builder.Default
    @Column(name = "amount")
    private int amount = 0;         // 잔액
}
