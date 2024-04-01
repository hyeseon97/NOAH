package com.noah.backend.domain.review.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.comment.entity.Comment;
import com.noah.backend.domain.image.entity.Image;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE review SET is_deleted = TRUE WHERE review_id = ?")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    // 비용이 전체 얼마인지
    @Setter
    @Column(name = "expense")
    private int expense;

    // 어느 나라를 가는지
    @Setter
    @Column(name = "country")
    private String country;

    // 몇명이 가는지
    @Setter
    @Column(name = "people")
    private int people;

    // 여행 시작 날짜
    @Setter
    @Column(name = "start_date")
    private Date startDate;

    // 여행 끝 날짜
    @Setter
    @Column(name = "end_date")
    private Date endDate;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Image> imageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Comment> commentList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

}
