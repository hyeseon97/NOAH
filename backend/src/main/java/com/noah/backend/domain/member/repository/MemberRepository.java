package com.noah.backend.domain.member.repository;

import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
