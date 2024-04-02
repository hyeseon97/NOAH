package com.noah.backend.domain.groupaccount.repository.custom;

import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.memberTravel.dto.Response.GetTravelListResDto;

import java.util.List;
import java.util.Optional;

public interface GroupAccountRepositoryCustom {

    Optional<GroupAccountInfoDto> getGroupAccountInfo(Long groupAccountId);

    Optional<GroupAccountInfoDto> getGroupAccountInfoByTravelId(Long travelId);

    Optional<List<GroupAccountInfoDto>> getGroupAccountListByMemberId(Long memberId);

    Optional<List<Long>> getGroupAccountIdsByMemberId(Long memberId);

	Optional<Integer> findBalance(Long travelId);

	Optional<Integer> findTargetAmount(Long travelId);

	Optional<AccountInfoDto> findByTravleId(Long travelId);
}
