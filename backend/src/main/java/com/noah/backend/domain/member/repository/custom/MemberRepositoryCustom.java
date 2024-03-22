package com.noah.backend.domain.member.repository.custom;

import com.noah.backend.domain.member.entity.Member;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);

    boolean isNicknameDuplicate(String email);

}
