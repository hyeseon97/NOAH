package com.noah.backend.domain.plan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlan is a Querydsl query type for Plan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlan extends EntityPathBase<Plan> {

    private static final long serialVersionUID = 1783534022L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlan plan = new QPlan("plan");

    public final StringPath country = createString("country");

    public final ListPath<com.noah.backend.domain.datailPlan.entity.DetailPlan, com.noah.backend.domain.datailPlan.entity.QDetailPlan> detailPlan = this.<com.noah.backend.domain.datailPlan.entity.DetailPlan, com.noah.backend.domain.datailPlan.entity.QDetailPlan>createList("detailPlan", com.noah.backend.domain.datailPlan.entity.DetailPlan.class, com.noah.backend.domain.datailPlan.entity.QDetailPlan.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final com.noah.backend.domain.travel.entity.QTravel travel;

    public final BooleanPath travelStart = createBoolean("travelStart");

    public QPlan(String variable) {
        this(Plan.class, forVariable(variable), INITS);
    }

    public QPlan(Path<? extends Plan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlan(PathMetadata metadata, PathInits inits) {
        this(Plan.class, metadata, inits);
    }

    public QPlan(Class<? extends Plan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.travel = inits.isInitialized("travel") ? new com.noah.backend.domain.travel.entity.QTravel(forProperty("travel"), inits.get("travel")) : null;
    }

}

