import Header from "../components/common/Header";
import styles from "./PlanningCreatePage.module.css";
import { ReactComponent as Airplane } from "./../assets/Icon/Airplane.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import { ReactComponent as Calender } from "./../assets/Icon/Calender.svg";
import RoundButton from "../components/common/RoundButton";
import { createPlan } from "../api/plan/Plan";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import showToast from "../components/common/Toast";

// 날짜 유효성 검사 및 형식 변환 함수
const validateAndFormatDate = (dateStr) => {
  const regex = /^(\d{4})(\d{2})(\d{2})$/; // yyyyMMdd 형식으로 변경
  const parts = dateStr.match(regex);
  if (!parts) return { isValid: false, formattedDate: "" };

  const year = parseInt(parts[1], 10);
  const month = parseInt(parts[2], 10);
  const day = parseInt(parts[3], 10);

  const date = new Date(year, month - 1, day);
  if (
    date.getFullYear() === year &&
    date.getMonth() + 1 === month &&
    date.getDate() === day
  ) {
    return {
      isValid: true,
      formattedDate: `${year}-${month.toString().padStart(2, "0")}-${day
        .toString()
        .padStart(2, "0")}`,
    };
  } else {
    return { isValid: false, formattedDate: "" };
  }
};

export default function PlanningCreatePage() {
  const { travelId } = useParams();
  const [place, setPlace] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const navigate = useNavigate();

  const handlePlaceChange = (e) => {
    setPlace(e.target.value);
  };

  const handleCreatePlan = async () => {
    if (startDate === "" || endDate === "" || place === "") {
      showToast("필요한 정보를 모두 입력해 주세요.");
      return;
    }
    const startDateObj = new Date(startDate);
    const endDateObj = new Date(endDate);

    if (endDateObj < startDateObj) {
      showToast("입력 정보를 확인해주세요.");
      return;
    }

    const object = {
      travelId: travelId,
      startDate: startDate,
      endDate: endDate,
      travelStart: true,
      country: place,
    };

    try {
      const res = await createPlan(object);
      if (res.status === "SUCCESS") {
        navigate(`/trip/${travelId}/planning/${res.data}`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleDateChange = (setter) => (e) => {
    const value = e.target.value.replace(/[^0-9]/g, ""); // 비숫자 문자 제거
    let formattedValue = "";

    if (value.length > 6) {
      // "yyyymmdd" 형식으로 입력된 경우 "yyyy-mm-dd"로 변환
      formattedValue = `${value.slice(0, 4)}-${value.slice(4, 6)}-${value.slice(
        6,
        8
      )}`;
    } else if (value.length > 4) {
      // "yyyymm" 형식으로 입력된 경우 "yyyy-mm"로 변환
      formattedValue = `${value.slice(0, 4)}-${value.slice(4, 6)}`;
    } else if (value.length > 2) {
      // "yyyy" 형식으로 입력된 경우 "yyyy"로 변환 (년도만 입력된 경우)
      formattedValue = value.slice(0, 4);
    } else {
      // "yy" 형식 또는 그 이하의 길이로 입력된 경우 그대로 사용
      formattedValue = value;
    }

    // 상태 업데이트는 실제 날짜 유효성 검사를 통과했을 때만 진행
    if (formattedValue.length === 10) {
      // "yyyy-mm-dd" 길이 체크
      const { isValid, formattedDate } = validateAndFormatDate(value);
      if (isValid) {
        setter(formattedDate); // 날짜 형식이 유효하면 변환된 날짜로 상태 업데이트
      } else {
        // 유효하지 않은 날짜 입력 처리
        console.error("Invalid date format");
      }
    } else {
      setter(formattedValue); // 유효성 검사를 하지 않고 현재 입력값으로 상태 업데이트
    }
  };

  return (
    <>
      <Header LeftIcon="Arrow" Title="계획 생성" />
      <div className={styles.planningCreateContainer}>
        <div className={styles.iconBox}>
          <Airplane className={styles.icon} />
        </div>
        <div className={styles.labelMedium}>여행 국가</div>
        <div className={styles.setBox}>
          <Mark className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            value={place}
            onChange={handlePlaceChange}
            placeholder="장소를 입력하세요"
          ></input>
        </div>
        <div className={styles.labelMedium}>여행 시작 날짜</div>
        <div className={styles.setBox}>
          <Calender className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            value={startDate}
            onChange={handleDateChange(setStartDate)}
            placeholder="날짜를 설정하세요"
          ></input>
        </div>
        <div className={styles.labelMedium}>여행 종료 날짜</div>
        <div className={styles.setBox}>
          <Calender className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            value={endDate}
            onChange={handleDateChange(setEndDate)}
            placeholder="날짜를 설정하세요"
          ></input>
        </div>
        <div className={styles.buttonBox} onClick={handleCreatePlan}>
          <RoundButton className={styles.button} buttonText="새 계획 생성" />
        </div>
      </div>
    </>
  );
}
