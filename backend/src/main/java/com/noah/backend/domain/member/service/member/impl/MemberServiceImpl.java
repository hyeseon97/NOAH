package com.noah.backend.domain.member.service.member.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountBalanceCheckReqDto;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountCreateReqDto;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountDepositReqDto;
import com.noah.backend.domain.bank.dto.requestDto.MemberCheckReqDto;
import com.noah.backend.domain.bank.dto.requestDto.MemberCreateReqDto;
import com.noah.backend.domain.bank.dto.responseDto.BankAccountBalanceCheckResDto;
import com.noah.backend.domain.bank.dto.responseDto.BankAccountCreateResDto;
import com.noah.backend.domain.bank.dto.responseDto.MemberCreateResDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.member.dto.login.LoginRequestDto;
import com.noah.backend.domain.member.dto.login.LoginResponseDto;
import com.noah.backend.domain.member.dto.requestDto.SignupRequestDto;
import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import com.noah.backend.domain.member.dto.responseDto.MemberSearchDto;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.global.exception.member.EmailNotFoundException;
import com.noah.backend.global.exception.member.InvalidLoginAttemptException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.member.PasswordMismatchException;
import com.noah.backend.global.jwt.TokenInfo;
import com.noah.backend.global.jwt.provider.TokenProvider;
import com.noah.backend.global.jwt.repository.RefreshTokenRepository;
import com.noah.backend.global.jwt.service.TokenService;
import com.noah.backend.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final BankService bankService;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Long create(SignupRequestDto requestDto) throws IOException {
        // 회원가입

        /* 싸피 금융망으로 UserKey 발급 */
        MemberCreateReqDto memberCreateReqDto = MemberCreateReqDto.builder()
                                                                  .email(requestDto.getEmail())
                                                                  .build();
        MemberCreateResDto memberCreateResDto = bankService.memberCreate(memberCreateReqDto);

        String userKey = null;

        // 유저키가 널이면 이미 가입되어 있는 회원 -> 유저키 재발급 받기
        if (memberCreateResDto == null) {
            MemberCheckReqDto memberCheckReqDto = MemberCheckReqDto.builder()
                                                                   .email(requestDto.getEmail()).build();
            userKey = bankService.memberCheck(memberCheckReqDto).getUserKey();
        } else {
            userKey = memberCreateResDto.getUserKey();
        }

        /* 회원 생성 */
        Member member = Member.builder()
                              .email(requestDto.getEmail())
                              .name(requestDto.getName())
                              .nickname(requestDto.getNickname())
                              .password(passwordEncoder.encode(requestDto.getPassword()))
                              .userKey(userKey)
                              .notificationToken(null).build();
        Member savedMember = memberRepository.save(member);
        System.out.println("회원가입된 아이디 : " + savedMember.getId());

        /* UserKey로 개인계좌 개설 */
        // 기본으로 가지고 있는 개인 계좌 개수 (랜덤으로 1-4개)
        int num = ThreadLocalRandom.current().nextInt(1, 5);
        System.out.println("기본계좌개수 : " + num);
        for (int i = 0; i < num; i++) {
            // 해당 계좌에 기본으로 가지고 있는 잔액 (랜덤으로 10000-1500000원)
            int money = ThreadLocalRandom.current().nextInt(1000, 150000) * 10;
            int type = ThreadLocalRandom.current().nextInt(1, 5);

            System.out.println((i + 1) + "번 계좌 잔액 : " + money + " " + type + "번 은행");

            String typeSsafy = null;
            switch (type) {
                case 1:
                    typeSsafy = "001";
                    break;
                case 2:
                    typeSsafy = "002";
                    break;
                case 3:
                    typeSsafy = "003";
                    break;
                case 4:
                    typeSsafy = "004";
                    break;
                default:
                    break;
            }

            // 싸피 금융망에 저장
            BankAccountCreateReqDto bankAccountCreateReqDto = BankAccountCreateReqDto.builder()
                                                                                     .bankType(typeSsafy)
                                                                                     .userKey(userKey).build();
            BankAccountCreateResDto bankAccountCreateResDto = bankService.bankAccountCreate(bankAccountCreateReqDto);

            // 입금으로 잔액 채워넣기
            BankAccountDepositReqDto bankAccountDepositReqDto = BankAccountDepositReqDto.builder()
                .userKey(userKey)
                .bankCode(typeSsafy)
                .accountNo(bankAccountCreateResDto.getAccountNumber())
                .transactionBalance(money)
                .transactionSummary("기존잔액").build();
            bankService.bankAccountDeposit(bankAccountDepositReqDto);

            BankAccountBalanceCheckReqDto test = BankAccountBalanceCheckReqDto.builder()
                .userKey(userKey)
                .bankCode(typeSsafy)
                .accountNo(bankAccountCreateResDto.getAccountNumber()).build();
            BankAccountBalanceCheckResDto blance = bankService.bankAccountBalanceCheck(test);
            System.out.println("계좌잔액 : " + blance.getAccountBalance());

            // DB에 저장
            Account account = Account.builder()
                                     .bankName(bankAccountCreateResDto.getBankName())
                                     .accountNumber(bankAccountCreateResDto.getAccountNumber())
                                     .type("개인계좌")
                                     .member(savedMember)
                                     .amount(money)
                                     .build();
            Account savedAccount = accountRepository.save(account);

        }

        return member.getId();
    }

    @Override
    public boolean checkNicknameDuplication(String nickname) {
        // 닉네임 중복 체크

        return memberRepository.isNicknameDuplicate(nickname);
    }

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse response) {
        // 로그인

        log.info("event=LoginAttempt, email={}", requestDto.getEmail());

        // 로그인한 이메일의 회원이 존재하는지 찾기
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
        // 패스워드 일치하는지 검사
        isPasswordMatchingWithEncoded(requestDto.getPassword(), member.getPassword());
        // old refresh 토큰 삭제
        removeOldRefreshToken(requestDto, member);

        // access 토큰, refresh 토큰 생성하기
        TokenInfo tokenInfo = tokenProvider.generateTokenInfo(member.getEmail());
        tokenService.saveToken(tokenInfo);
        // refresh 토큰 쿠키에 넣기
        cookieUtil.addCookie("RefreshToken", tokenInfo.getRefreshToken(), tokenProvider.getREFRESH_TOKEN_TIME(), response);

        // 로그인 응답
        return LoginResponseDto.builder()
                               .token(tokenInfo.getAccessToken())
                               .memberInfo(MemberInfoDto.builder()
                                                        .memberId(member.getId())
                                                        .email(member.getEmail())
                                                        .name(member.getName())
                                                        .nickname(member.getNickname())
                                                        .build())
                               .build();
    }

    private void isPasswordMatchingWithEncoded(String input, String encoded) {
        // 두개의 패스워드 일치 확인
        // input은 사용자가 입력한 패스워드, encoded는 DB에 있는 인코딩한 패스워드

        if (!passwordEncoder.matches(input, encoded)) {
            throw new InvalidLoginAttemptException();
        }
    }

    @Override
    @Transactional
    public String logout(String email, HttpServletResponse servletResponse) {
        // 로그아웃

        cookieUtil.removeCookie("RefreshToken", servletResponse);
        refreshTokenRepository.findById(email)
                              .ifPresent(refreshTokenRepository::delete);
        return email;
    }

    private void removeOldRefreshToken(LoginRequestDto requestDto, Member member) {
        // old refresh 토큰 삭제

        refreshTokenRepository.findById(member.getEmail())
                              .ifPresent(refreshTokenRepository::delete);
        log.info("event=DeleteExistingRefreshToken, email={}", requestDto.getEmail());
    }


    private void checkPasswordConfirmation(String password, String passwordConfirm) {
        // 회원가입 시 비밀번호 일치 확인
        // 둘 다 사용자가 입력한 패스워드

        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException();
        }
    }

    @Override
    public MemberInfoDto searchMember(String email) {
        // 여행에 회원 초대할 때 회원 검색

        MemberInfoDto member = memberRepository.searchMember(email).orElseThrow(MemberNotFoundException::new);
        return member;
    }

    @Override
    public String test(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        return member.getNickname();
    }

    @Override
    public MemberSearchDto searchMember(Authentication authentication) {
        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(MemberNotFoundException::new);
        MemberSearchDto searchMember = MemberSearchDto.builder()
                                                      .memberId(member.getId())
                                                      .userKey(member.getUserKey())
                                                      .build();
        return searchMember;
    }

}
