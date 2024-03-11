package com.noah.backend.domain.notification.entity;

import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor //아무것도없는 기본생성자
@AllArgsConstructor //모든 필드가 들어있는 생성자
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	@Column(name = "notification_id", nullable = false)
	private Long id;

	@Column(name = "type", nullable = false)
	private int type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id")
	private Member receiver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_id")
	private Travel travel;

}
