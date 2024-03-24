package com.noah.backend.domain.memberTravel.Repository.custom;

import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelGetDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;

import java.util.List;
import java.util.Optional;

public interface MemberTravelRepositoryCustom {

    Optional<List<MemberTravelListGetDto>> findByTravelId(Long travelId);

    Optional<MemberTravelGetDto> getSelect(Long memberTravelId);

}
