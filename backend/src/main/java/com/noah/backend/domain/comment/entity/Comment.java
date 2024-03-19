package com.noah.backend.domain.comment.entity;

import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE comment SET is_deleted = TRUE WHERE detailPlan_id = ?")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    @Setter
    @Column(name = "content")
    private String content;


    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Setter
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;


}
