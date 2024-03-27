package com.noah.backend.domain.memberTravel.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTravel is a Querydsl query type for MemberTravel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTravel extends EntityPathBase<MemberTravel> {

    private static final long serialVersionUID = -1937639268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTravel memberTravel = new QMemberTravel("memberTravel");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    public final com.noah.backend.domain.account.entity.QAccount account;

    public final BooleanPath autoTransfer = createBoolean("autoTransfer");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final com.noah.backend.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> payment_amount = createNumber("payment_amount", Integer.class);

    public final com.noah.backend.domain.travel.entity.QTravel travel;

    public QMemberTravel(String variable) {
        this(MemberTravel.class, forVariable(variable), INITS);
    }

    public QMemberTravel(Path<? extends MemberTravel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTravel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTravel(PathMetadata metadata, PathInits inits) {
        this(MemberTravel.class, metadata, inits);
    }

    public QMemberTravel(Class<? extends MemberTravel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.noah.backend.domain.account.entity.QAccount(forProperty("account"), inits.get("account")) : null;
        this.member = inits.isInitialized("member") ? new com.noah.backend.domain.member.entity.QMember(forProperty("member")) : null;
        this.travel = inits.isInitialized("travel") ? new com.noah.backend.domain.travel.entity.QTravel(forProperty("travel"), inits.get("travel")) : null;
    }

}

