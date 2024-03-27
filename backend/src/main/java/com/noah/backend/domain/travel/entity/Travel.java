package com.noah.backend.domain.travel.entity;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE travel SET is_deleted = TRUE WHERE travel_id = ?")
public class Travel extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	@Column(name = "travel_id", nullable = false)
	private Long id;

	@Setter
	@Column(name = "title", nullable = false)
	private String title;

	@Setter
	@Column(name = "is_ended", nullable = false)
	private boolean isEnded;

	@OneToMany(mappedBy = "travel", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<MemberTravel> memberTravelList = new ArrayList<>();

	@OneToOne(mappedBy = "travel", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private GroupAccount groupAccount;

	@OneToOne(mappedBy = "travel", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private Plan plan;

	@OneToMany(mappedBy = "travel", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Ticket> ticketList = new ArrayList<>();

}
