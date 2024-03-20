package com.noah.backend.domain.account.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 1120255546L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath bank = createString("bank");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> deposit = createNumber("deposit", Integer.class);

    public final com.noah.backend.domain.exchange.entity.QExchange exchange;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> ownerId = createNumber("ownerId", Long.class);

    public final NumberPath<Integer> paymentDate = createNumber("paymentDate", Integer.class);

    public final NumberPath<Integer> perAmount = createNumber("perAmount", Integer.class);

    public final NumberPath<Integer> targetAmount = createNumber("targetAmount", Integer.class);

    public final ListPath<com.noah.backend.domain.trade.entity.Trade, com.noah.backend.domain.trade.entity.QTrade> tradeList = this.<com.noah.backend.domain.trade.entity.Trade, com.noah.backend.domain.trade.entity.QTrade>createList("tradeList", com.noah.backend.domain.trade.entity.Trade.class, com.noah.backend.domain.trade.entity.QTrade.class, PathInits.DIRECT2);

    public final com.noah.backend.domain.travel.entity.QTravel travel;

    public final NumberPath<Integer> withdraw = createNumber("withdraw", Integer.class);

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exchange = inits.isInitialized("exchange") ? new com.noah.backend.domain.exchange.entity.QExchange(forProperty("exchange"), inits.get("exchange")) : null;
        this.travel = inits.isInitialized("travel") ? new com.noah.backend.domain.travel.entity.QTravel(forProperty("travel"), inits.get("travel")) : null;
    }

}

