package com.noah.backend.domain.trade.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrade is a Querydsl query type for Trade
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrade extends EntityPathBase<Trade> {

    private static final long serialVersionUID = 1736173722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrade trade = new QTrade("trade");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath consumeType = createString("consumeType");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath date = createString("date");

    public final com.noah.backend.domain.groupaccount.entity.QGroupAccount groupAccount;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isContained = createBoolean("isContained");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath time = createString("time");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QTrade(String variable) {
        this(Trade.class, forVariable(variable), INITS);
    }

    public QTrade(Path<? extends Trade> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrade(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrade(PathMetadata metadata, PathInits inits) {
        this(Trade.class, metadata, inits);
    }

    public QTrade(Class<? extends Trade> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupAccount = inits.isInitialized("groupAccount") ? new com.noah.backend.domain.groupaccount.entity.QGroupAccount(forProperty("groupAccount"), inits.get("groupAccount")) : null;
    }

}

