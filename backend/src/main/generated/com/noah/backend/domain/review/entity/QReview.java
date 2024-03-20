package com.noah.backend.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -764485916L;

    public static final QReview review = new QReview("review");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    public final ListPath<com.noah.backend.domain.comment.entity.Comment, com.noah.backend.domain.comment.entity.QComment> commentList = this.<com.noah.backend.domain.comment.entity.Comment, com.noah.backend.domain.comment.entity.QComment>createList("commentList", com.noah.backend.domain.comment.entity.Comment.class, com.noah.backend.domain.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath country = createString("country");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> expense = createNumber("expense", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.noah.backend.domain.image.entity.Image, com.noah.backend.domain.image.entity.QImage> imageList = this.<com.noah.backend.domain.image.entity.Image, com.noah.backend.domain.image.entity.QImage>createList("imageList", com.noah.backend.domain.image.entity.Image.class, com.noah.backend.domain.image.entity.QImage.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> people = createNumber("people", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

