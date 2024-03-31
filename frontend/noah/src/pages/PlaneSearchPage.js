import { useState } from "react";
import Header from "../components/common/Header";
import { ReactComponent as SmallPlane } from "./../assets/Icon/SmallPlane.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import styles from "./PlaneSearchPage.module.css";

// const wholeStyle = {
//   display: "flex",
//   flexDirection: "column",
//   border: "1px solid grey",
//   margin: "10px",
//   borderRadius: "5px",
//   padding: "10px",
// };

// const upperStyle = {
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-between",
//   margin: "10px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const middleStyle = {
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-evenly",
//   margin: "0px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "4.0vw",
//   lineHeight: "200%",
// };

// const lowerStyle = {
//   display: "flex",
//   flexDirection: "column",
//   textAlign: "center",
//   color: "blue",
//   marginTop: "10px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "4.4vw",
//   lineHeight: "200%",
// };

// const wholeBox = {
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-between",
//   margin: "20px",
//   border: "1px solid grey",
//   textAlign: "center",
//   alignItems: "center",
//   borderRadius: "5px",
// };
// const boxLeft = {
//   display: "flex",
//   flexDirection: "row",
//   margin: "15px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const inputStyle = {
//   border: "none",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "4.0vw",
//   lineHeight: "200%",
// };
// const boxRight = {
//   marginRight: "10px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.8vw",
//   lineHeight: "200%",
// };

// const lowerButtonStyle = {
//   display: "flex",
//   justifyContent: "center",
//   marginBottom: "20px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const buttonStyle = {
//   width: "90vw",
//   height: "7vh",
//   alignItems: "center",
//   borderRadius: "10px",
//   border: "none",
//   backgroundColor: "black",
//   color: "white",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "550",
//   fontSize: "4.8vw",
//   lineHeight: "200%",
// };

// const placeOrTicket = {
//   height: "8vh",
//   // backgroundColor: "red",
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-evenly",
//   marginLeft: "20px",
//   marginRight: "20px",
//   alignItems: "center",
// };

// const resultList = {
//   // margin: "10px",
//   marginLeft: "20px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "550",
//   fontSize: "3.8vw",
//   lineHeight: "200%",
// };

// const noResultStyle = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "550",
//   fontSize: "3.8vw",
//   lineHeight: "200%",
//   margin: "10px",
//   textAlign: "center",
//   border: "1px solid grey",
//   padding: "10px"
// };

export default function PlaneSearchPage() {
  // const [planeList, setPlaneList] = useState([
  //   {
  //     a_airport: "인천공항",
  //     d_airport: "나리타공항",
  //     a_time: "07:57",
  //     d_time: "09:17",
  //     price: 248000,
  //     airLine: "싸피항공",
  //     code: "7C115",
  //   },
  //   {
  //     a_airport: "인천공항",
  //     d_airport: "나리타공항",
  //     a_time: "07:57",
  //     d_time: "09:17",
  //     price: 248000,
  //     airLine: "싸피항공",
  //     code: "7C115",
  //   },
  //   {
  //     a_airport: "인천공항",
  //     d_airport: "나리타공항",
  //     a_time: "07:57",
  //     d_time: "09:17",
  //     price: 248000,
  //     airLine: "싸피항공",
  //     code: "7C115",
  //   },
  //   {
  //     a_airport: "인천공항",
  //     d_airport: "나리타공항",
  //     a_time: "07:57",
  //     d_time: "09:17",
  //     price: 248000,
  //     airLine: "싸피항공",
  //     code: "7C115",
  //   },
  //   {
  //     a_airport: "인천공항",
  //     d_airport: "나리타공항",
  //     a_time: "07:57",
  //     d_time: "09:17",
  //     price: 248000,
  //     airLine: "싸피항공",
  //     code: "7C115",
  //   },
  // ]);
  const [planeList, setPlaneList] = useState([]);

  const [arrivalAirPort, setArrivalAirPort] = useState();
  const [departureAirPort, setDepartureAirPort] = useState();
  const [boarderDate, setBoarderDate] = useState();

  const handleArrival = (e) => {
    const value = e.target.value;
    setArrivalAirPort(value);
  };
  const handleDeparture = (e) => {
    const value = e.target.value;
    setDepartureAirPort(value);
  };
  const handleDate = (e) => {
    const value = e.target.value;
    setBoarderDate(value);
  };

  const postPlaneDate = () => {
    const postDate = { departureAirPort, arrivalAirPort, boarderDate };
    setDepartureAirPort("");
    setArrivalAirPort("");
    setBoarderDate("");
  };

  const selectPlane = () => {};

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
              placeholder="탑승 날짜"
              value={boarderDate}
              onChange={handleDate}
            ></input>
          </div>
          <div className={styles.boxRight}>탑승날짜</div>
        </div>
      </div>
      <div className={styles.lowerButtonStyle}>
        <button className={styles.buttonStyle}>검색</button>
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
                  <div>{plane.a_time} 출발</div>
                </div>
                <div>
                  <SmallPlane />
                </div>
                <div>
                  <div>{plane.d_airport}</div>
                  <div>{plane.d_time} 도착</div>
                </div>
              </div>
              <div className={styles.lowerStyle} onClick={selectPlane}>
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
              <input className={styles.inputStyle} placeholder="2023/04/01"></input>
            </div>
            <div className={styles.boxRight}>날짜</div>
          </div>
        </div>
      )}
    </>
  );
}
