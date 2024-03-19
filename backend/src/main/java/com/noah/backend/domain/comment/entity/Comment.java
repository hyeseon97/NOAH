package com.noah.backend.domain.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE comment SET is_deleted = TRUE WHERE detailPlan_id = ?")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    @Column(name = "content")
    private String content;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Long memberId;


    @ManyToOne
    @JoinColumn(name = "review_id")
    private Long reviewId;


}
