package com.noah.backend.domain.datailPlan.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.image.entity.Image;
import com.noah.backend.domain.plan.entity.Plan;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE detailPlan SET is_deleted = TRUE WHERE detailPlan_id = ?")
public class DetailPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    // 몇일차인지
    @Column(name = "day")
    @Setter
    private int day;

    // 정해진 날 어디를 갈지 순서
    @Column(name = "sequence")
    @Setter
    private int sequence;

    // 방문 장소
    @Column(name = "place")
    @Setter
    private String place;

    // 구글맵 핀 찍을 위도
    @Column(name = "pinX")
    @Setter
    private double pinX;

    // 구글맵 핀 찍을 경도
    @Column(name = "pinY")
    @Setter
    private double pinY;

    // 메모 남기기 기능
    @Column(name = "memo")
    @Setter
    private String memo;

    // 시간
    @Column(name = "time")
    @Setter
    private String time;

    // planId 외래키
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Builder.Default
    @OneToMany(mappedBy = "detailPlan", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Image> imageList = new ArrayList<>();
}
