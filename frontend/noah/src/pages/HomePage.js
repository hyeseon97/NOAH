import styles from "./HomePage.module.css";
import { useState, useRef } from "react";
import { ReactComponent as Notification } from "../assets/Icon/Notification.svg";
import { ReactComponent as My } from "../assets/Icon/My.svg";
import { useNavigate } from "react-router-dom";
import Trip from "../components/trip/Trip";

export default function HomePage() {
  const navigate = useNavigate();
  const trips = [{}, {}, {}]; // 여행 데이터 저장

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

  const handleSwipeStart = (position) => {
    setStartX(position);
  };

  const handleSwipeEnd = (endPosition) => {
    if (containerRef.current) {
      const moveDistance = startX - endPosition;
      if (Math.abs(moveDistance) >= window.innerWidth * 0.05) {
        const direction = moveDistance > 0 ? 1 : -1; // 스와이프 방향 결정
        // 다음 Trip으로 스무스하게 스크롤
        containerRef.current.scrollTo({
          left:
            containerRef.current.scrollLeft +
            direction * window.innerWidth * 0.8744,
          behavior: "smooth",
        });
      }
    }
  };

  // 마우스 이벤트 핸들러
  const handleMouseDown = (e) => handleSwipeStart(e.clientX);
  const handleMouseUp = (e) => handleSwipeEnd(e.clientX);

  // 터치 이벤트 핸들러
  const handleTouchStart = (e) => handleSwipeStart(e.touches[0].clientX);
  const handleTouchEnd = (e) => handleSwipeEnd(e.changedTouches[0].clientX);

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
          <Trip onClick={() => handleTripClick(index)} /> // index 가 아니라 여행 id 전달하면 된다
        ))}
        <Trip isLast={true} />
        <div style={{ marginRight: "5vw" }}></div>
      </div>
    </>
  );
}
