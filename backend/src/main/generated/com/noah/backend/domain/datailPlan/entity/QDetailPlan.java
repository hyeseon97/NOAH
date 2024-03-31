package com.noah.backend.domain.datailPlan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDetailPlan is a Querydsl query type for DetailPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDetailPlan extends EntityPathBase<DetailPlan> {

    private static final long serialVersionUID = -457194140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDetailPlan detailPlan = new QDetailPlan("detailPlan");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.noah.backend.domain.image.entity.Image, com.noah.backend.domain.image.entity.QImage> imageList = this.<com.noah.backend.domain.image.entity.Image, com.noah.backend.domain.image.entity.QImage>createList("imageList", com.noah.backend.domain.image.entity.Image.class, com.noah.backend.domain.image.entity.QImage.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Double> pinX = createNumber("pinX", Double.class);

    public final NumberPath<Double> pinY = createNumber("pinY", Double.class);

    public final StringPath place = createString("place");

    public final com.noah.backend.domain.plan.entity.QPlan plan;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final StringPath time = createString("time");

    public QDetailPlan(String variable) {
        this(DetailPlan.class, forVariable(variable), INITS);
    }

    public QDetailPlan(Path<? extends DetailPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDetailPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDetailPlan(PathMetadata metadata, PathInits inits) {
        this(DetailPlan.class, metadata, inits);
    }

    public QDetailPlan(Class<? extends DetailPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.plan = inits.isInitialized("plan") ? new com.noah.backend.domain.plan.entity.QPlan(forProperty("plan"), inits.get("plan")) : null;
    }

}

