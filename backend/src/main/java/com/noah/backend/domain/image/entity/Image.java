package com.noah.backend.domain.image.entity;

import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.datailPlan.entity.DetailPlan;
import com.noah.backend.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE image SET is_deleted = TRUE WHERE image_id = ?")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "url", length = 3000)
    private String url;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = true)
    private Review review;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_plan_id", nullable = true)
    private DetailPlan detailPlan;

}
