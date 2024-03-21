package com.noah.backend.domain.travel.controller;

import com.noah.backend.domain.travel.dto.requestDto.TravelGetDto;
import com.noah.backend.domain.travel.dto.requestDto.TravelGetListDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelPostDto;
import com.noah.backend.domain.travel.dto.responseDto.TravelUpdateDto;
import com.noah.backend.domain.travel.service.TravelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Travel 컨트롤러", description = "Travel Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/travel")
public class TravelController {

    private final TravelService travelService;

//    @PostMapping()
//    public ResponseEntity<?> create(Authentication authentication, TravelPostDto travelPostDto){
//
//
//        String email = authentication.getName();
//        Member member = memberService.findByEmail(email);
//    }

    @GetMapping("/list")
    public ResponseEntity<?> getTravelList(){
        List<TravelGetListDto> travelList = travelService.getTravelList();

        return ResponseEntity.ok(travelList);
    }

    @GetMapping("{travelId}")
    public ResponseEntity<?> getTravelSelect(@PathVariable Long travelId){
        TravelGetDto selectTravel = travelService.getTravelSelect(travelId);

        return ResponseEntity.ok(selectTravel);
    }

    @PostMapping
    public ResponseEntity<?> createTravel(@RequestBody TravelPostDto travelPostDto){

        Long createTravelId = travelService.createTravel(travelPostDto);
        return ResponseEntity.ok(createTravelId);
    }

    @PutMapping("{travelId}")
    public ResponseEntity<?> updateTravel(@PathVariable Long travelId, @RequestBody TravelUpdateDto travelUpdateDto){

        Long updateTravelId = travelService.updateTravel(travelId, travelUpdateDto);

        return ResponseEntity.ok(updateTravelId);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTravel(@PathVariable Long travelId){
        travelService.deleteTravel(travelId);

        return ResponseEntity.ok().build();
    }


}
