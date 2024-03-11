package com.noah.backend.domain.notification.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor //아무것도없는 기본생성자
@AllArgsConstructor //모든 필드가 들어있는 생성자
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE notification SET is_deleted = TRUE WHERE notification_id = ?")
public class Notification extends BaseEntity {
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
