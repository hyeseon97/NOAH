package com.noah.backend.domain.exchange.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExchange is a Querydsl query type for Exchange
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExchange extends EntityPathBase<Exchange> {

    private static final long serialVersionUID = 1420388794L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExchange exchange = new QExchange("exchange");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> currency = createNumber("currency", Integer.class);

    public final NumberPath<Integer> exchangeAmount = createNumber("exchangeAmount", Integer.class);

    public final NumberPath<Double> exchangeRate = createNumber("exchangeRate", Double.class);

    public final com.noah.backend.domain.groupaccount.entity.QGroupAccount groupAccount;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QExchange(String variable) {
        this(Exchange.class, forVariable(variable), INITS);
    }

    public QExchange(Path<? extends Exchange> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExchange(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExchange(PathMetadata metadata, PathInits inits) {
        this(Exchange.class, metadata, inits);
    }

    public QExchange(Class<? extends Exchange> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupAccount = inits.isInitialized("groupAccount") ? new com.noah.backend.domain.groupaccount.entity.QGroupAccount(forProperty("groupAccount"), inits.get("groupAccount")) : null;
    }

}

