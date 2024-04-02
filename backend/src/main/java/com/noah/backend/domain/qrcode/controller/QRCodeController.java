package com.noah.backend.domain.qrcode.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/************
 * @info : QR Code 생성 및 제공 Controller
 * @name : QrController
 * @date : 2023/03/24 5:32 PM
 * @version : 1.0.0
 * @Description :
 ************/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qrcode")
@Slf4j
public class QRCodeController {

    private final MemberService memberService;

    @GetMapping("/image/{travelId}")
    public ResponseEntity<byte[]> qrToTistory(@Parameter(hidden = true)Authentication authentication, @PathVariable(name = "travelId") Long travelId) throws WriterException, IOException {

        Long memberId = memberService.findMemberId(authentication.getName());

        // QR 정보
        int width = 200;
        int height = 200;
        String url = "http://j10b106.p.ssafy.io/market?memberId=" + memberId + "&travelId=" + travelId;

        // QR Code - BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter()
            .encode(url, BarcodeFormat.QR_CODE, width, height);

        // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
        // stream으로 Generate(1회성이 아니면 File로 작성 가능.)
        try {
            //output Stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", out);

            return ResponseEntity.ok()
                                 .contentType(MediaType.IMAGE_PNG)
                                 .body(out.toByteArray());

        }catch (Exception e){log.warn("QR Code OutputStream 도중 Excpetion 발생, {}", e.getMessage());}

        return null;
    }

}