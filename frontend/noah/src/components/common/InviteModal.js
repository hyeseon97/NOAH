import React, { useState } from "react";
import { ReactComponent as Cancel } from "../../assets/Icon/Cancel.svg";
import InputSmall from "../common/InputSmall";
import InviteButton from "../common/InviteButton";
import showToast from "./Toast";
import { useParams } from "react-router-dom";
import { travelMemberInvite } from "../../api/travel/Travel";

// Modal 컴포넌트를 export default function 형식으로 정의
export default function InviteModal({
  onDelete,
  isVisible,
  closeModal,
  travelId,
}) {
  const [email, setEmail] = useState("");
  const [warningText, setWarningText] = useState("");

  const handleInvite = async () => {
    if (!email) {
      showToast("이메일을 입력해주세요.");
      return;
    }
    try {
      // API 호출에 필요한 객체 구성
      const object = { email, travelId };
      const res = await travelMemberInvite(object); // 멤버 초대 함수 호출
      console.log(res);
      if (res.message === "여행에 이미 가입된 회원입니다.") {
        setWarningText("여행에 이미 가입된 회원입니다.");
      } else if (res.message === "멤버를 찾을 수 없습니다.") {
        setWarningText("멤버를 찾을 수 없습니다.");
      } else {
        showToast("초대가 성공적으로 완료되었습니다.");
        setEmail("");
        closeModal(); // 초대 후 모달 닫기
      }
    } catch (error) {
      showToast("초대에 실패했습니다: " + error.message);
    }
  };

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
        <InputSmall
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholderText={"이메일을 입력하세요."}
        />
        <div onClick={handleInvite}>
          <InviteButton buttonText="초대" warningText={warningText} />
        </div>
      </div>
    </div>
  );
}
