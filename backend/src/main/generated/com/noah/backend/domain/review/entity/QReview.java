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

    private static final PathInits INITS = PathInits.DIRECT2;

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

    public final com.noah.backend.domain.travel.entity.QTravel travel;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.travel = inits.isInitialized("travel") ? new com.noah.backend.domain.travel.entity.QTravel(forProperty("travel"), inits.get("travel")) : null;
    }

}

