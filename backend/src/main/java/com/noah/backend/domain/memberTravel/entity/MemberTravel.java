package com.noah.backend.domain.memberTravel.entity;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE member_travel SET is_deleted = TRUE WHERE member_travel_id = ?")
public class MemberTravel extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	@Column(name = "member_travel_id", nullable = false)
	private Long id;

	@Setter
	@Column(name = "payment_amount", nullable = false)
	private int payment_amount;

	@Setter
	@Column(name = "auto_transfer")
	private boolean autoTransfer = false;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_id")
	private Travel travel;

	@Setter
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = true)
	private Account account;
	
}
