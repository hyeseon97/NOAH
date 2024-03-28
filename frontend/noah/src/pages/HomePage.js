import styles from "./HomePage.module.css";
import { useState, useRef } from "react";
import { ReactComponent as Notification } from "../assets/Icon/Notification.svg";
import { ReactComponent as My } from "../assets/Icon/My.svg";
import { useNavigate } from "react-router-dom";
import Trip from "../components/trip/Trip";
import Exchange from "./../components/exchange/Exchange";
import sample1 from "../assets/Image/sample1.jpg";
import sample2 from "../assets/Image/sample2.png";

export default function HomePage() {
  const navigate = useNavigate();
  const trips = [{ id: 1 }, { id: 2 }, { id: 3 }]; // 여행 데이터 저장

  const handleNotificationClick = () => {
    navigate("/notification");
  };

  const handleMyClick = () => {
    navigate("/mypage");
  };

  const handleTripClick = (index) => {
    console.log(`Trip ${index} 클릭됨`);
    navigate(`/trip/${index}`);
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
        {trips.map((trip, index) => (
          <Trip
            fromHome={true}
            onClick={() => handleTripClick(index)}
            tripid={trip.id}
          /> // index 가 아니라 여행 id 전달하면 된다
        ))}
        <Trip isLast={true} />
        <div style={{ marginRight: "5vw" }}></div>
      </div>
      <div className={styles.exchangeContainer}>
        <Exchange />
      </div>
      <div className={styles.paragraphSmall}>
        {formatTime(new Date())} 환율 기준
      </div>
      <div className={styles.reviewHeader}>추천 후기</div>
      <div className={styles.reviewContainer}>
        <div className={styles.review}>
          <img src={sample1} alt="Sample 1" className={styles.reviewImage} />
          <div className={styles.place}>준규모리현 벚꽃공원</div>
        </div>
        <div className={styles.review}>
          <img src={sample2} alt="Sample 2" className={styles.reviewImage} />
          <div className={styles.place}>오오건건현 스마트료칸</div>
        </div>
      </div>
    </>
  );
}
