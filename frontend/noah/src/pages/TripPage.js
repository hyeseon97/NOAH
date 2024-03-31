import DoughnutChart from "../components/common/DoughnutChart";
import Header from "./../components/common/Header";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import styles from "./TripPage.module.css";

import { ReactComponent as Change } from "./../assets/Icon/Change.svg";
import { ReactComponent as QR } from "./../assets/Icon/QR.svg";
import { ReactComponent as Pig } from "./../assets/Icon/Pig.svg";
import { ReactComponent as Person } from "./../assets/Icon/Person.svg";
import { ReactComponent as Plan } from "./../assets/Icon/Plan.svg";
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

  const handleMenuClick = (menu) => {
    if (menu === "결제") {
      navigate("/trip/3/payment");
      return;
    }
    if (menu === "환전") {
      navigate("/trip/3/exchange");
      return;
    }
    if (menu === "소비관리") {
      navigate("/trip/3/spendingmanagement");
      return;
    }
    if (menu === "인원관리") {
      navigate("/trip/3/participantmanagement");
      return;
    }
  };

  return (
    <>
      <Header
        LeftIcon="Cancel"
        Title="여행이름"
        onClick={handleLeftIconClick}
      />
      <div style={{ marginTop: "6.67vw", cursor: "pointer" }}>
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
          {false && (
            <>
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  marginTop: "7.2vw",
                }}
              >
                <Plan style={{ width: "13.3vw", height: "13.3vw" }} />
                <div
                  className={styles.labelSmall}
                  style={{ marginTop: "4.44vw" }}
                >
                  구체적인 여행 계획을 세워보세요
                </div>
                <div className={styles.detailParagraph}>클릭하여 계획작성</div>
              </div>
            </>
          )}
          {true && (
            <>
              <div className={styles.infoContainer}>
                <div className={styles.Dday}>D-27</div>
                <div className={styles.day}>2024/03/15 ~ 2024/03/18</div>
                <div className={styles.bottom}>
                  <div className={styles.country}>일본</div>
                  <div className={styles.clickMessage}>여행계획 보러가기</div>
                </div>
              </div>
            </>
          )}
        </div>
      </div>
      <div
        style={{ width: "100vw", display: "flex", justifyContent: "center" }}
      >
        <div className={styles.menuContainer}>
          <div className={styles.menu} onClick={() => handleMenuClick("결제")}>
            <div>
              <QR className={styles.icon} />
            </div>
            <div>결제</div>
          </div>

          <div className={styles.menu} onClick={() => handleMenuClick("환전")}>
            <div>
              <Change className={styles.icon} />
            </div>
            <div>환전</div>
          </div>
          <div
            className={styles.menu}
            onClick={() => handleMenuClick("소비관리")}
          >
            <div>
              <Pig className={styles.icon} />
            </div>
            <div>소비관리</div>
          </div>
          <div
            className={styles.menu}
            onClick={() => handleMenuClick("인원관리")}
          >
            <div>
              <Person className={styles.icon} />
            </div>
            <div>인원관리</div>
          </div>
        </div>
      </div>
    </>
  );
}
