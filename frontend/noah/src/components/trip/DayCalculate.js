

import React, { useEffect, useState } from "react";

export default function DayCalculate() {
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [currentDayIndex, setCurrentDayIndex] = useState(0);
    const [daysList, setDaysList] = useState([]);
  
    const calculateDays = () => {
      if (!startDate || !endDate) return;
  
      const start = new Date(startDate);
      const end = new Date(endDate);
      const oneDay = 24 * 60 * 60 * 1000;
      const differenceInTime = end - start;
      const differenceInDays = Math.round(differenceInTime / oneDay);
  
      const newDaysList = [];
      for (let i = 0; i <= differenceInDays; i++) {
        newDaysList.push(`Day ${i + 1}`);
      }
  
      setDaysList(newDaysList);
      setCurrentDayIndex(0); // 인덱스를 0으로 초기화합니다.
    };
  
    // 이전 Day로 이동합니다.
    const goToPreviousDay = () => {
      setCurrentDayIndex((prev) => (prev > 0 ? prev - 1 : 0));
    };
  
    // 다음 Day로 이동합니다.
    const goToNextDay = () => {
      setCurrentDayIndex((prev) =>
        prev < daysList.length - 1 ? prev + 1 : prev
      );
    };
  
    // useEffect를 사용하여 startDate와 endDate가 변경될 때 calculateDays를 호출합니다.
    useEffect(() => {
      calculateDays();
    }, [startDate, endDate]);
  
    return (
      <>
        {daysList.length > 0 && (
          <div className="day-navigation">
            <button onClick={goToPreviousDay}>&lt;</button>
            <span> {daysList[currentDayIndex]} 정보 </span>
            <button onClick={goToNextDay}>&gt;</button>
          </div>
        )}
      </>
    );
  }
  