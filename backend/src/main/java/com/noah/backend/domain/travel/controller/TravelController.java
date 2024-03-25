package com.noah.backend.domain.travel.controller;

import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.domain.memberTravel.Service.MemberTravelService;
import com.noah.backend.domain.memberTravel.dto.Request.MemberTravelInviteDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelUpdateDto;
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

//    @PostMapping()
//    public ResponseEntity<?> create(Authentication authentication, TravelPostDto travelPostDto){
//
//
//        String email = authentication.getName();
//        Member member = memberService.findByEmail(email);
//    }

    @Operation(summary = "여행 전체 조회", description = "여행 전체 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<?> getTravelList(){
        List<TravelGetListDto> travelList = travelService.getTravelList();

        return ResponseEntity.ok(travelList);
    }

    @Operation(summary = "여행 선택 조회", description = "여행 선택 상세 조회 / travelID 필요")
    @GetMapping("{travelId}")
    public ResponseEntity<?> getTravelSelect(@PathVariable(value = "travelId") Long travelId){
        TravelGetDto selectTravel = travelService.getTravelSelect(travelId);

        return ResponseEntity.ok(selectTravel);
    }

    @Operation(summary = "여행 생성", description = "여행 생성 기능")
    @PostMapping
    public ResponseEntity<?> createTravel(@RequestBody TravelPostDto travelPostDto, @RequestParam(value = "memberId") Long memberId){

        Long createTravelId = travelService.createTravelTest(travelPostDto, memberId);
        return ResponseEntity.ok(createTravelId);
    }
    
//    @Operation(summary = "여행 생성", description = "여행 생성 기능")
//    @PostMapping
//    public ResponseEntity<?> createTravel(@RequestBody TravelPostDto travelPostDto, @Parameter Authentication authentication){
//
//        Long memberId = memberService.searchMember(authentication).getMemberId();
//
//        Long createTravelId = travelService.createTravelTest(travelPostDto, memberId);
//        return ResponseEntity.ok(createTravelId);
//    }


    @Operation(summary = "여행 정보 수정", description = "여행 정보 수정 기능 / travelId 필요")
    @PutMapping("{travelId}")
    public ResponseEntity<?> updateTravel(@PathVariable(value = "travelId") Long travelId, @RequestBody TravelUpdateDto travelUpdateDto){

        Long updateTravelId = travelService.updateTravel(travelId, travelUpdateDto);

        return ResponseEntity.ok(updateTravelId);
    }

    @Operation(summary = "여행 삭제", description = "여행 선택 삭제 / TravelID 필요")
    @DeleteMapping("{travelId}")
    public ResponseEntity<?> deleteTravel(@PathVariable(value = "travelId") Long travelId){
        travelService.deleteTravel(travelId);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "여행 멤버 초대 요청", description = "여행에 멤버 초대 요청 보내기")
    @PostMapping("/invite")
    public ResponseEntity<?> invite(@RequestBody MemberTravelInviteDto memberTravelInviteDto){
        Long memberTravelId = memberTravelService.inviteMember(memberTravelInviteDto);
        return response.success(ResponseCode.TRAVEL_INVITE_SUCCESS, memberTravelId);
    }

}
