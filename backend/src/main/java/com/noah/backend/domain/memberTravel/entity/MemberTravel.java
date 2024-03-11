package com.noah.backend.domain.memberTravel.entity;

import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MemberTravel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	@Column(name = "member_travel_id", nullable = false)
	private Long id;

	@Column(name = "payment_amount", nullable = false)
	private int payment_amount;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_id")
	private Travel travel;


}
