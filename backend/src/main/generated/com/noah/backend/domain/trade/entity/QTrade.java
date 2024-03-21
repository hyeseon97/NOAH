package com.noah.backend.domain.trade.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrade is a Querydsl query type for Trade
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrade extends EntityPathBase<Trade> {

    private static final long serialVersionUID = 1736173722L;

    public static final QTrade trade = new QTrade("trade");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath consumeType = createString("consumeType");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath date = createString("date");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isContained = createBoolean("isContained");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath time = createString("time");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QTrade(String variable) {
        super(Trade.class, forVariable(variable));
    }

    public QTrade(Path<? extends Trade> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrade(PathMetadata metadata) {
        super(Trade.class, metadata);
    }

}

