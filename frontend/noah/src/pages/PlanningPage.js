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

import { getTicketList, deleteTicket } from "../api/ticket/Ticket";

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
  const [currentSelectedDate, setCurrentSelectedDate] = useState("");

  const [plan, setPlan] = useState({
    id: 1,
    title: "오사카 일본",
    start_date: "2024-04-02",
    end_date: "2024-04-05",
    travel_start: false,
    country: "일본",
    travel_id: 1,
  });

  const [plane, setPlane] = useState([]);

  const [detailPlans, setDetailPlans] = useState([]);

  const filteredDetailPlans = detailPlans.filter(
    (detailPlan) => detailPlan.day === currentDay
  );

  const handleAddPlanClick = () => {
    navigate("planningTest", { state: { planId: plan.id, day: currentDay, date: currentSelectedDate } });
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

  function removeTicketDuplicates(tickets) {
    const uniqueTickets = [];
    const uniqueKeySet = new Set();

    tickets.forEach((ticket) => {
      // 중복 판단을 위한 고유 키 생성 (예: 출발-도착 시간 조합)
      const uniqueKey = `${ticket.departure}-${ticket.arrival}`;
      if (!uniqueKeySet.has(uniqueKey)) {
        uniqueTickets.push(ticket);
        uniqueKeySet.add(uniqueKey);
      }
    });

    return uniqueTickets;
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

  const loadTicketList = async () => {
    try {
      const response = await getTicketList(travelId);
      if (response.status === "SUCCESS" && Array.isArray(response.data)) {
        const uniqueTickets = removeTicketDuplicates(response.data); // 중복 제거 함수 호출
        setPlane(uniqueTickets); // 중복이 제거된 데이터로 상태 업데이트
      } else {
        console.error("plane is not an array or status is not SUCCESS");
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleDayChange = (newDay) => {
    setCurrentDay(newDay.day); // 현재 날짜 인덱스 업데이트
    setCurrentSelectedDate(newDay.date); // 현재 선택된 날짜를 상태로 저장
  };

  // 상태 업데이트 후 확인을 위한 useEffect
  useEffect(() => {
    console.log(JSON.stringify(plane), "plane updated"); // 상태가 업데이트된 후의 값을 로깅
  }, [plane]);

  useEffect(() => {
    setCurrentSelectedDate(plan.start_date);
    loadDetailPlan();
    loadTicketList();
  }, [plan.start_date]);

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

  const handleDeleteTicket = async (ticketId) => {
    try {
      // deleteDetailPlan 함수가 성공적으로 수행되었다고 가정
      console.log(ticketId);
      await deleteTicket(ticketId);
      // 삭제 후 detailPlans 상태 업데이트
      setPlane((prevTickets) =>
        prevTickets.filter((flight) => flight.id !== ticketId)
      );
    } catch (error) {
      console.error("삭제 작업 중 오류가 발생했습니다.", error);
    }
  };

  const filteredPlanes = plane.filter((flight) => {
    // 출발 또는 도착 날짜를 현재 선택된 날짜와 비교합니다.
    // 예시로 출발 날짜(departure)를 사용했습니다. 필요에 따라 도착 날짜(arrival)로 변경 가능합니다.
    const flightDate = flight.departure.split("T")[0]; // 'YYYY-MM-DD' 형식으로 날짜 추출
    return flightDate === currentSelectedDate;
  });

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
          {filteredPlanes.map((flight) => (
            <div className={style.planeBoxStyle}>
              <div className={style.planeInfo}>
                <div className={style.middleFont}>{flight.a_airport}</div>

                <div className={style.smallFont}>
                  {getTimeFromString(flight.arrival)}
                </div>
              </div>
              <SmallPlane className={style.smallPlaneStyle} />
              <div className={style.planeInfo}>
                <div className={style.middleFont}>{flight.d_airport}</div>
                <div className={style.smallFont}>
                  {getTimeFromString(flight.departure)}
                </div>
              </div>
              <TrashCan
                className={style.trashCanButton}
                onClick={() => handleDeleteTicket(flight.id)}
              />
            </div>
          ))}
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
  const [currentDayIndex, setCurrentDayIndex] = useState(0);
  const [daysList, setDaysList] = useState([]);

  const calculateDays = () => {
    if (!initialStartDate || !initialEndDate) return;

    const startDate = new Date(initialStartDate);
    const endDate = new Date(initialEndDate);
    const oneDay = 24 * 60 * 60 * 1000;
    let current = new Date(startDate);
    const newDaysList = [];

    // 'current' 날짜가 'endDate' 이전 또는 같은 동안 반복합니다.
    while (current <= endDate) {
      newDaysList.push({
        day: newDaysList.length + 1,
        date: `${current.getFullYear()}-${String(
          current.getMonth() + 1
        ).padStart(2, "0")}-${String(current.getDate()).padStart(2, "0")}`,
      });
      // 'current' 날짜를 다음 날로 업데이트합니다.
      current = new Date(current.setDate(current.getDate() + 1));
    }

    setDaysList(newDaysList);
    setCurrentDayIndex(0);
    if (newDaysList.length > 0) {
      onDayChange(newDaysList[0]); // 첫 번째 day 객체 전달
    }
  };

  useEffect(() => {
    calculateDays();
  }, [initialStartDate, initialEndDate]);

  const goToPreviousDay = () => {
    setCurrentDayIndex((prev) => {
      const newIndex = prev > 0 ? prev - 1 : prev;
      onDayChange(daysList[newIndex]); // 여기에서 현재 선택된 날짜를 상위 컴포넌트로 전달
      return newIndex;
    });
  };

  const goToNextDay = () => {
    const newIndex =
      currentDayIndex < daysList.length - 1
        ? currentDayIndex + 1
        : currentDayIndex;
    setCurrentDayIndex(newIndex);
    onDayChange(daysList[newIndex]); // 현재 선택된 날짜를 상위 컴포넌트로 전달
  };

  return (
    <>
      {daysList.length > 0 && (
        <div className={style.midStyle}>
          <Next className={style.previousButton} onClick={goToPreviousDay} />
          <div>
            <div className={style.bigFont}>
              DAY {daysList[currentDayIndex].day}
            </div>
            <div className={style.smallFont}>
              {daysList[currentDayIndex].date}
            </div>
          </div>
          <Next className={style.nextButton} onClick={goToNextDay} />
        </div>
      )}
    </>
  );
};
