package com.noah.backend.domain.travel.controller;

import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.domain.memberTravel.Service.MemberTravelService;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelInviteDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelGetDtoJun;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelUpdateDto;
import com.noah.backend.domain.travel.service.TravelService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Travel 컨트롤러", description = "Travel Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/travel")
public class TravelController {

    private final ApiResponse response;
    private final TravelService travelService;
    private final MemberService memberService;
    private final MemberTravelService memberTravelService;

    @Operation(summary = "여행 전체 조회", description = "여행 전체 목록 조회")
    @GetMapping("/allTravel")
    public ResponseEntity<?> getTravelList(){
        List<TravelGetListDto> travelList = travelService.getTravelList();

        return response.success(ResponseCode.TRAVEL_INFO_FETCHED, travelList);
    }

    @Operation(summary = "여행 선택 조회", description = "목표금액 그래프, 날짜별 대표 장소 출력하는 페이지 요청용")
    @GetMapping("{travelId}")
    public ResponseEntity<?> getTravelSelect(@Parameter(hidden = true) Authentication authentication, @PathVariable(value = "travelId") Long travelId){
        TravelGetDto selectTravel = travelService.getTravelSelect(authentication.getName(), travelId);
        return response.success(ResponseCode.TRAVEL_INFO_FETCHED, selectTravel);
    }

//    @Operation(summary = "메인페이지에 표시할 나의 여행 리스트 조회", description = "여행별 모임통장, 환율, 추천 조회")
//    @GetMapping()
//    public ResponseEntity<?> getTravelMember(@Parameter(hidden = true) Authentication authentication){
//
//        List<TravelGetListDto> travelList = travelService.getTravelMemberId(authentication.getName());
//
//        return response.success(ResponseCode.TRAVEL_INFO_FETCHED, travelList);
//
//    }

    @Operation(summary = "여행 생성", description = "여행 생성 기능")
    @PostMapping
    public ResponseEntity<?> createTravel(@RequestBody TravelPostDto travelPostDto, @Parameter(hidden = true) Authentication authentication){

        Long memberId = memberService.searchMember(authentication).getMemberId();

        Long createTravelId = travelService.createTravelTest(travelPostDto, memberId);
        return response.success(ResponseCode.TRAVEL_CREATED, createTravelId);
    }


    @Operation(summary = "여행 정보 수정", description = "여행 정보 수정 기능 / travelId 필요")
    @PutMapping("{travelId}")
    public ResponseEntity<?> updateTravel(@PathVariable(value = "travelId") Long travelId, @RequestBody TravelUpdateDto travelUpdateDto){

        Long updateTravelId = travelService.updateTravel(travelId, travelUpdateDto);

        return response.success(ResponseCode.TRAVEL_INFO_UPDATED, updateTravelId);
    }

    @Operation(summary = "여행 삭제", description = "여행 선택 삭제 / TravelID 필요")
    @DeleteMapping("{travelId}")
    public ResponseEntity<?> deleteTravel(@PathVariable(value = "travelId") Long travelId){
        travelService.deleteTravel(travelId);

        return response.success(ResponseCode.TRAVEL_DELETED);
    }

    @Operation(summary = "여행 멤버 초대 요청", description = "여행에 멤버 초대 요청 보내기")
    @PostMapping("/invite")
    public ResponseEntity<?> invite(@RequestBody MemberTravelInviteDto memberTravelInviteDto){
        Long memberTravelId = memberTravelService.inviteMember(memberTravelInviteDto);
        return response.success(ResponseCode.TRAVEL_INVITE_SUCCESS, memberTravelId);
    }

}
