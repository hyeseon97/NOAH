package com.noah.backend.domain.travel.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravel is a Querydsl query type for Travel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravel extends EntityPathBase<Travel> {

    private static final long serialVersionUID = 866923240L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravel travel = new QTravel("travel");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.noah.backend.domain.groupaccount.entity.QGroupAccount groupAccount;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final BooleanPath isEnded = createBoolean("isEnded");

    public final ListPath<com.noah.backend.domain.memberTravel.entity.MemberTravel, com.noah.backend.domain.memberTravel.entity.QMemberTravel> memberTravelList = this.<com.noah.backend.domain.memberTravel.entity.MemberTravel, com.noah.backend.domain.memberTravel.entity.QMemberTravel>createList("memberTravelList", com.noah.backend.domain.memberTravel.entity.MemberTravel.class, com.noah.backend.domain.memberTravel.entity.QMemberTravel.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.noah.backend.domain.plan.entity.QPlan plan;

    public final ListPath<com.noah.backend.domain.ticket.entity.Ticket, com.noah.backend.domain.ticket.entity.QTicket> ticketList = this.<com.noah.backend.domain.ticket.entity.Ticket, com.noah.backend.domain.ticket.entity.QTicket>createList("ticketList", com.noah.backend.domain.ticket.entity.Ticket.class, com.noah.backend.domain.ticket.entity.QTicket.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QTravel(String variable) {
        this(Travel.class, forVariable(variable), INITS);
    }

    public QTravel(Path<? extends Travel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravel(PathMetadata metadata, PathInits inits) {
        this(Travel.class, metadata, inits);
    }

    public QTravel(Class<? extends Travel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupAccount = inits.isInitialized("groupAccount") ? new com.noah.backend.domain.groupaccount.entity.QGroupAccount(forProperty("groupAccount"), inits.get("groupAccount")) : null;
        this.plan = inits.isInitialized("plan") ? new com.noah.backend.domain.plan.entity.QPlan(forProperty("plan"), inits.get("plan")) : null;
    }

}

