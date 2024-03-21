package com.noah.backend.domain.exchange.entity;

//import com.noah.backend.domain.account.entity.Account;
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
    private int currency;

    @Column(name = "exchange_amount")
    private int exchangeAmount;

//    @OneToOne(mappedBy = "exchange", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
//    @JoinColumn(name = "account_id")
//    private Account account;
}
