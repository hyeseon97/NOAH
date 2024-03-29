package com.noah.backend.domain.memberTravel.Service;

import com.noah.backend.domain.account.dto.requestDto.AutoTransferPostDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelInviteDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelPostDto;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelUpdateDto;

public interface MemberTravelService {

    public Long createMemberTravel(MemberTravelPostDto memberTravelPostDto);

    public Long updateMemberTravel(Long memberTravelId, MemberTravelUpdateDto memberTravelUpdateDto);

    public Long inviteMember(MemberTravelInviteDto memberTravelInviteDto);

    public void deleteResistMember(Long memberTravelId);

    void setAutoTransfer(String email, AutoTransferPostDto autoTransferPostDto);

    void deleteAutoTransfer(String email, Long travelId);

    boolean memberAccessTravel(Long memberId, Long travelId);
}
