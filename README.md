# NOAH ✈

### 삼성 청년 SW아카데미(SSAFY) 10th 특화 핀테크 프로젝트

## 🔎 Team 'Noah'

> [🔗 Team Notion](https://momentous-tuberose-1dc.notion.site/NOAH-9184619eac914b708c77956c48415039?pvs=4)

👤 **팀장 여진구** `Front-Reader`
 - `FrontEnd`(전체 디자인 컴포넌트 설계 및 개발)

👤 **팀원 강준규**
 - `Backend`(계획 및 세부 계획, 리뷰, 티켓 API 설계), `FrontEnd`(계획 및 항공권, 리뷰, 지도 API 연결 및 디자인)

👤 **팀원 오건영**
 - `Backend`(계좌 개설 및 거래내역, 환전 API 설계), `FrontEnd`(개좌 개설 및 환전 관련 API 연결 및 디자인)

👤 **팀원 전현철**
 - `BackEnd`(외부 API 통신), `DevOps`(인프라 구축, 깃랩-젠킨스-EC2 파이프라인 구축)

👤 **팀원 이우진**
 - `BackEnd`(외부 금융망 연결 API 설계, 리뷰 추천 기능 구현)

👤 **팀원 박혜선** `Back-Reader`
 - `BackEnd`(JWT 로그인 및 회원 API 설계,백엔드 플로우 관리, FCM Push 알림)

## ✅ 프로젝트 소개

**🚩 서비스 한줄 소개**

```bash
여행의 모든 순간, 금융부터 계획까지 한 번에 관리하는 당신의 여행 비서
```

**🚩 목표**
* 여행 준비와 진행 과정에서 발생하는 금융 및 계획 관리의 불편함을 해소하여, 사용자가 더 쉽고 효율적으로 여행을 계획하고 즐길 수 있게 하는 것.

**🚩 주요 기능**
### 1. 모임통장
* 여러 사람이 돈을 함께 모을 수 있는 공유 통장 기능 제공
* 자동 이체를 통한 정기적인 납부
* 납부일 알림으로 기한 내 납부 유도
* 납부 현황 확인으로 모임원 간의 투명한 금융 관리 지원

### 2. QR페이 결제
* 모임통장에 모인 돈을 모임 구성원이 손쉽게 사용할 수 있게 하는 결제 시스템
* 하나의 통장에서 여러 명이 결제 가능
* 결제 과정의 편리성과 속도 향상

### 3. 공동 가계부
* 모임통장 사용 내역을 공유하여 모임원들의 소비 패턴을 분석하고 관리
* 사용 내역 분석을 통한 투명한 금융 관리
* 소비 내역을 쉽게 파악하여 공동의 재정 관리 강화

**🚩 기대 효과**
- 여행 준비 과정의 효율성 증가
- 모임원 간의 금융 관련 의사소통 개선 및 갈등 감소
- 여행 중 발생할 수 있는 결제와 재정 관리의 번거로움 해소
- 여행 후 소비 내역 분석을 통한 경제적인 여행 계획 수립 지원


## ✅ 프로젝트 진행 기간

### 2024.02.19 ~ 2024.04.04(6주)

## ✅ 프로젝트 구조

### Back-End
<details>
<summary>Back-End</summary>

```plaintext
백엔드 프로젝트 구조
├── BackendApplication.java
├── domain
│   ├── account
│   │   ├── controller
│   │   │   └── AccountController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── AccountPostDto.java
│   │   │   │   ├── AccountUpdateDto.java
│   │   │   │   └── AutoTransferPostDto.java
│   │   │   └── responseDto
│   │   │       ├── AccountIncludeIsAutoTransfer.java
│   │   │       └── AccountInfoDto.java
│   │   ├── entity
│   │   │   └── Account.java
│   │   ├── repository
│   │   │   ├── AccountRepository.java
│   │   │   └── custom
│   │   │       ├── AccountRepositoryCustom.java
│   │   │       └── AccountRepositoryImpl.java
│   │   └── service
│   │       ├── AccountService.java
│   │       └── impl
│   │           └── AccountServiceImpl.java
│   ├── admin
│   │   ├── controller
│   │   │   └── AdminController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   └── AdminKeyRequestDto.java
│   │   │   └── responseDto
│   │   │       └── AdminKeyResponseDto.java
│   │   └── service
│   │       ├── AdminService.java
│   │       └── impl
│   │           └── AdminServiceImpl.java
│   ├── apis
│   │   ├── controller
│   │   │   └── ApisController.java
│   │   ├── dto
│   │   │   ├── AirlineCodeDto.java
│   │   │   ├── AirlineDto.java
│   │   │   ├── AirlineRouteDto.java
│   │   │   ├── AirportDto.java
│   │   │   ├── AirportNearestDto.java
│   │   │   ├── AirportRouteDto.java
│   │   │   ├── CurrencyDto.java
│   │   │   ├── FlightOffersDto.java
│   │   │   ├── FlightPriceDto.java
│   │   │   └── ResponseFlightOffersDto.java
│   │   ├── entity
│   │   │   ├── Airline.java
│   │   │   ├── Airport.java
│   │   │   └── Currency.java
│   │   ├── mock
│   │   │   ├── airline-code.json
│   │   │   ├── airline-routes.json
│   │   │   ├── airport-nearest-relevant.json
│   │   │   ├── airport-routes.json
│   │   │   ├── flight-offers-single.json
│   │   │   ├── flight-offers.json
│   │   │   ├── flight-price-analysis.json
│   │   │   ├── hotel-detail-id.json
│   │   │   ├── hotel-detail.json
│   │   │   ├── hotel-list-city.json
│   │   │   ├── hotel-list-geocode.json
│   │   │   └── tmp.json
│   │   ├── repository
│   │   │   ├── AirlineRepository.java
│   │   │   ├── AirportRepository.java
│   │   │   ├── CurrencyRepository.java
│   │   │   └── custom
│   │   │       ├── AirportRepositoryCustom.java
│   │   │       └── AirportRepositoryImpl.java
│   │   └── service
│   │       ├── FlightService.java
│   │       ├── ForeignCurrencyService.java
│   │       ├── HotelService.java
│   │       └── TravelInsuranceService.java
│   ├── bank
│   │   ├── controller
│   │   │   └── BankController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── AccountPostDto.java
│   │   │   │   ├── AccountUpdateDto.java
│   │   │   │   ├── BankAccountBalanceCheckReqDto.java
│   │   │   │   ├── BankAccountCreateReqDto.java
│   │   │   │   ├── BankAccountDepositReqDto.java
│   │   │   │   ├── BankAccountListReqDto.java
│   │   │   │   ├── BankAccountTransferReqDto.java
│   │   │   │   ├── BankAccountWithdrawReqDto.java
│   │   │   │   ├── BankHolderCheckReqDto.java
│   │   │   │   ├── MemberCheckReqDto.java
│   │   │   │   ├── MemberCreateReqDto.java
│   │   │   │   ├── QrWithdrawReqDto.java
│   │   │   │   ├── RequestHeaderDto.java
│   │   │   │   └── TransactionHistoryReqDto.java
│   │   │   └── responseDto
│   │   │       ├── AccountInfoDto.java
│   │   │       ├── BankAccountBalanceCheckResDto.java
│   │   │       ├── BankAccountCreateResDto.java
│   │   │       ├── BankAccountListResDto.java
│   │   │       ├── BankHolderCheckResDto.java
│   │   │       ├── MemberCheckResDto.java
│   │   │       ├── MemberCreateResDto.java
│   │   │       ├── ProductDto.java
│   │   │       └── TransactionHistoryResDto.java
│   │   └── service
│   │       ├── BankService.java
│   │       └── Impl
│   │           └── BankServiceImpl.java
│   ├── base
│   │   └── BaseEntity.java
│   ├── comment
│   │   ├── controller
│   │   │   └── CommentController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── CommentPostDto.java
│   │   │   │   └── CommentUpdateDto.java
│   │   │   └── responseDto
│   │   │       ├── CommentGetDto.java
│   │   │       └── CommentListGetDto.java
│   │   ├── entity
│   │   │   └── Comment.java
│   │   ├── repository
│   │   │   ├── CommentRepository.java
│   │   │   └── custom
│   │   │       ├── CommentRepositoryCustom.java
│   │   │       └── CommentRepositoryImpl.java
│   │   └── service
│   │       ├── CommentService.java
│   │       └── impl
│   │           └── CommentServiceImpl.java
│   ├── csv
│   │   ├── controller
│   │   │   └── CsvController.java
│   │   └── service
│   │       ├── CsvService.java
│   │       └── impl
│   │           └── CsvServiceImpl.java
│   ├── datailPlan
│   │   ├── controller
│   │   │   └── DetailPlanController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   └── DetailPlanPostDto.java
│   │   │   └── responseDto
│   │   │       ├── DetailPlanDto.java
│   │   │       └── DetailPlanListDto.java
│   │   ├── entity
│   │   │   └── DetailPlan.java
│   │   ├── repository
│   │   │   ├── DetailPlanRepository.java
│   │   │   └── custom
│   │   │       ├── DetailPlanRepositoryCustom.java
│   │   │       └── DetailPlanRepositoryImpl.java
│   │   └── service
│   │       ├── DetailPlanService.java
│   │       └── impl
│   │           └── DetailPlanServiceImpl.java
│   ├── exchange
│   │   ├── controller
│   │   │   └── ExchangeController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── ExchangeRatePutDto.java
│   │   │   │   └── ExchangeReqDto.java
│   │   │   └── responseDto
│   │   │       ├── ExchangeInfoDto.java
│   │   │       ├── ExchangeRateGetDto.java
│   │   │       └── TargetExchangeRate.java
│   │   ├── entity
│   │   │   └── Exchange.java
│   │   ├── repository
│   │   │   ├── ExchangeRepository.java
│   │   │   └── custom
│   │   │       ├── ExchangeRepositoryCustom.java
│   │   │       └── ExchangeRepositoryImpl.java
│   │   └── service
│   │       ├── ExchangeService.java
│   │       └── impl
│   │           └── ExchangeServiceImpl.java
│   ├── groupaccount
│   │   ├── controller
│   │   │   └── GroupAccountController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── DepositReqDto.java
│   │   │   │   ├── GroupAccountPostDto.java
│   │   │   │   ├── GroupAccountRequestDto.java
│   │   │   │   └── GroupAccountUpdateDto.java
│   │   │   └── responseDto
│   │   │       └── GroupAccountInfoDto.java
│   │   ├── entity
│   │   │   └── GroupAccount.java
│   │   ├── repository
│   │   │   ├── GroupAccountRepository.java
│   │   │   └── custom
│   │   │       ├── GroupAccountRepositoryCustom.java
│   │   │       └── GroupAccountRepositoryImpl.java
│   │   └── service
│   │       ├── GroupAccountService.java
│   │       └── impl
│   │           └── GroupAccountServiceImpl.java
│   ├── image
│   │   ├── controller
│   │   │   └── ImageController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── ImageGetDto.java
│   │   │   │   └── ImageListGetDto.java
│   │   │   └── responseDto
│   │   │       ├── ImagePostDto.java
│   │   │       └── ImageUpdateDto.java
│   │   ├── entity
│   │   │   └── Image.java
│   │   ├── repository
│   │   │   ├── ImageRepository.java
│   │   │   └── custom
│   │   │       ├── ImageRepositoryCustom.java
│   │   │       └── ImageRepositoryImpl.java
│   │   └── service
│   │       ├── ImageService.java
│   │       └── impl
│   │           └── ImageServiceImpl.java
│   ├── member
│   │   ├── controller
│   │   │   └── MemberController.java
│   │   ├── dto
│   │   │   ├── email
│   │   │   │   ├── EmailRequestDto.java
│   │   │   │   └── EmailVerificationRequestDto.java
│   │   │   ├── login
│   │   │   │   ├── LoginRequestDto.java
│   │   │   │   └── LoginResponseDto.java
│   │   │   ├── requestDto
│   │   │   │   ├── MemberUpdateDto.java
│   │   │   │   ├── NicknameRequestDto.java
│   │   │   │   ├── SignupRequestDto.java
│   │   │   │   └── UserKeyRequestDto.java
│   │   │   └── responseDto
│   │   │       ├── MemberInfoDto.java
│   │   │       └── MemberSearchDto.java
│   │   ├── entity
│   │   │   └── Member.java
│   │   ├── repository
│   │   │   ├── MemberRepository.java
│   │   │   └── custom
│   │   │       ├── MemberRepositoryCustom.java
│   │   │       └── MemberRepositoryImpl.java
│   │   └── service
│   │       ├── mail
│   │       │   ├── MailService.java
│   │       │   └── MailServiceImpl.java
│   │       └── member
│   │           ├── MemberService.java
│   │           └── impl
│   │               └── MemberServiceImpl.java
│   ├── memberTravel
│   │   ├── Repository
│   │   │   ├── MemberTravelRepository.java
│   │   │   └── custom
│   │   │       ├── MemberTravelRepositoryCustom.java
│   │   │       └── MemberTravelRepositoryImpl.java
│   │   ├── Service
│   │   │   ├── MemberTravelService.java
│   │   │   └── impl
│   │   │       └── MemberTravelServiceImpl.java
│   │   ├── dto
│   │   │   ├── Request
│   │   │   │   ├── MemberTravelInviteDto.java
│   │   │   │   ├── MemberTravelPostDto.java
│   │   │   │   └── MemberTravelUpdateDto.java
│   │   │   └── Response
│   │   │       ├── GetTravelListResDto.java
│   │   │       ├── MemberTravelGetDto.java
│   │   │       ├── MemberTravelListGetDto.java
│   │   │       └── MemberTravelListGetFromTravelDto.java
│   │   └── entity
│   │       └── MemberTravel.java
│   ├── notification
│   │   ├── controller
│   │   │   └── NotificationController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   └── SaveTokenDto.java
│   │   │   └── responseDto
│   │   │       └── NotificationGetDto.java
│   │   ├── entity
│   │   │   └── Notification.java
│   │   ├── repository
│   │   │   ├── NotificationRepository.java
│   │   │   └── custom
│   │   │       ├── NotificationRepositoryCustom.java
│   │   │       └── NotificationRepositoryImpl.java
│   │   └── service
│   │       ├── NotificationService.java
│   │       └── impl
│   │           └── NotificationServiceImpl.java
│   ├── plan
│   │   ├── controller
│   │   │   └── PlanController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── PlanPostDto.java
│   │   │   │   └── PlanUpdateDto.java
│   │   │   └── responseDto
│   │   │       ├── PlanGetDto.java
│   │   │       ├── PlanListGetFromTravelDto.java
│   │   │       └── SimplePlan.java
│   │   ├── entity
│   │   │   └── Plan.java
│   │   ├── repository
│   │   │   ├── PlanRepository.java
│   │   │   └── custom
│   │   │       ├── PlanRepositoryCustom.java
│   │   │       └── PlanRepositoryImpl.java
│   │   └── service
│   │       ├── PlanService.java
│   │       └── impl
│   │           └── PlanServiceImpl.java
│   ├── qrcode
│   │   └── controller
│   │       └── QRCodeController.java
│   ├── review
│   │   ├── controller
│   │   │   └── ReviewController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── ReviewPostDto.java
│   │   │   │   └── ReviewUpdateDto.java
│   │   │   └── responseDto
│   │   │       ├── ReviewGetDto.java
│   │   │       └── ReviewListGetDto.java
│   │   ├── entity
│   │   │   └── Review.java
│   │   ├── repository
│   │   │   ├── ReviewRepository.java
│   │   │   └── custom
│   │   │       ├── ReviewRepositoryCustom.java
│   │   │       └── ReviewRepositoryImpl.java
│   │   └── service
│   │       ├── ReviewService.java
│   │       └── impl
│   │           └── ReviewServiceImpl.java
│   ├── suggest
│   │   ├── controller
│   │   │   └── SuggestController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── SuggestImageGetDto.java
│   │   │   │   └── SuggestListReqDto.java
│   │   │   └── responseDto
│   │   │       ├── MainSuggestGetDto.java
│   │   │       └── SuggestListResDto.java
│   │   ├── entity
│   │   │   └── Suggest.java
│   │   ├── repository
│   │   │   ├── SuggestRepository.java
│   │   │   └── custom
│   │   │       ├── SuggestRepositoryCustom.java
│   │   │       └── SuggestRepositoryImpl.java
│   │   └── service
│   │       ├── Impl
│   │       │   └── SuggestServiceImpl.java
│   │       └── SuggestService.java
│   ├── ticket
│   │   ├── controller
│   │   │   └── TicketController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── TicketPostDto.java
│   │   │   │   └── TicketUpdateDto.java
│   │   │   └── responseDto
│   │   │       ├── TicketGetDto.java
│   │   │       └── TicketListGetFromTravelDto.java
│   │   ├── entity
│   │   │   └── Ticket.java
│   │   ├── repository
│   │   │   ├── TicketRepository.java
│   │   │   └── custom
│   │   │       ├── TicketRepositoryCustom.java
│   │   │       └── TicketRepositoryImpl.java
│   │   └── service
│   │       ├── TicketService.java
│   │       └── impl
│   │           └── TicketServiceImpl.java
│   ├── trade
│   │   ├── controller
│   │   │   └── TradeController.java
│   │   ├── dto
│   │   │   ├── requestDto
│   │   │   │   ├── TradeGetReqDto.java
│   │   │   │   ├── TradePostReqDto.java
│   │   │   │   └── TradeUpdateClassifyReqDto.java
│   │   │   └── responseDto
│   │   │       ├── TradeDateAndTime.java
│   │   │       └── TradeGetResDto.java
│   │   ├── entity
│   │   │   └── Trade.java
│   │   ├── repository
│   │   │   ├── TradeRepository.java
│   │   │   └── custom
│   │   │       ├── TradeRepositoryCustom.java
│   │   │       └── impl
│   │   │           └── TradeRepositoryImpl.java
│   │   └── service
│   │       ├── TradeService.java
│   │       └── impl
│   │           └── TradeServiceImpl.java
│   └── travel
│       ├── controller
│       │   └── TravelController.java
│       ├── dto
│       │   ├── requestDto
│       │   │   ├── TravelGetListDto.java
│       │   │   ├── TravelPostDto.java
│       │   │   └── TravelUpdateDto.java
│       │   └── responseDto
│       │       ├── MyTravelGetDto.java
│       │       ├── TravelGetDto.java
│       │       └── TravelGetDtoJun.java
│       ├── entity
│       │   └── Travel.java
│       ├── repository
│       │   ├── TravelRepository.java
│       │   └── custom
│       │       ├── TravelRepositoryCustom.java
│       │       └── TravelRepositoryImpl.java
│       └── service
│           ├── TravelService.java
│           └── impl
│               └── TravelServiceImpl.java
└── global
    ├── annotation
    │   ├── AccessToken.java
    │   └── Nickname.java
    ├── config
    │   ├── FCMConfig.java
    │   ├── MailConfig.java
    │   ├── QuerydslConfig.java
    │   ├── RedisConfig.java
    │   ├── SecurityConfig.java
    │   ├── SwaggerConfig.java
    │   └── WebConfig.java
    ├── entrypoint
    │   └── JwtAuthenticationEntryPoint.java
    ├── exception
    │   ├── account
    │   │   └── AccountNotFoundException.java
    │   ├── bank
    │   │   ├── A1001Exception.java
    │   │   ├── A1003Exception.java
    │   │   ├── A1011Exception.java
    │   │   ├── A1014Exception.java
    │   │   ├── A1016Exception.java
    │   │   ├── A1017Exception.java
    │   │   ├── A1018Exception.java
    │   │   ├── BankAccountCreateFailed.java
    │   │   ├── H1008Exception.java
    │   │   └── H1009Exception.java
    │   ├── comment
    │   │   ├── CommentDeleteFailed.java
    │   │   ├── CommentNotFound.java
    │   │   ├── CommentPermissionDenied.java
    │   │   └── CommentUpdateFailed.java
    │   ├── csv
    │   │   └── CsvCreateFailedException.java
    │   ├── detailplan
    │   │   ├── DetailPlanBadRequest.java
    │   │   ├── DetailPlanDeleteFailed.java
    │   │   ├── DetailPlanNotFound.java
    │   │   ├── DetailPlanPermissionDenied.java
    │   │   └── DetailUpdateFailed.java
    │   ├── exchange
    │   │   ├── ExchangeCurrencyNotAcceptableException.java
    │   │   ├── ExchangeFailedException.java
    │   │   └── ExchangeNotFoundException.java
    │   ├── flight
    │   │   ├── AirportNotFoundException.java
    │   │   ├── DepartureDateException.java
    │   │   └── RequiredFilledException.java
    │   ├── groupaccount
    │   │   ├── GroupAccountAccessDeniedException.java
    │   │   └── GroupAccountNotFoundException.java
    │   ├── image
    │   │   ├── ImageNotFoundException.java
    │   │   ├── ImageProcessingFailed.java
    │   │   └── ImageUploadFailed.java
    │   ├── member
    │   │   ├── AccessTokenNotFoundException.java
    │   │   ├── DuplicateEmailException.java
    │   │   ├── EmailNotFoundException.java
    │   │   ├── FailedMessageTransmissionException.java
    │   │   ├── InvalidAuthCodeException.java
    │   │   ├── InvalidLoginAttemptException.java
    │   │   ├── MemberNotFoundException.java
    │   │   ├── PasswordMismatchException.java
    │   │   ├── RefreshTokenNotFoundException.java
    │   │   └── UnauthorizedAccessException.java
    │   ├── membertravel
    │   │   ├── MemberTravelAccessException.java
    │   │   ├── MemberTravelAlreadyExistException.java
    │   │   ├── MemberTravelAlreadyInvitedException.java
    │   │   └── MemberTravelNotFoundException.java
    │   ├── notification
    │   │   ├── FirebaseTokenNotExistException.java
    │   │   ├── NotificationAccessException.java
    │   │   ├── NotificationNotFoundException.java
    │   │   └── NotificationSendFailedException.java
    │   ├── plan
    │   │   ├── PlanAccessException.java
    │   │   ├── PlanBadRequest.java
    │   │   ├── PlanDeleteFailed.java
    │   │   ├── PlanNotFound.java
    │   │   ├── PlanPermissionDenied.java
    │   │   └── PlanUpdateFailed.java
    │   ├── review
    │   │   ├── ReviewDeleteFailed.java
    │   │   ├── ReviewNotFound.java
    │   │   ├── ReviewPermissionDenied.java
    │   │   └── ReviewUpdateFailed.java
    │   ├── suggest
    │   │   ├── LowerThanPriceNotExists.java
    │   │   ├── ReviewIdNotExists.java
    │   │   └── SuggestNotExists.java
    │   ├── ticket
    │   │   ├── TicketBadRequest.java
    │   │   ├── TicketDeleteFailed.java
    │   │   ├── TicketNotFound.java
    │   │   ├── TicketPermissionDenied.java
    │   │   └── TicketUpdateFailed.java
    │   ├── trade
    │   │   ├── TradeAccessException.java
    │   │   └── TradeNotFoundException.java
    │   ├── travel
    │   │   ├── TravelBadRequest.java
    │   │   ├── TravelDeleteFailed.java
    │   │   ├── TravelMemberNotFoundException.java
    │   │   ├── TravelNotFoundException.java
    │   │   ├── TravelPerMissionDenied.java
    │   │   └── TravelUpdateFailed.java
    │   └── travelmember
    │       ├── MemberTravelDeleteFailed.java
    │       ├── MemberTravelNotFound.java
    │       ├── MemberTravelPermissionDenied.java
    │       └── MemberTravelUpdateFailed.java
    ├── filter
    │   ├── EmailVerificationFilter.java
    │   ├── JwtAuthenticationFilter.java
    │   ├── TokenExceptionFilter.java
    │   └── TokenRefreshRequestFilter.java
    ├── format
    │   ├── code
    │   │   ├── ApiResponse.java
    │   │   ├── FilterResponse.java
    │   │   └── HttpClientRequest.java
    │   └── response
    │       ├── ErrorCode.java
    │       └── ResponseCode.java
    ├── handler
    │   └── GlobalExceptionHandler.java
    ├── interceptor
    │   └── NicknameValidInterceptor.java
    ├── jwt
    │   ├── RefreshToken.java
    │   ├── TokenInfo.java
    │   ├── provider
    │   │   └── TokenProvider.java
    │   ├── repository
    │   │   └── RefreshTokenRepository.java
    │   └── service
    │       └── TokenService.java
    ├── resolver
    │   └── AccessTokenArgumentResolver.java
    └── util
        ├── CookieUtil.java
        ├── FilterUtil.java
        ├── RedisUtil.java
        └── cookie
            ├── DevCookieUtil.java
            └── ProdCookieUtil.java
```

</details>


### Front-End
<details>
<summary>Front-End</summary>

```plaintext
프론트 프로젝트 구조
├── App.css
├── App.js
├── Main.js
├── api
│   ├── account
│   │   └── Account.js
│   ├── axios.js
│   ├── comment
│   │   └── Comment.js
│   ├── detailplan
│   │   └── DetailPlan.js
│   ├── exchange
│   │   └── Exchange.js
│   ├── flight
│   │   └── flight.js
│   ├── groupaccount
│   │   └── GroupAccount.js
│   ├── image
│   │   └── Image.js
│   ├── member
│   │   └── Member.js
│   ├── notification
│   │   └── Notification.js
│   ├── payment
│   │   └── Payment.js
│   ├── plan
│   │   └── Plan.js
│   ├── review
│   │   └── Review.js
│   ├── suggest
│   │   └── Suggest.js
│   ├── ticket
│   │   └── Ticket.js
│   ├── trade
│   │   └── Trade.js
│   └── travel
│       └── Travel.js
├── assets
│   ├── Icon
│   │   ├── Account.svg
│   │   ├── Airplane.svg
│   │   ├── Arrow.svg
│   │   ├── Auto.svg
│   │   ├── Bank.svg
│   │   ├── BluePeople.svg
│   │   ├── Calender.svg
│   │   ├── Cancel.svg
│   │   ├── CancelGrey.svg
│   │   ├── Change.svg
│   │   ├── Comment.svg
│   │   ├── Edit.svg
│   │   ├── Exchange.svg
│   │   ├── Filter.svg
│   │   ├── GreyPeople.svg
│   │   ├── History.svg
│   │   ├── Logout.svg
│   │   ├── Mark.svg
│   │   ├── My.svg
│   │   ├── Next.svg
│   │   ├── Noah.svg
│   │   ├── Notification.svg
│   │   ├── Person.svg
│   │   ├── Pig.svg
│   │   ├── Plan.svg
│   │   ├── Plus.svg
│   │   ├── QR.svg
│   │   ├── Search.svg
│   │   ├── Ship.svg
│   │   ├── SmallBill.svg
│   │   ├── SmallCalendar.svg
│   │   ├── SmallPeople.svg
│   │   ├── SmallPlane.svg
│   │   ├── Store.svg
│   │   ├── TransferArrow.svg
│   │   ├── TrashCan.svg
│   │   ├── TravelPlace.svg
│   │   ├── User.svg
│   │   ├── WhiteArrow.svg
│   │   ├── check.svg
│   │   └── test.svg
│   └── Image
│       ├── RecommandPlace1.svg
│       ├── RecommandPlace2.svg
│       ├── sample1.jpg
│       └── sample2.png
├── components
│   ├── SpendingManagement
│   │   ├── DoughnutChartSmall.js
│   │   ├── DoughnutChartSmall.module.css
│   │   ├── SpedingHeader.js
│   │   ├── Spending.js
│   │   ├── Spending.module.css
│   │   ├── SpendingHeader.module.css
│   │   ├── SumBox.js
│   │   ├── SumBoxAll.js
│   │   ├── SumBoxType.js
│   │   └── SumBoxTypeAll.js
│   ├── common
│   │   ├── Button.js
│   │   ├── DoughnutChart.js
│   │   ├── DoughnutChart.module.css
│   │   ├── Dropdown.js
│   │   ├── DropdownConsumeType.js
│   │   ├── DropdownMember.js
│   │   ├── DropdownSmall.js
│   │   ├── ExchangeButton.js
│   │   ├── Header.js
│   │   ├── Input.js
│   │   ├── InputSmall.js
│   │   ├── InviteButton.js
│   │   ├── InviteModal.js
│   │   ├── Logo.js
│   │   ├── MyAccount.js
│   │   ├── RoundButton.js
│   │   ├── ScrollToTop.js
│   │   ├── Star.js
│   │   ├── Star.module.css
│   │   ├── Stick.js
│   │   └── Toast.js
│   ├── exchange
│   │   └── Exchange.js
│   ├── notification
│   │   └── Notification.js
│   └── trip
│       ├── DayCalculate.js
│       ├── EditModal.js
│       ├── EditModal.module.css
│       ├── ReviewModal.js
│       ├── ReviewModel.module.css
│       ├── TravelHistory.js
│       └── Trip.js
├── firebase-config.js
├── index.css
├── index.js
├── pages
│   ├── AutomaticWithdrawalSettingPage.js
│   ├── AutomaticWithdrawalSettingPage.module.css
│   ├── ErrorPage.js
│   ├── ExchangePage.js
│   ├── ExchangePage.module.css
│   ├── GoalPage.js
│   ├── GoalPage.module.css
│   ├── GoogleMapSearch.js
│   ├── GoogleMapSearch.module.css
│   ├── HomePage.js
│   ├── HomePage.module.css
│   ├── LoginPage.js
│   ├── LoginPage.module.css
│   ├── MarketPage.js
│   ├── MarketPage.module.css
│   ├── MyAccountPage.js
│   ├── MyPage.js
│   ├── MyPage.module.css
│   ├── NotificationPage.js
│   ├── NotificationPage.module.css
│   ├── ParticipantManagementPage.js
│   ├── ParticipantManagementPage.module.css
│   ├── PaymentPage.js
│   ├── PlaneSearchPage.js
│   ├── PlaneSearchPage.module.css
│   ├── PlanningCreatePage.js
│   ├── PlanningCreatePage.module.css
│   ├── PlanningPage.js
│   ├── PlanningPage.module.css
│   ├── PlanningTestPage.js
│   ├── ReviewCreatePage.js
│   ├── ReviewDetailPage.js
│   ├── ReviewDetailPage.module.css
│   ├── ReviewPage.js
│   ├── ReviewPage.module.css
│   ├── SignUpPage.js
│   ├── SpendingManagementPage.js
│   ├── SpendingManagementPage.module.css
│   ├── TestGoogleMap.js
│   ├── TransferPage.js
│   ├── TransferPage.module.css
│   ├── TravelHistoryPage.js
│   ├── TravelHistoryPage.module.css
│   ├── TripCreatePage.js
│   ├── TripCreatePage.module.css
│   ├── TripPage.js
│   ├── TripPage.module.css
│   ├── WelcomePage.module.css
│   └── WelcomgPage.js
└── store
    └── useUserStore.js

```

</details>

## ✅ 산출물

### ERD
![ERD](etc/ERD.png)

### 시스템 아키텍처
![시스템아키텍처](etc/시스템아키텍처.png)

### 기술 스택 세부명세


## ✅ 시연 시나리오

### 01. 회원가입 페이지 

1. 이메일 인증  메일로 받은 인증코드로 이메일 인증
2. 비밀번호 유효성 검사  영문 대소문자, 숫자 각 1개 이상 조합하여 5자 이상  SQL Injection 고려한 특수문자 제한 (!, @, $, %, ^, & 만 가능)
3. 이름 유효성 검사  한글(자음 또는 모음만 존재하는 것 제외)을 조합해 2-5자
4. 닉네임 유효성 검사  한글만 가능하며 2-8자
<br>
![회원가입](etc/회원가입.png){: width="324" height="576"}

### 02. 로그인 페이지 

이메일, 비밀번호로 로그인

**둘러보기**<br>
회원가입을 하기 전 NOAH가 어떤 서비스인지 기능들을 둘러볼 수 있는 기능
<br>
![로그인](etc/로그인.png){: width="324" height="576"}

### 03. 메인 페이지 

1. 여행 모임통장 정보  여행 제목, 모임통장 계좌 번호, 계좌 잔액, 목표 금액 표시  계좌 번호로 바로 송금 가능
2. 환율 정보  실시간 환율 정보 제공  달러, 엔화, 위안화, 유로 환율 제공
3. 추천 후기 정보  목표금액에 따른 인당 경비 기준으로 여행지 추천  목표금액 미설정인 경우 랜덤 추천 
<br>
![메인페이지1](etc/메인페이지1.png){: width="324" height="576"}
![메인페이지2](etc/메인페이지2.png){: width="324" height="576"}

### 04. 모임 통장 송금 

1. 마이데이터(싸피금융망)를 통해 내 계좌 조회
2. 사용할 계좌와 송금 금액 입력 후 모임 통장으로 송금
<br>
![모임통장송금1](etc/모임통장송금1.png){: width="324" height="576"}
![모임통장송금2](etc/모임통장송금2.png){: width="324" height="576"}

### 05. 추천 리스트 페이지 

1. 모임 통장의 목표 금액에 따른 추천 여행 리스트  도시명, 여행인원, 총 경비, 여행일수 정보 제공
2. 인당 경비를 기준으로 내림차순하여 최적의 여행지를 추천
<br>
![추천리스트페이지](etc/추천리스트페이지.png){: width="324" height="576"}

### 06. 여행 생성 페이지 

여행 이름과 모임 통장 은행 선택<br>

**은행 종류**<br>
한국은행, 기업은행, 산업은행, 국민은행
<br>

![여행생성페이지1](etc/여행생성페이지1.png){: width="324" height="576"}
![여행생성페이지2](etc/여행생성페이지2.png){: width="324" height="576"}

### 07. 여행 정보 페이지 

1. 모임 통장 정보  목표금액에 따른 모은 금액 비율 그래프로 표시
2. 여행 계획 정보  여행 시작일까지 남은 일 수 디데이로 표시  여행 날짜와 도시 정보 제공
3. 여행 관련 서비스  1) QR 결제  2) 환전  3) 소비관리  4) 인원관리
<br>
![여행정보페이지](etc/여행정보페이지.png){: width="324" height="576"}

