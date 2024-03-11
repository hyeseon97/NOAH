package com.noah.backend.domain.ticket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE ticket SET is_deleted = TRUE WHERE ticket_id = ?")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;

    // 항공기 출발 날짜
    @Column(name = "departure")
    private Date departure;

    // 출발 공항
    @Column(name = "d_airport")
    private String dAirport;

    // 출발 게이트
    @Column(name = "d_gate")
    private int dGate;

    // 도착 게이트
    @Column(name = "arrival")
    private Date arrival;

    // 도착 공항
    @Column(name = "a_airport")
    private String aAirport;

//    //외래키
//    @Setter
//    @JoinColumn(name = "travel_id")
//    @ManyToOne
//    private Travel travel;

}
