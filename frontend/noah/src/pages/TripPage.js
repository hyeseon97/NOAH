import DoughnutChart from "../components/common/DoughnutChart";
import Header from "./../components/common/Header";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import styles from "./TripPage.module.css";

import { ReactComponent as Change } from "./../assets/Icon/Change.svg";
import { ReactComponent as QR } from "./../assets/Icon/QR.svg";
import { ReactComponent as Pig } from "./../assets/Icon/Pig.svg";
import { ReactComponent as Person } from "./../assets/Icon/Person.svg";

export default function TripPage() {
  const navigate = useNavigate();
  const handleLeftIconClick = () => {
    navigate("/home");
  };
  const [percent, setPercent] = useState(85);
  const handleDetailClick = () => {
    navigate(`/trip/3/goal`); // 3은 tripId
  };

  const handlePlanningClick = () => {
    navigate(`/trip/3/planning`); // 3은 tripId
  };

  return (
    <>
      <Header
        LeftIcon="Cancel"
        Title="여행이름"
        onClick={handleLeftIconClick}
      />
      <div style={{ marginTop: "6.67vw" }}>
        <DoughnutChart percent={percent} />
        <div
          className={styles.detailParagraph}
          onClick={handleDetailClick}
          style={{
            zIndex: "100",
            position: "relative",
            left: "38vw",
            bottom: "17vw",
          }}
        >
          클릭하여 상세보기
        </div>
      </div>
      <div
        style={{ width: "100vw", display: "flex", justifyContent: "center" }}
      >
        <div className={styles.planBorder} onClick={handlePlanningClick}>
          <div>D-27</div>
          <div className={styles.planLine}></div>

          <div
            style={{
              background: "black",
              height: "0.277vw",
              width: `${20 * 1}vw`,
            }}
          ></div>
          <div className={styles.detailParagraph}>클릭하여 상세보기</div>
        </div>
      </div>
      <div className={styles.menuContainier}>
        <div>
          <div className={styles.icon}>
            <QR />
          </div>
          <div>결제</div>
        </div>

        <div>
          <div>
            <Change />
          </div>
          <div>환전</div>
        </div>
        <div>
          <div>
            <Pig />
          </div>
          <div>소비관리</div>
        </div>
        <div>
          <div>
            <Person />
          </div>
          <div>인원관리</div>
        </div>
      </div>
    </>
  );
}