### 08. 모임 통장 관리 페이지 

1. 여행 목표 설정  목표금액, 목표기간, 월별 납입금액, 납입날짜 설정(월별 납입금액 및 납입날짜는 자동이체 계좌 등록 시 반영되어 자동이체 기능 동작)
2. 달성 인원  매 달 월별 납입금액에 해당되는 돈을 입금한 사람을 표시
<br>
![모임통장관리페이지](etc/모임통장관리페이지.png){: width="324" height="576"}

### 09. 여행 계획 관리 페이지 

1. 계획 기본 정보와 함께 여행의 날짜별로 계획 작성
2. 항공편부터 구글맵 기반의 장소까지 등록 가능

**항공권 검색**<br>
출발지와 도착지, 탑승 날짜를 선택해 다양한 시간대의 항공편을 검색하고 계획에 추가
<br>

![여행계획관리페이지](etc/여행계획관리페이지.png){: width="324" height="576"}
![항공권등록페이지](etc/항공권등록페이지.png){: width="324" height="576"}

**장소 검색**<br>
1. 구글맵 API를 사용한 장소 검색
2. 장소에 대한 자세한 정보 제공  주소, 가게정보, 리뷰, 평점 등  리뷰 더보기로 사진과 함께 자세한 리뷰가 제공
3. 원하는 장소를 계획에 추가
<br>
![장소등록페이지](etc/장소등록페이지.png){: width="324" height="576"}
![장소검색](etc/장소검색.png){: width="324" height="576"}

