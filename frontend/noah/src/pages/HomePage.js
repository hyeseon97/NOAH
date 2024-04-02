import styles from "./HomePage.module.css";
import { useState, useRef, useEffect } from "react";
import { ReactComponent as Notification } from "../assets/Icon/Notification.svg";
import { ReactComponent as My } from "../assets/Icon/My.svg";
import { useNavigate } from "react-router-dom";
import Trip from "../components/trip/Trip";
import Exchange from "./../components/exchange/Exchange";
import { getAllGroupAccount } from "../api/groupaccount/GroupAccount";
import showToast from "../components/common/Toast";
import { getExchangeRate } from "../api/exchange/Exchange";
import ClipLoader from "react-spinners/ClipLoader";
import {
  getRecommendReviewInfo,
  getRecommendReviewInfoNonLogin,
} from "../api/suggest/Suggest";

export default function HomePage() {
  const navigate = useNavigate();
  const [trips, setTrips] = useState([]); // 여행 데이터 저장
  const [isLoading, setIsLoading] = useState(true);
  const [exchangeRate, setExchangeRate] = useState([]);
  const [krwAmount, setKrwAmount] = useState();
  const [foreignAmount, setForeignAmount] = useState("1");
  const [currency, setCurrency] = useState("USD");
  const [travelId, setTravelId] = useState(0);
  const [recommendReviewInfo, setRecommendReviewInfo] = useState([]);
  const [recommendReviews, setRecommendReviews] = useState([]);
  const [idx, setIdx] = useState(0);
  const [isImageLoading, SetIsImageLoading] = useState(true);

  const handleNotificationClick = () => {
    if (localStorage.getItem("accessToken") === null) {
      showToast("로그인 후 이용해보세요.");
      navigate("/login");
      return;
    }

    navigate("/notification");
  };

  const handleMyClick = () => {
    if (localStorage.getItem("accessToken") === null) {
      showToast("로그인 후 이용해보세요.");
      navigate("/login");
      return;
    }
    navigate("/mypage");
  };

  /* Trip 컴포넌트 스와이프 */
  const containerRef = useRef(null);
  const [startX, setStartX] = useState(0);
  const [startTime, setStartTime] = useState(0); // 스와이프 시작 시간을 저장하기 위한 상태

  const handleSwipeStart = (position) => {
    setStartX(position);
    setStartTime(Date.now()); // 스와이프 시작 시간 저장
  };

  const handleSwipeEnd = (endPosition) => {
    const endTime = Date.now(); // 스와이프가 끝난 시간
    const moveDistance = startX - endPosition;
    const moveTime = endTime - startTime; // 총 이동 시간 계산

    if (Math.abs(moveDistance) >= window.innerWidth * 0.05 || moveTime > 150) {
      // 이동 거리가 충분히 길거나 이동 시간이 150ms 이상인 경우 스와이프로 판단
      if (containerRef.current) {
        const direction = moveDistance > 0 ? 1 : -1;
        if (moveDistance > 0) {
          if (idx < trips.length) setIdx((prev) => prev + 1);
        } else if (moveDistance < 0) {
          if (idx > 0) setIdx((prev) => prev - 1);
        }

        containerRef.current.scrollTo({
          left:
            containerRef.current.scrollLeft +
            direction * window.innerWidth * 0.8744,
          behavior: "smooth",
        });
      }
    } else if (
      Math.abs(moveDistance) < window.innerWidth * 0.05 &&
      moveTime < 150
    ) {
      // 이동 거리가 짧고 이동 시간이 150ms 미만인 경우 클릭으로 판단
      // 클릭 이벤트 처리
      // 여기서는 별도의 클릭 이벤트 처리 로직을 실행하지 않음
    }
  };

  // 마우스 이벤트 핸들러
  const handleMouseDown = (e) => handleSwipeStart(e.clientX);
  const handleMouseUp = (e) => handleSwipeEnd(e.clientX);

  // 터치 이벤트 핸들러
  const handleTouchStart = (e) => handleSwipeStart(e.touches[0].clientX);
  const handleTouchEnd = (e) => handleSwipeEnd(e.changedTouches[0].clientX);

  function formatTime(date) {
    const hours = date.getHours();
    const minutes = date.getMinutes();

    // 시간과 분이 10보다 작으면 앞에 '0'을 붙여 두 자리로 만듦
    const formattedHours = hours < 10 ? `0${hours}` : hours;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;

    // '15:36' 형태의 문자열로 결합
    return `${formattedHours}:${formattedMinutes}`;
  }

  useEffect(() => {
    setIsLoading(true);
    const fetchGroupAccounts = async () => {
      try {
        const response = await getAllGroupAccount();
        if (response.data === null) setTrips([]);
        else {
          setTrips(response.data); // API로부터 받아온 여행 데이터를 상태에 저장
          setTravelId(response.data[0]?.travelId);
        }
      } catch (error) {
        setTrips([]);
      } finally {
        setTimeout(() => setIsLoading(false), 100);
      }
    };
    fetchGroupAccounts();

    const fetchExchangeRate = async () => {
      try {
        const res = await getExchangeRate();
        setExchangeRate(res.data);
      } catch (error) {
        console.log(error);
      } finally {
      }
    };
    fetchExchangeRate();

    const fetchGetRecommendReviewInfo = async () => {
      try {
        let res = null;
        if (localStorage.getItem("accessToken") === null) {
          res = await getRecommendReviewInfoNonLogin();
        } else {
          res = await getRecommendReviewInfo();
        }

        if (res.status === "SUCCESS") {
          setRecommendReviews(res.data);
          setRecommendReviewInfo(res.data[0]);
          console.log(res.data);
        } else {
        }
      } catch (error) {
      } finally {
        setTimeout(() => SetIsImageLoading(false), 200);
      }
    };
    fetchGetRecommendReviewInfo();
  }, []);

  useEffect(() => {
    if (recommendReviews.length === 0) return;
    if (idx === recommendReviews.length) return;

    setRecommendReviewInfo(recommendReviews[idx]);
    if (trips.length > 0) setTravelId(trips[idx]?.travelId);
  }, [idx]);

  const handleReviewClick = () => {
    if (localStorage.getItem("accessToken") === null) {
      showToast("로그인 후 이용해보세요.");
      navigate("/login");
      return;
    }

    navigate(`/trip/${travelId}/review`);
  };

  return (
    <>
      <div className={styles.headerContainer}>
        <div className={styles.headerLogo}>NOAH</div>
        <div className={styles.headerIcon}>
          <Notification
            className={styles.icon}
            onClick={() => handleNotificationClick()}
          />
          <My className={styles.icon} onClick={() => handleMyClick()} />
        </div>
      </div>
      {isLoading && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "80vh",
            }}
          >
            <ClipLoader />
          </div>
        </>
      )}
      {!isLoading && (
        <>
          <div
            className={styles.tripContainer}
            ref={containerRef}
            onMouseDown={handleMouseDown}
            onMouseUp={handleMouseUp}
            onMouseLeave={handleMouseUp} // 컨테이너 밖으로 마우스가 나갔을 때
            onTouchStart={handleTouchStart}
            onTouchEnd={handleTouchEnd}
          >
            <div style={{ marginLeft: "5vw" }}></div>
            {isLoading && (
              <>
                <Trip isLoading={true} />
              </>
            )}

            {trips.map((trip, index) => (
              <Trip
                key={index}
                onClick={() => navigate(`/trip/${trip.travelId}`)}
                title={trip.title}
                bankName={trip.bankName}
                accountNumber={trip.accountNumber}
                amount={trip.amount}
                targetAmount={trip.targetAmount}
                fromHome={true}
                travelId={trip.travelId}
              />
            ))}
            <Trip isLast={true} />
            <div style={{ marginRight: "5vw" }}></div>
          </div>

          <div className={styles.exchangeContainer}>
            <Exchange
              exchangeRateInfo={exchangeRate}
              krwAmount={krwAmount}
              foreignAmount={foreignAmount}
              setKrwAmount={setKrwAmount}
              setForeignAmount={setForeignAmount}
              currency={currency}
              setCurrency={setCurrency}
            />
          </div>
          <div className={styles.paragraphSmall}>
            {formatTime(new Date())} 환율 기준
          </div>
          <div className={styles.reviewHeaderContainer}>
            <div className={styles.reviewHeader}>추천 후기</div>
            <div
              className={styles.labelSmallReview}
              onClick={() => handleReviewClick()}
            >
              리뷰 보러가기
            </div>
          </div>
          <div className={styles.reviewContainer}>
            <div
              className={styles.review}
              onClick={() =>
                navigate(
                  `/trip/${travelId}/review/${recommendReviewInfo.reviewId}`
                )
              }
            >
              {isImageLoading && (
                <>
                  <div
                    style={{
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                    }}
                  >
                    <ClipLoader />
                  </div>
                </>
              )}
              {!isImageLoading && (
                <>
                  <img
                    src={recommendReviewInfo?.imageUrl}
                    alt="Sample 1"
                    className={styles.reviewImage}
                  />
                </>
              )}
              <div className={styles.place} style={{ fontSize: "4.44vw" }}>
                {recommendReviewInfo?.country}
              </div>
              <div className={styles.place}>
                {new Intl.NumberFormat("ko-KR").format(
                  recommendReviewInfo?.expense
                )}{" "}
                원
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
}
