package com.noah.backend.domain.member.repository.custom;

import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import com.noah.backend.domain.member.entity.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);

    boolean isNicknameDuplicate(String email);

    Optional<MemberInfoDto> searchMember(String email);

    Optional<List<Long>> findByTravelId(Long travelId);
}