### 10. QR코드 결제 페이지 

**QR결제 코드 페이지**<br>
모임 통장 특성 상 하나의 카드로 여러명이 사용해야 하는 제한으로 여행에 포함된 모든 사용자가 QR코드 결제로 모임 통장을 사용할 수 있게 함

**결제 승인 페이지**<br>
가게에서 QR을 찍어 자동결제가 되게 하는 것을 구현
(실제로는 가게마다 가게명이 등록되어 있고 결제금액도 포스기에 저장되어 있지만 서비스 구현을 위해 가게 페이지를 생성함)
<br>
![QR결제페이지](etc/QR결제페이지.png){: width="324" height="576"}
![가게페이지](etc/가게페이지.png){: width="324" height="576"}

### 11. 환전 페이지 

1. 여행 가기 직전이 아닌 여행을 준비하는 기간동안 원하는 환율일 때 모임 통장 공금으로 미리 환전 가능 -> 여행 당일에 공항에서 환전해둔 외화를 받을 수 있음
2. 원하는 환율이 되었을 때 실시간 푸시알림 설정
<br>
![환전페이지](etc/환전페이지.png){: width="324" height="576"}

### 12. 소비 관리 페이지 

**모임 통장 거래 내역 조회**<br>
모임 통장의 입출금 내역 조회소비분류, 사용자분류로 소비 관리 가능<br>
[ 소비분류 ]<br>
식비, 숙박, 항공/교통, 환전, 쇼핑, 기타<br>
[ 사용자분류 ]<br>
공동, 멤버명<br>

