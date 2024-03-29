import React, { useEffect, useState } from "react";
import Header from "./../components/common/Header";
import { ReactComponent as Next } from "./../assets/Icon/Next.svg";
import { ReactComponent as SmallPlane } from "./../assets/Icon/SmallPlane.svg";
import { ReactComponent as TrashCan } from "./../assets/Icon/TrashCan.svg";
import { ReactComponent as Plus } from "./../assets/Icon/Plus.svg";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";

import {
  getDetailPlan,
  getDetailPlanList,
  updateDetailPlan,
  deleteDetailPlan,
  createDetailPlan,
} from "../api/detailplan/DetailPlan";

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

const headStyle = {
  height: "50px",
  margin: "10px",
  display: "flex",
  flexDirection: "row",
  justifyContent: "space-between",
  alignItems: "beetween",
  // backgroundColor: "coral",
};

const midStyle = {
  height: "50px",
  margin: "10px",
  display: "flex",
  flexDirection: "row",
  justifyContent: "space-between",
  alignItems: "center",
  // fontFamily: "Pretendard",
  // fontStyle: "normal",
  // backgroundColor: "blue",
};

const boxStyle = {
  display: "flex",
  alignItems: "center",
  border: "1px solid gray",
  padding: "0px",
  margin: "10px",
  borderRadius: "10px",
  // justifyContent: "space-between"
};

const planeBoxStyle = {
  display: "flex",
  alignItems: "center",
  border: "1px solid gray",
  padding: "0px",
  margin: "10px",
  borderRadius: "10px",
  justifyContent: "space-between",
  margin: "10px",
  padding: "25px",
  height: "100px",
};

// 새로운 계획 추가 버튼 스타일
const addDetailPlanStyle = {
  display: "flex",
  justifyContent: "center", // 수평 중앙 정렬
  alignItems: "center", // 추가: 수직 중앙 정렬
  flexDirection: "column",
  padding: "23px",
  margin: "10px",
  border: "1px solid gray",
  cursor: "pointer",
  borderRadius: "10px",
  marginTop: "0px",
  // backgroundColor: "#f0f0f0",
  // marginBottom: "10px",
  // height: "50px",
};

const placeInfoStyle = {
  marginLeft: "15px",
  width: "170px",
  // backgroundColor: "RED",
};

// 이미지 스타일에 border-radius 추가하여 모서리를 둥글게
const imgStyle = {
  width: "90px",
  height: "90px",
  margin: "10px",
  borderRadius: "5px",
};

const privousButton = {
  transform: "rotate(90deg)",
};

const nextButton = {
  transform: "rotate(270deg)",
};

const editButton = {
  marginTop: "23px",
  width: "25px",
  height: "25px",
};

const trashCanButton = {
  width: "20px",
  height: "20px",
  marginTop: "50px",
  marginLeft: "10px",
};

const smallPlaneStyle = {
  width: "30px",
  height: "30px",
  // backgroundColor: "green",
};

const planeInfo = {
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
};

const getTimeFromString = (dateTimeString) => {
  const date = new Date(dateTimeString);
  // 옵션을 설정하여 시간과 분만 표시하도록 합니다.
  const timeOptions = { hour: "2-digit", minute: "2-digit" };
  return date.toLocaleTimeString("ko-KR", timeOptions);
};

export default function PlanningPage() {
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

  const [day, setDay] = useState({
    detailday: 24,
    detailweekday: "일요일",
    sqday: 2,
  });

  const [detailPlans, setDetailPlans] = useState([
    {
      place: "이토모리 신사",
      city: "교토, 일본",
      rate: 4.5,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
    {
      place: "이토모리 호텔",
      city: "교토, 일본",
      rate: 4.2,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
    {
      place: "이토모리 음식점",
      city: "교토, 일본",
      rate: 4.1,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
    {
      place: "독도는 우리땅",
      city: "교토, 일본",
      rate: 5.0,
      url: "https://a.cdn-hotels.com/gdcs/production147/d1285/0745ceba-e251-44dd-900d-758bd7078d8a.jpg?impolicy=fcrop&w=800&h=533&q=medium",
    },
  ]);

  return (
    <>
      <Header LeftIcon="Arrow" Title="계획" />
      <div style={headStyle}>
        <div>
          <div>{plan.title}</div>
          <div>
            {plan.start_date} ~ {plan.end_date}
          </div>
        </div>
        <Edit style={editButton} />
      </div>
      <div style={midStyle}>
        <Next style={privousButton} />
        <div>
          <div>
            {day.detailday} {day.detailweekday}
          </div>
          <div>DAY {day.sqday}</div>
        </div>
        <Next style={nextButton} />
      </div>
      <div>
        <div>
          <div style={planeBoxStyle}>
            <div style={planeInfo}>
              <div>{plane.a_airport}</div>

              <div>{getTimeFromString(plane.arrival)}</div>
            </div>
            <SmallPlane style={smallPlaneStyle} />
            <div style={planeInfo}>
              <div>{plane.d_airport}</div>
              <div>{getTimeFromString(plane.departure)}</div>
            </div>
          </div>
          {detailPlans.map((detailPlan, index) => (
            <div key={index} style={boxStyle}>
              <img src={detailPlan.url} style={imgStyle} />
              <div style={placeInfoStyle}>
                <p>{detailPlan.place}</p>
                <p>{detailPlan.city}</p>
                <p>{detailPlan.rate}</p>
              </div>
              <TrashCan style={trashCanButton} />
            </div>
          ))}
        </div>
        <div style={addDetailPlanStyle}>
          <Plus />
          <div>새로운 계획 추가</div>
        </div>
      </div>
    </>
  );
}
