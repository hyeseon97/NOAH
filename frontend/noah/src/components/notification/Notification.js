import React, { useState } from "react";
import { ReactComponent as Cancel } from "../../assets/Icon/Cancel.svg";
import { ReactComponent as Ship } from "../../assets/Icon/Ship.svg";
import showToast from "../common/Toast";

export default function Notification({ isInvitation, onDelete }) {
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
      handleClick();
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

  const title = {
    //labelXL
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: "600",
    fontSize: "7.90vw",
    lineHeight: "150%",
    textAlign: "center",
    color: "#000000",
  };

  const button = {
    textAlign: "center",

    fontFamily: "Pretendard",
    fontSize: "4.44vw",
    fontWeight: "normal",
    lineHeight: "150%",
    color: "white",
    backgroundColor: "black",
    width: "70vw",
    height: "7.77vw",
    margin: "5vw auto 1vw auto",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    cursor: "pointer",
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

  const [isModalVisible, setIsModalVisible] = useState(false); // 모달 보이기/숨기기 상태
  // 기존 코드...

  const [isDeleteClicked, setIsDeleteClicked] = useState(false);
  // 클릭 이벤트에서 모달 상태 변경
  const handleClick = () => {
    if (!isDeleteClicked) {
      setIsModalVisible(true); // 모달을 보이게 설정
    }
  };

  // 모달을 닫는 함수
  const closeModal = () => {
    setIsModalVisible(false);
  };

  const Modal = ({ onDelete }) => (
    <div
      style={{
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        background: "white",
        zIndex: 100,
        width: "88.8vw",
        height: "83.3vw",
        borderRadius: "2.77vw",
        border: "0.277vw solid #E1E1E1",
        backdropFilter: "blur(0.277vw)",
      }}
    >
      {/* 모달 내용 */}
      <div onClick={closeModal}>
        <Cancel
          style={{
            width: "6.67vw",
            height: "6.67vw",
            marginTop: "4.44vw",
            marginLeft: "3.33vw",
            cursor: "pointer",
          }}
        />
      </div>
      <Ship
        style={{
          width: "26.67vw",
          height: "26.67vw",
          marginTop: "4.44vw",
          marginLeft: "31.11vw",
        }}
      />
      <div style={title}>B106여행가자</div>
      <div style={{ ...paragraphSmall, textAlign: "center" }}>
        목표금액: 1,000,000원
      </div>
      <div
        style={button}
        onClick={() => {
          closeModal();
          showToast("초대를 수락하셨습니다.");
        }}
      >
        탑승
      </div>
      <div
        style={{ ...paragraphSmall, textAlign: "center", cursor: "pointer" }}
        onClick={() => {
          closeModal();
          onDelete();
        }}
      >
        거절
      </div>
    </div>
  );

  return (
    <>
      <div
        style={notificationContainer}
        //onMouseDown={handleMouseDown}
        //onMouseMove={handleMouseMove}
        //onMouseUp={handleMouseUp}
        onTouchStart={handleTouchStart}
        ㅇ
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
        <div
          style={deleteButton}
          onClick={(e) => {
            e.stopPropagation();

            setTimeout(() => {
              onDelete();
              setTimeout(() => setIsDeleteClicked(true), 0);
            }, 0);
          }}
        >
          삭제
        </div>
      </div>
      <div style={line}></div>
      {isInvitation && isModalVisible && <Modal onDelete={onDelete} />}
    </>
  );
}