NOAH 서비스 내의 송금, 환전, QR결제 등은 사용자와 소비타입을 자동으로 분류해서 사용자에게 편의성을 제공
<br>

![소비관리페이지](etc/소비관리페이지.png){: width="324" height="576"}

**소비분류, 사용자분류에 따른 비율 정보**<br>
모임 통장의 전체 입금액, 출금액 대비 선택한 분류 항목에 대한 합계와 비율을 시각적으로 표현
사용자별, 소비분류별로 거래내역과 비율을 계산해볼 수 있음
<br>

![사용자별소비분류](etc/사용자별소비분류.png){: width="324" height="576"}
![소비항목별분류](etc/소비항목별분류.png){: width="324" height="576"}

### 13. 인원 관리 페이지 

1. 멤버별 이번 달 납입금 정보 제공
2. 이메일로 새로운 멤버 초대 -> 초대 수락 여부 알림 전송
<br>
![인원관리페이지](etc/인원관리페이지.png){: width="324" height="576"}
![여행초대페이지](etc/여행초대페이지.png){: width="324" height="576"}

### 14. 알림 

3가지 타입의 알림이 알림창에서도 조회가 가능하며 FCM 푸시 알림 기능도 제공<br>

[ 알림 타입 ]<br>
TYPE1. 초대 알림 - 여행에 초대되었을 때 수락/거절 할 수 있는 알림<br>
TYPE2. 납부일안내 알림 - 여행의 납부일이 되었을 때 오전 9시 알림<br>
TYPE3. 환율 알림 - 설정해놓은 환율에 도달했을 때 실시간 알림<br>
<br>
![알림페이지](etc/알림페이지.png){: width="324" height="576"}
![여행초대수락거절](etc/여행초대수락거절.png){: width="324" height="576"}

