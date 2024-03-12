package com.noah.backend.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1688873448L;

    public static final QMember member = new QMember("member1");

    public final com.noah.backend.domain.base.QBaseEntity _super = new com.noah.backend.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final ListPath<com.noah.backend.domain.memberTravel.entity.MemberTravel, com.noah.backend.domain.memberTravel.entity.QMemberTravel> memberTravelList = this.<com.noah.backend.domain.memberTravel.entity.MemberTravel, com.noah.backend.domain.memberTravel.entity.QMemberTravel>createList("memberTravelList", com.noah.backend.domain.memberTravel.entity.MemberTravel.class, com.noah.backend.domain.memberTravel.entity.QMemberTravel.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final ListPath<com.noah.backend.domain.notification.entity.Notification, com.noah.backend.domain.notification.entity.QNotification> notificationList = this.<com.noah.backend.domain.notification.entity.Notification, com.noah.backend.domain.notification.entity.QNotification>createList("notificationList", com.noah.backend.domain.notification.entity.Notification.class, com.noah.backend.domain.notification.entity.QNotification.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

