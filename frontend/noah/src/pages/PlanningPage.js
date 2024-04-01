import React, { useEffect, useState } from "react";
import Header from "./../components/common/Header";
import { ReactComponent as Next } from "./../assets/Icon/Next.svg";
import { ReactComponent as SmallPlane } from "./../assets/Icon/SmallPlane.svg";
import { ReactComponent as TrashCan } from "./../assets/Icon/TrashCan.svg";
import { ReactComponent as Plus } from "./../assets/Icon/Plus.svg";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
// import DayCalculate from "../components/trip/DayCalculate";

import style from "./PlanningPage.module.css";

import {
  getDetailPlan,
  getDetailPlanList,
  updateDetailPlan,
  deleteDetailPlan,
  createDetailPlan,
} from "../api/detailplan/DetailPlan";
import { useNavigate } from "react-router-dom";

// const imgStyle = {
//   width: "90px",
//   height: "90px",
//   margin: "20px"
// }

// const boxStyle = {
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "center",
//   alignItems: "beetween",
//   border: "gray",
//   radius: "5px"
// }

// const headStyle = {
//   height: "50px",
//   margin: "10px",
//   marginTop: "15px",
//   marginBottom: "15px",
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-between",
//   alignItems: "beetween",
//   // backgroundColor: "coral",
// };

// const bigFont = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "5.2vw",
//   lineHeight: "140%",
// };

// const middleFont = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "4.6vw",
//   lineHeight: "140%",
// };

// const smallFont = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "500",
//   fontSize: "3.9vw",
//   lineHeight: "140%",
// };

// const smallBoldFont = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.9vw",
//   lineHeight: "140%",
// };

// const midStyle = {
//   height: "50px",
//   margin: "10px",
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-between",
//   alignItems: "center",
//   // fontFamily: "Pretendard",
//   // fontStyle: "normal",
//   // backgroundColor: "blue",
// };

// const boxStyle = {
//   display: "flex",
//   alignItems: "center",
//   border: "1px solid gray",
//   padding: "0px",
//   margin: "10px",
//   borderRadius: "10px",
//   // justifyContent: "space-between"
// };

// const planeBoxStyle = {
//   display: "flex",
//   alignItems: "center",
//   border: "1px solid gray",
//   padding: "0px",
//   margin: "10px",
//   borderRadius: "10px",
//   justifyContent: "space-between",
//   margin: "10px",
//   padding: "25px",
//   height: "100px",
// };

// // 새로운 계획 추가 버튼 스타일
// const addDetailPlanStyle = {
//   display: "flex",
//   justifyContent: "center", // 수평 중앙 정렬
//   alignItems: "center", // 추가: 수직 중앙 정렬
//   flexDirection: "column",
//   padding: "23px",
//   margin: "10px",
//   border: "1px solid gray",
//   cursor: "pointer",
//   borderRadius: "10px",
//   marginTop: "0px",
//   // backgroundColor: "#f0f0f0",
//   // marginBottom: "10px",
//   // height: "50px",
// };

// const placeInfoStyle = {
//   marginLeft: "15px",
//   width: "170px",
//   // backgroundColor: "RED",
// };

// // 이미지 스타일에 border-radius 추가하여 모서리를 둥글게
// const imgStyle = {
//   width: "90px",
//   height: "90px",
//   margin: "10px",
//   borderRadius: "5px",
// };

// const privousButton = {
//   transform: "rotate(90deg)",
// };

// const nextButton = {
//   transform: "rotate(270deg)",
// };

// const editButton = {
//   marginTop: "23px",
//   width: "25px",
//   height: "25px",
// };

// const trashCanButton = {
//   width: "20px",
//   height: "20px",
//   marginTop: "50px",
//   marginLeft: "10px",
// };

// const smallPlaneStyle = {
//   width: "30px",
//   height: "30px",
//   // backgroundColor: "green",
// };

// const planeInfo = {
//   display: "flex",
//   flexDirection: "column",
//   alignItems: "center",
// };

const getTimeFromString = (dateTimeString) => {
  const date = new Date(dateTimeString);
  // 옵션을 설정하여 시간과 분만 표시하도록 합니다.
  const timeOptions = { hour: "2-digit", minute: "2-digit" };
  return date.toLocaleTimeString("ko-KR", timeOptions);
};

export default function PlanningPage() {
  const navigate = useNavigate();

  const [currentDay, setCurrentDay] = useState(1);
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

  const [detailPlans, setDetailPlans] = useState([
    {
      place: "이토모리 신사",
      day: 1,
      city: "교토, 일본",
      rate: 4.5,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
    {
      place: "이토모리 호텔",
      day: 2,
      city: "교토, 일본",
      rate: 4.2,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
    {
      place: "이토모리 음식점",
      day: 3,
      city: "교토, 일본",
      rate: 4.1,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
    {
      place: "독도는 우리땅",
      day: 4,
      city: "교토, 일본",
      rate: 5.0,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
  ]);

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

  const handleNavigation = () => {
    // 현재 URL을 가져옵니다.
    const currentUrl = window.location.href;
  
    // 기존 URL에서 '/planning' 부분을 'planningTest'로 변경합니다.
    const newUrl = currentUrl.replace('/planning', '/planningTest');
  
    // 새로운 URL로 이동합니다.
    window.location.href = newUrl;
  };
  
  const handleAddPlanClick = () => {
    // 'add-plan' 경로로 이동하며 planId와 현재 선택된 day를 상태로 전달
    navigate("planningTest", { state: { planId: plan.id, day: currentDay } });
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
            {filteredDetailPlans.map((detailPlan, index) => (
              <div key={index} className={style.boxStyle}>
                <img
                  src={detailPlan.url}
                  alt="Detail Plan"
                  className={style.imgStyle}
                />
                <div className={style.placeInfoStyle}>
                  <div className={style.smallBoldFont}>{detailPlan.place}</div>
                  <div className={style.smallFont}>{detailPlan.city}</div>
                  <div className={style.smallFont}>
                    사용자 평점 {detailPlan.rate}
                  </div>
                </div>
                <TrashCan className={style.trashCanButton} />
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