### 15. 마이페이지 

**내 여행 기록**<br>
1. 지난여행, 계획한 여행 등 모든 나의 여행 조회
2. 지난여행은 사진과 함께 후기를 등록할 수 있음
3. 후기 등록 후 코멘트를 등록해 다른 사용자들에게 추가로 여행 정보를 제공 가능

**내 계좌**<br>
마이데이터 조회로 나의 모든 계좌 조회
<br>
![내여행기록페이지](etc/내여행기록페이지.png){: width="324" height="576"}
![내계좌페이지](etc/내계좌페이지.png){: width="324" height="576"}

**자동이체 설정**<br>
1. 여행별로 자동이체를 허용하고 싶을 때 내 계좌 중 한개를 선택해서 등록
2. 모임 통장 관리에 있는 납입일, 납입금에 따라 자동이체 진행
<br>
![자동이체설정페이지](etc/자동이체설정페이지.png){: width="324" height="576"}

## ✅ 프로젝트 후기

### 강준규
기본적으로 재미있었습니다. 적당히 하고싶은 거 다 하고싶어 모인 팀원들이었지만, 프로젝트에 집중해줘서 고마웠습니다. JPA를 처음 공부하면서 백엔드 구조에 대해 다시 공부할 수 있어 좋았고, 다대다 구조 관계에서 API 개발시 어떻게 해야하는지도 경험할 수 있어 좋았습니다. 더 나아가 구조 설계시 DTO 연결 과정에 많은 이슈가 생겨 해결 과정에서 더 많은 공부가 되었고, RequestParam으로 Id를 별개로 받는게 프론트와 통신이 용이하다는 것도 알 수 있게 되었습니다.

