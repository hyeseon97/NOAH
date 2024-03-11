package com.noah.backend.domain.review.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE review SET is_deleted = TRUE WHERE review_id = ?")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    // 비용이 전체 얼마인지
    @Column(name = "expense")
    private int expense;

    // 어느 나라를 가는지
    @Column(name = "country")
    private String country;

    // 몇명이 가는지
    @Column(name = "people")
    private int people;

    // 여행 시작 날짜
    @Column(name = "start_date")
    private Date startDate;

    // 여행 끝 날짜
    @Column(name = "end_date")
    private Date endDate;

}
