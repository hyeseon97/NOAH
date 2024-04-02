import Header from "../components/common/Header";
import styles from "./PlanningCreatePage.module.css";
import { ReactComponent as Airplane } from "./../assets/Icon/Airplane.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import { ReactComponent as Calender } from "./../assets/Icon/Calender.svg";
import RoundButton from "../components/common/RoundButton";
import { createPlan } from "../api/plan/Plan";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

export default function PlanningCreatePage() {
  const { travelId } = useParams();
  const [place, setPlace] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const navigate = useNavigate();

  const handlePlaceChange = (e) => {
    setPlace(e.target.value);
  };
  const handleStartDateChange = (e) => {
    setStartDate(e.target.value);
  };
  const handleEndDateChange = (e) => {
    setEndDate(e.target.value);
  };

  const handleCreatePlan = async () => {
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
            onChange={handleStartDateChange}
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
            onChange={handleEndDateChange}
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
