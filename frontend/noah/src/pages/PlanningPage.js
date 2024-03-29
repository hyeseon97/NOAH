import React, { useEffect, useState } from "react";
import Header from "./../components/common/Header";

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
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "beetween",
};
const midStyle = {
  height: "50px",
  margin: "20px",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
};

const boxStyle = {
  display: "flex",
  alignItems: "center", 
  border: "1px solid gray", 
  padding: "0px", 
  margin: "10px",
  borderRadius: "10px"
};

// 새로운 계획 추가 버튼 스타일
const addPlanStyle = {
  display: "flex",
  justifyContent: "center", // 수평 중앙 정렬
  alignItems: "center", // 추가: 수직 중앙 정렬
  padding: "0px",
  margin: "10px",
  border: "1px solid gray",
  cursor: "pointer",
  borderRadius: "10px",
  backgroundColor: "#f0f0f0",
  marginBottom: "10px",
  height: "50px",
};


const placeInfoStyle = {
  marginLeft: "15px", 
};

// 헤더와 중간 부분의 스타일
const sharedHeaderMidStyle = {
  padding: "10px 20px", 
  display: "flex",
  alignItems: "center", 
  justifyContent: "space-between", 
  
};

// 이미지 스타일에 border-radius 추가하여 모서리를 둥글게
const imgStyle = {
  width: "90px",
  height: "90px",
  margin: "10px",
  borderRadius: "5px", 
};




export default function PlanningPage() {
  const [plan, setPlan] = useState([]);
  const [title, setTitle] = useState("오사카 일본");
  const [date, setDate] = useState("2024/03/21 ~ 2024/03/24");

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
        <div>{title}</div>
        <div>{date}</div>
      </div>
      <div style={midStyle}>
        <div>
          {day.detailday} {day.detailweekday}
        </div>
        <div>DAY {day.sqday}</div>
      </div>
      <div>
        <div>
          {detailPlans.map((detailPlan, index) => (
            <div key={index} style={boxStyle}>
              <img src={detailPlan.url} style={imgStyle} />
              <div style={placeInfoStyle}>
                <p>{detailPlan.place}</p>
                <p>{detailPlan.city}</p>
                <p>{detailPlan.rate}</p>
              </div>
            </div>
          ))}
        </div>
        <div style={addPlanStyle}>
          <div>새로운 계획 추가</div>
        </div>
      </div>
    </>
  );
}
