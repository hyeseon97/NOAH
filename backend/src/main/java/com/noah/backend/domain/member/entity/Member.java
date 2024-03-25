package com.noah.backend.domain.member.entity;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.base.BaseEntity;
import com.noah.backend.domain.comment.entity.Comment;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.domain.notification.entity.Notification;
import com.noah.backend.domain.trade.entity.Trade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Builder
@Getter
@NoArgsConstructor //아무것도없는 기본생성자
@AllArgsConstructor //모든 필드가 들어있는 생성자
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE member SET is_deleted = TRUE WHERE member_id = ?")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "userkey", nullable = false)
    private String userKey;

    @Setter
    @Column(name = "notification_token", nullable = false)
    private String notificationToken = null;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) //cascade는 연쇄 삭제
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<MemberTravel> memberTravelList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Trade> tradeList = new ArrayList<>();
}
