package com.noah.backend.domain.travel.entity;

import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	@Column(name = "travel_id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@OneToMany(mappedBy = "travel", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<MemberTravel> memberTravelList = new ArrayList<>();

	@OneToMany(mappedBy = "travel", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Notification> notificationList = new ArrayList<>();
}
