import React, { useState } from "react";

export default function Notification() {
  const [startX, setStartX] = useState(0); // 스와이프 시작 X 좌표
  const [isSwiping, setIsSwiping] = useState(false); // 스와이핑 중인지 여부
  const [translateX, setTranslateX] = useState(0); // 변환(이동) X 좌표

  // 터치 또는 마우스 다운 시작 위치 설정
  const handleStart = (clientX) => {
    setStartX(clientX);
    setIsSwiping(false);
  };

  // 터치 또는 마우스 이동 중 스와이프 감지 및 이동 거리 계산
  const handleMove = (clientX) => {
    const moveX = clientX - startX;
    if (Math.abs(moveX) > 10) {
      // 스와이프로 간주되는 최소 거리
      setIsSwiping(true);
      setTranslateX(moveX);
    }
  };

  // 터치 또는 마우스 업 시 이동 완료 및 최종 위치 설정
  const handleEnd = (e) => {
    if (isSwiping) {
      if (translateX < -50) {
        // 왼쪽으로 충분히 스와이프한 경우
        setTranslateX((-16.67 * window.innerWidth) / 100); // 16.67vw만큼 왼쪽으로 이동
      } else if (translateX > 50) {
        // 오른쪽으로 충분히 스와이프한 경우
        setTranslateX(0); // 원래 위치로 복귀
      } else {
        // 스와이프가 충분히 강하지 않은 경우, 초기 상태로 복귀
        setTranslateX(startX === 0 ? 0 : (-16.67 * window.innerWidth) / 100);
        // 클릭 이벤트 발생
      }
    } else {
      e.stopPropagation();
      console.log("터치");
    }
    setIsSwiping(false);
  };

  // 이벤트 핸들러 바인딩
  const handleTouchStart = (e) => handleStart(e.touches[0].clientX);
  const handleTouchMove = (e) => handleMove(e.touches[0].clientX);
  const handleTouchEnd = handleEnd;

  const handleMouseDown = (e) => handleStart(e.clientX);
  const handleMouseMove = (e) => {
    if (e.buttons !== 1) return; // 마우스 왼쪽 버튼이 아닐 경우 무시
    handleMove(e.clientX);
  };
  const handleMouseUp = handleEnd;

  const notificationContainer = {
    width: "120vw",
    height: "27.77vw",
    cursor: "pointer",
    display: "flex",
    alignItems: "center",
    overflow: "hidden",
    transform: `translateX(${translateX}px)`, // 스와이프에 따라 X축으로 이동
    transition: "transform 0.3s ease", // 부드러운 애니메이션
  };

  const notificationInfo = {
    paddingLeft: "8.89vw",
    width: "100vw",
  };

  const deleteButton = {
    background: "#E74733",
    color: "white",
    width: "16.67vw",
    height: "28vw",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 600,
    fontSize: "3.35vw",
    lineHeight: "150%",
  };

  const line = {
    width: "100vw",
    height: "1.39vw",
    background: "#f6f6f6",
  };

  const labelSmall = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 700,
    fontSize: "3.35vw",
    lineHeight: "150%",
    color: "#000000",
  };

  const paragraphSmall = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 500,
    fontSize: "3.33vw",
    lineHeight: "160%",
    color: "#898989",
  };

  return (
    <>
      <div
        style={notificationContainer}
        //onMouseDown={handleMouseDown}
        //onMouseMove={handleMouseMove}
        //onMouseUp={handleMouseUp}
        onTouchStart={handleTouchStart}
        onTouchMove={handleTouchMove}
        onTouchEnd={handleTouchEnd}
      >
        <div style={notificationInfo}>
          <div style={labelSmall}>출금 안내</div>
          <div style={{ ...paragraphSmall, color: "black" }}>
            <span style={labelSmall}>B106여행가자</span> 모임에서 출금이
            발생했습니다.
          </div>
          <div style={paragraphSmall}>2024.03.08 14:57:32</div>
        </div>
        <div style={deleteButton} onClick={() => console.log("삭제")}>
          삭제
        </div>
      </div>
      <div style={line}></div>
    </>
  );
}
