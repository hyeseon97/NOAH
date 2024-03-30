import React from "react";
import { ReactComponent as Cancel } from "../../assets/Icon/Cancel.svg";
import { ReactComponent as Ship } from "../../assets/Icon/Ship.svg";
import InputSmall from "../common/InputSmall";
import InviteButton from "../common/InviteButton";
import showToast from "./Toast";

// Modal 컴포넌트를 export default function 형식으로 정의
export default function Modal({ onDelete, isVisible, closeModal }) {
  // 모달이 보이지 않을 때는 아무것도 렌더링하지 않음
  if (!isVisible) return null;

  return (
    <div
      style={{
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        background: "white",
        zIndex: 100,
        width: "88.8vw",
        height: "69.17vw",
        borderRadius: "2.77vw",
        border: "0.277vw solid #E1E1E1",
        backdropFilter: "blur(0.277vw)",
      }}
    >
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
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          width: "100%",
          marginTop: "10vw",
        }}
      >
        <InputSmall />
        <InviteButton buttonText="초대" warningText="이미 초대된 유저입니다." />
      </div>
    </div>
  );
}
