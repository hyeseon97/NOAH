package com.noah.backend.domain.plan.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.datailPlan.entity.DetailPlan;
import com.noah.backend.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE plan SET is_deleted = TRUE WHERE plan_id = ?")
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
//    이건 생각을 조금 더 해야함
//    @OneToMany(mappedBy = "plan")
    private Long id;

    // 시작 날짜
    @Column(name = "start_date")
    private Date startDate;

    // 끝 날짜
    @Column(name = "end_date")
    private Date endDate;

    //여행시작버튼을 누른 이후
    @Column(name = "travel_start")
    private boolean travelStart;

    @Column(name="country")
    private String country;

    //여행ID 외래키
    @OneToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    //연관관계 설정
    @OneToMany(mappedBy = "plan", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<DetailPlan> detailPlan = new ArrayList<>();
}
