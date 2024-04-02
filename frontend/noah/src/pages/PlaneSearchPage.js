import { useState } from "react";
import Header from "../components/common/Header";
import { ReactComponent as SmallPlane } from "./../assets/Icon/SmallPlane.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import styles from "./PlaneSearchPage.module.css";

import { getFlightInfo } from "../api/flight/flight";
import { createTicket } from "../api/ticket/Ticket";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
// import { useNavigate } from "react-router-dom";

export default function PlaneSearchPage() {
  const [planeList, setPlaneList] = useState([]);
  // const [planeList, setPlaneList] = useState([]);
  const { travelId, planId } = useParams();
  const navigate = useNavigate();
  const [arrivalAirPort, setArrivalAirPort] = useState();
  const [departureAirPort, setDepartureAirPort] = useState();
  const [boarderDate, setBoarderDate] = useState();
  const location = useLocation();
  const { planId2, day, date } = location.state;

  const handleArrival = (e) => {
    const value = e.target.value;
    setArrivalAirPort(value);
  };
  const handleDeparture = (e) => {
    const value = e.target.value;
    setDepartureAirPort(value);
  };
  const handleDate = (e) => {
     const value = e.target.value
    setBoarderDate(value);
  };

  // const postPlaneDate = () => {
  //   const postDate = { departureAirPort, arrivalAirPort, boarderDate };
  //   setDepartureAirPort("");
  //   setArrivalAirPort("");
  //   setBoarderDate("");
  // };

  const handleCreateTicket = async (
    departureTime,
    arrivalTime,
    departureAirport,
    arrivalAirport
  ) => {
    try {
      await selectPlane(
        departureTime,
        arrivalTime,
        departureAirport,
        arrivalAirport
      );
      navigate(-1);
    } catch (error) {
      // 에러 처리
      console.error("계획 추가 중 오류 발생", error);
    }
  };

  const getPlaneList = () => {
    // console.log(arrivalAirPort, departureAirPort, boarderDate);
    getFlightInfo(arrivalAirPort, departureAirPort, boarderDate)
      .then((data) => {
        console.log(data); // API 호출 결과를 여기에서 사용할 수 있습니다.
        setPlaneList(data.data);
        console.log(planeList);
      })
      .catch((error) => {
        console.error(error); // 오류 처리
      });
  };

  const selectPlane = (
    departureTime,
    arrivalTime,
    departureAirport,
    arrivalAirport
  ) => {
    const departureDate = new Date(departureTime).toISOString();
    const arrivalDate = new Date(arrivalTime).toISOString();

    console.log(departureDate, arrivalDate, departureAirport, arrivalAirport + "testestestset" + " travelId" + travelId);

    const object = {
      departure: date,
      arrival: arrivalDate,
      d_airport: departureAirport,
      a_airport: arrivalAirport,
      d_gate: 0,
    };
    createTicket(travelId, object);
  };

  function extractTime(dateTime) {
    return dateTime.split(" ")[1];
  }

  return (
    <>
      {/* <Header LeftIcon="Arrow" Title="항공권 검색" />

      <div style={placeOrTicket}>
        <div>장소</div>
        <div>항공권</div>
      </div> */}

      <div>
        <div className={styles.wholeBox}>
          <div className={styles.boxLeft}>
            <Mark />
            <input
              className={styles.inputStyle}
              placeholder="출발 공항"
              value={arrivalAirPort}
              onChange={handleArrival}
            ></input>
          </div>
          <div className={styles.boxRight}>출발</div>
        </div>
        <div className={styles.wholeBox}>
          <div className={styles.boxLeft}>
            <Mark />
            <input
              className={styles.inputStyle}
              placeholder="도착 공항"
              value={departureAirPort}
              onChange={handleDeparture}
            ></input>
          </div>
          <div className={styles.boxRight}>도착</div>
        </div>
        <div className={styles.wholeBox}>
          <div className={styles.boxLeft}>
            <Mark />
            <input
              className={styles.inputStyle}
              placeholder={date}
              value={boarderDate}
              onChange={handleDate}
            ></input>
          </div>
          <div className={styles.boxRight}>탑승날짜</div>
        </div>
      </div>
      <div className={styles.lowerButtonStyle}>
        <button className={styles.buttonStyle} onClick={getPlaneList}>
          검색
        </button>
      </div>

      {planeList.length > 0 ? (
        <div className={styles.resultList}>
          검색결과
          {planeList.map((plane, index) => (
            <div key={index} className={styles.wholeStyle}>
              <div className={styles.upperStyle}>
                <div>
                  {plane.airLine} {plane.code}
                </div>
                <div>{plane.price}원</div>
              </div>
              <div className={styles.middleStyle}>
                <div>
                  <div>{plane.a_airport}</div>
                  <div>{extractTime(plane.a_time)} 출발</div>
                </div>
                <div>
                  <SmallPlane />
                </div>
                <div>
                  <div>{plane.d_airport}</div>
                  <div>{extractTime(plane.d_time)} 도착</div>
                </div>
              </div>
              <div
                className={styles.lowerStyle}
                onClick={() =>
                  handleCreateTicket(
                    plane.d_time,
                    plane.a_time,
                    plane.d_airport,
                    plane.a_airport
                  )
                }
              >
                항공권 선택
              </div>
            </div>
          ))}
        </div>
      ) : (
        <div className={styles.noResultStyle}>
          <p>검색 방법</p>
          <p>출발 : 나리타, 도착 : 인천, 날짜 : 2023/04/01</p>
          <div className={styles.wholeBox}>
            <div className={styles.boxLeft}>
              <Mark />
              <input className={styles.inputStyle} placeholder="나리타"></input>
            </div>
            <div className={styles.boxRight}>출발</div>
          </div>
          <div className={styles.wholeBox}>
            <div className={styles.boxLeft}>
              <Mark />
              <input className={styles.inputStyle} placeholder="인천"></input>
            </div>
            <div className={styles.boxRight}>도착</div>
          </div>
          <div className={styles.wholeBox}>
            <div className={styles.boxLeft}>
              <Mark />
              <input
                className={styles.inputStyle}
                placeholder="2023/04/01"
              ></input>
            </div>
            <div className={styles.boxRight}>날짜</div>
          </div>
        </div>
      )}
    </>
  );
}
