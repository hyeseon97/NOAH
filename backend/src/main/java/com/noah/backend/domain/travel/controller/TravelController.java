package com.noah.backend.domain.travel.controller;

import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.domain.memberTravel.Service.MemberTravelService;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelInviteDto;
import com.noah.backend.domain.travel.dto.responseDto.MyTravelGetDto;
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
import java.io.IOException;
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

    //    @Operation(summary = "여행 전체 조회", description = "여행 전체 목록 조회")
//    @GetMapping("/allTravel")
//    public ResponseEntity<?> getTravelList(){
//        List<TravelGetListDto> travelList = travelService.getTravelList();
//
//        return response.success(ResponseCode.TRAVEL_INFO_FETCHED, travelList);
//    }
    @Operation(summary = "마이페이지에서 모든 여행 조회", description = "목표금액 그래프, 날짜별 장소 리스트(사진포함) 조회하는 페이지 요청용")
    @GetMapping()
    public ResponseEntity<?> getTravelAll(@Parameter(hidden = true) Authentication authentication){
        List<MyTravelGetDto> result = travelService.getTravelAll(authentication.getName());
        return response.success(ResponseCode.TRAVEL_INFO_FETCHED, result);
    }

    @Operation(summary = "여행 선택 조회", description = "목표금액 그래프, 날짜별 장소 리스트(사진포함) 조회하는 페이지 요청용")
    @GetMapping("{travelId}")
    public ResponseEntity<?> getTravelSelect(@Parameter(hidden = true) Authentication authentication,
                                             @PathVariable(value = "travelId") Long travelId) throws IOException {
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
    public ResponseEntity<?> createTravel(@Parameter(hidden = true) Authentication authentication,
                                          @RequestBody TravelPostDto travelPostDto) {

        Long memberId = memberService.searchMember(authentication).getMemberId();

        Long createTravelId = travelService.createTravelTest(travelPostDto, memberId);
        return response.success(ResponseCode.TRAVEL_CREATED, createTravelId);
    }


    @Operation(summary = "여행 정보 수정", description = "여행 정보 수정 기능 / travelId 필요")
    @PutMapping()
    public ResponseEntity<?> updateTravel(@Parameter(hidden = true) Authentication authentication,
                                          @RequestBody TravelUpdateDto travelUpdateDto) {

        Long updateTravelId = travelService.updateTravel(authentication.getName(), travelUpdateDto);

        return response.success(ResponseCode.TRAVEL_INFO_UPDATED, updateTravelId);
    }

    @Operation(summary = "여행 삭제", description = "여행 선택 삭제 / TravelID 필요")
    @DeleteMapping("{travelId}")
    public ResponseEntity<?> deleteTravel(@Parameter(hidden = true) Authentication authentication,
                                          @PathVariable(value = "travelId") Long travelId) {
        travelService.deleteTravel(authentication.getName(), travelId);

        return response.success(ResponseCode.TRAVEL_DELETED);
    }

    @Operation(summary = "여행 멤버 초대 요청", description = "여행에 멤버 초대 요청 보내기")
    @PostMapping("/invite")
    public ResponseEntity<?> invite(@Parameter(hidden = true) Authentication authentication,
                                    @RequestBody MemberTravelInviteDto memberTravelInviteDto) {
        Long memberTravelId = memberTravelService.inviteMember(authentication.getName(), memberTravelInviteDto);
        return response.success(ResponseCode.TRAVEL_INVITE_SUCCESS, memberTravelId);
    }

}