동시에 백엔드에서 설계한 부분에 대해서 프론트에서 직접 연결하여 그 과정에서 오류를 처리하며, 더 깔끔하고 좋은 코드를 작성할 수 있게 되었다고 생각합니다. 더 나아가 구글맵 api를 사용할 때 pwa 환경에서는 동적인 지도를 불러올 수 없단 걸 알고 정적으로 변경하여 완성함으로써 시각을 다양하게 바라보게 되었습니다.

### 여진구
이번 프로젝트를 통해 다양한 경험을 하고 성장할 수 있었습니다. 프론트엔드 개발을 주도하며 프로젝트의 전반적인 흐름을 체험하고, 컴포넌트화를 통해 개발 과정을 효율화하는 등의 긍정적인 경험들이 많았습니다. 예외 처리에도 많은 신경을 써서 안정적인 서비스 제공을 목표로 했습니다.

프로젝트를 진행하면서 몇 가지 아쉬운 점도 발견했지만, 이는 팀 전체가 함께 성장할 수 있는 소중한 배움의 기회였다고 생각합니다. 예를 들어, 혼자 프론트엔드 설계를 맡으며 시야가 좁아진 점, 디자인 시스템에 너무 많은 시간을 할애한 점 등은 다음 프로젝트에서는 보다 효율적인 접근을 시도하게 하는 계기가 될 것입니다.

또한, 이번 프로젝트를 통해 팀워크와 협업의 중요성을 다시 한번 깊이 깨닫게 되었습니다. 각자의 업무에만 집중하기보다는 팀원들과의 원활한 소통과 정보 공유가 프로젝트 성공의 핵심임을 이해하게 되었습니다. 이를 위해 문서화의 중요성, 소통의 효율성 증진 등에 더 많은 주의를 기울여야겠다는 다짐을 하게 되었습니다.

이번 프로젝트가 제 개인적인 성장뿐만 아니라, 팀원 모두에게도 유익한 경험이 되었기를 바라며, 프로젝트에서 배운 교훈을 바탕으로 앞으로 더 나은 개발자가 되기 위해 노력할 것입니다. 우리 모두가 함께 만들어낸 결과물에 자부심을 느끼며, 다음 프로젝트에서는 더욱 발전된 모습으로 도전할 것입니다.
