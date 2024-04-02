import React, { useEffect, useState } from "react";
import Header from "./../components/common/Header";
import { ReactComponent as Next } from "./../assets/Icon/Next.svg";
import { ReactComponent as SmallPlane } from "./../assets/Icon/SmallPlane.svg";
import { ReactComponent as TrashCan } from "./../assets/Icon/TrashCan.svg";
import { ReactComponent as Plus } from "./../assets/Icon/Plus.svg";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
// import DayCalculate from "../components/trip/DayCalculate";
import { useParams } from "react-router-dom";
import style from "./PlanningPage.module.css";

import {
  getDetailPlan,
  getDetailPlanList,
  updateDetailPlan,
  deleteDetailPlan,
  createDetailPlan,
} from "../api/detailplan/DetailPlan";
import { useNavigate } from "react-router-dom";

const getTimeFromString = (dateTimeString) => {
  const date = new Date(dateTimeString);
  // 옵션을 설정하여 시간과 분만 표시하도록 합니다.
  const timeOptions = { hour: "2-digit", minute: "2-digit" };
  return date.toLocaleTimeString("ko-KR", timeOptions);
};

export default function PlanningPage() {
  const navigate = useNavigate();
  // const { planId } = useParams();
  const [currentDay, setCurrentDay] = useState(1);
  let { travelId, planId } = useParams();
  const [currentDate, setCurrentDate] = useState("");

  const [plan, setPlan] = useState({
    id: 1,
    title: "오사카 일본",
    start_date: "2024/03/21",
    end_date: "2024/03/24",
    travel_start: false,
    country: "일본",
    travel_id: 1,
  });

  const [plane, setPlane] = useState({
    id: 1,
    departure: "2024/03/24/16:30",
    d_airport: "인천",
    d_gate: 3,
    arrival: "2024/03/24/19:30",
    a_airport: "오사카",
    travel_id: 1,
  });

  const [detailPlans, setDetailPlans] = useState([]);

  const getFormattedDate = (dateString) => {
    const date = new Date(dateString);
    return `${date.getFullYear()}/${String(date.getMonth() + 1).padStart(
      2,
      "0"
    )}/${String(date.getDate()).padStart(2, "0")}`;
  };

  // // 현재 선택된 날짜로부터 YYYY/MM/DD 형태의 문자열을 얻습니다.
  // useEffect(() => {
  //   if (daysList.length > 0 && currentDayIndex >= 0) {
  //     // 선택된 day 인덱스를 실제 날짜로 변환합니다.
  //     const startDate = new Date(plan.start_date);
  //     const currentDate = new Date(
  //       startDate.setDate(startDate.getDate() + currentDayIndex)
  //     );
  //     setCurrentDate(getFormattedDate(currentDate));
  //   }
  // }, [currentDayIndex, daysList, plan.start_date]);

  const filteredDetailPlans = detailPlans.filter(
    (detailPlan) => detailPlan.day === currentDay
  );

  const handleDayChange = (newDay) => {
    setCurrentDay(newDay);
  };

  const handleAddPlanClick = () => {
    navigate("planningTest", { state: { planId: plan.id, day: currentDay } });
  };

  function removeDuplicates(detailPlans) {
    const unique = detailPlans.reduce((acc, current) => {
      const x = acc.find((item) => item.detailPlanId === current.detailPlanId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      }
    }, []);
    return unique;
  }

  const loadDetailPlan = async () => {
    try {
      const response = await getDetailPlanList(planId);
      if (
        response.status === "SUCCESS" &&
        Array.isArray(response.data.detailPlanList)
      ) {
        // 중복을 제거한 데이터로 상태 업데이트
        const uniqueDetailPlans = removeDuplicates(
          response.data.detailPlanList
        );
        setDetailPlans(uniqueDetailPlans);
      } else {
        console.error(
          "detailPlanList is not an array or status is not SUCCESS"
        );
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadDetailPlan();
  }, []);

  const handleDeleteDetailPlan = async (detailPlanId) => {
    try {
      // deleteDetailPlan 함수가 성공적으로 수행되었다고 가정
      await deleteDetailPlan(detailPlanId);
      // 삭제 후 detailPlans 상태 업데이트
      setDetailPlans((prevDetailPlans) =>
        prevDetailPlans.filter((plan) => plan.detailPlanId !== detailPlanId)
      );
    } catch (error) {
      console.error("삭제 작업 중 오류가 발생했습니다.", error);
    }
  };

  return (
    <>
      <Header LeftIcon="Arrow" Title="계획" />
      <div className={style.headStyle}>
        <div>
          <div className={style.bigFont}>{plan.title}</div>
          <div className={style.middleFont}>
            {plan.start_date} ~ {plan.end_date}
          </div>
        </div>
        <Edit className={style.editButton} />
      </div>
      {/* <div className={style.midStyle}>
        <Next className={style.privousButton} />
        <div>
          <div className={style.smallFont}>
            {day.detailday} {day.detailweekday}
          </div>
          <div className={style.bigFont}>DAY {day.sqday}</div>
        </div>
        <Next className={style.nextButton} />
      </div> */}
      <DayCalculate
        startDate={plan.start_date}
        endDate={plan.end_date}
        onDayChange={handleDayChange}
      />
      <div>
        <div>
          <div className={style.planeBoxStyle}>
            <div className={style.planeInfo}>
              <div className={style.middleFont}>{plane.a_airport}</div>

              <div className={style.smallFont}>
                {getTimeFromString(plane.arrival)}
              </div>
            </div>
            <SmallPlane className={style.smallPlaneStyle} />
            <div className={style.planeInfo}>
              <div className={style.middleFont}>{plane.d_airport}</div>
              <div className={style.smallFont}>
                {getTimeFromString(plane.departure)}
              </div>
            </div>
          </div>
          <div>
            {filteredDetailPlans.map((detailPlan) => (
              <div key={detailPlan.detailPlanId} className={style.boxStyle}>
                <img
                  src={detailPlan.url}
                  alt="Detail Plan"
                  className={style.imgStyle}
                />
                <div className={style.placeInfoStyle}>
                  <div className={style.smallBoldFont}>{detailPlan.place}</div>
                  <div className={style.smallFont}>{detailPlan.memo}</div>
                  <div className={style.smallFont}>
                    사용자 평점 {detailPlan.time}
                  </div>
                </div>
                <TrashCan
                  className={style.trashCanButton}
                  onClick={() =>
                    handleDeleteDetailPlan(detailPlan.detailPlanId)
                  }
                />
              </div>
            ))}
            {/* 새로운 계획 추가 버튼 등 나머지 UI 요소 */}
          </div>
        </div>
        <div className={style.addDetailPlanStyle} onClick={handleAddPlanClick}>
          <Plus />
          <div className={style.middleFont}>새로운 계획 추가</div>
        </div>
      </div>
    </>
  );
}

const DayCalculate = ({
  startDate: initialStartDate,
  endDate: initialEndDate,
  onDayChange,
}) => {
  // useState를 이용하여 초기 상태 설정
  const [currentDayIndex, setCurrentDayIndex] = useState(0);
  const [daysList, setDaysList] = useState([]);

  // 시작 날짜와 종료 날짜를 이용하여 daysList를 계산하는 함수
  const calculateDays = () => {
    if (!initialStartDate || !initialEndDate) return;

    const start = new Date(initialStartDate);
    const end = new Date(initialEndDate);
    const oneDay = 24 * 60 * 60 * 1000;
    const differenceInTime = end - start;
    const differenceInDays = Math.round(differenceInTime / oneDay);

    const newDaysList = [];
    for (let i = 0; i <= differenceInDays; i++) {
      newDaysList.push(i + 1); // 실제 day 값은 1부터 시작
    }

    setDaysList(newDaysList);
    setCurrentDayIndex(0); // 컴포넌트 내부 인덱스는 0으로 초기화
    onDayChange(1); // 부모 컴포넌트에는 실제 day 값을 전달
  };

  // 컴포넌트 마운트 후 calculateDays 함수를 실행
  useEffect(() => {
    calculateDays();
  }, [initialStartDate, initialEndDate]);

  // 이전 Day로 이동
  const goToPreviousDay = () => {
    setCurrentDayIndex((prev) => {
      const newIndex = prev > 0 ? prev - 1 : prev;
      onDayChange(newIndex + 1); // 실제 day 값으로 업데이트
      return newIndex;
    });
  };

  // 다음 Day로 이동
  const goToNextDay = () => {
    setCurrentDayIndex((prev) => {
      const newIndex = prev < daysList.length - 1 ? prev + 1 : prev;
      onDayChange(newIndex + 1); // 실제 day 값으로 업데이트
      return newIndex;
    });
  };

  return (
    <>
      {daysList.length > 0 && (
        <div className={style.midStyle}>
          <Next className={style.previousButton} onClick={goToPreviousDay} />
          <div className={style.bigFont}>DAY {daysList[currentDayIndex]}</div>
          <Next className={style.nextButton} onClick={goToNextDay} />
        </div>
      )}
    </>
  );
};
