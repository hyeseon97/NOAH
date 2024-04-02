import DoughnutChart from "../components/common/DoughnutChart";
import Header from "./../components/common/Header";
import { useNavigate, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import styles from "./TripPage.module.css";

import { ReactComponent as Change } from "./../assets/Icon/Change.svg";
import { ReactComponent as QR } from "./../assets/Icon/QR.svg";
import { ReactComponent as Pig } from "./../assets/Icon/Pig.svg";
import { ReactComponent as Person } from "./../assets/Icon/Person.svg";
import { ReactComponent as Plan } from "./../assets/Icon/Plan.svg";
import { getTravelInfo } from "../api/travel/Travel";
export default function TripPage() {
  const navigate = useNavigate();
  const { travelId } = useParams();
  const [percent, setPercent] = useState(0);
  const [travelInfo, setTravelInfo] = useState({
    accountAmount: null,
    accountId: null,
    country: null,
    endDate: null,
    groupAccountId: null,
    planId: null,
    startDate: null,
    targetAmount: 0,
    title: "",
    travelId: null,
  });

  useEffect(() => {
    (async () => {
      try {
        const res = await getTravelInfo(travelId);
        console.log(res.data);
        if (res.status === "SUCCESS") {
          setTravelInfo(res.data);
        } else {
          throw new Error(res.status);
        }
      } catch (e) {
        console.log(e);
      }
    })();
  }, []);

  useEffect(() => {
    if (travelInfo.accountAmount === 0) {
      setPercent(0);
      return;
    } else if (travelInfo.targetAmount === 0) {
      setPercent(0);
      return;
    }

    setPercent((travelInfo.depositTotal / travelInfo.targetAmount) * 100);
  }, [travelInfo.depositTotal, travelInfo.targetAmount]);

  const handleLeftIconClick = () => {
    navigate("/home");
  };

  const handleDetailClick = () => {
    navigate(`/trip/${travelId}/goal`); // 3은 travelId
  };

  const handlePlanningClick = () => {
    navigate(`/trip/${travelId}/planning`); // 3은 travelId
  };

  const handleCreatePlanningClick = () => {
    navigate(`/trip/${travelId}/planningcreate`);
  };

  const handleMenuClick = (menu) => {
    if (menu === "결제") {
      navigate(`/trip/${travelId}/payment`);
      return;
    }
    if (menu === "환전") {
      navigate(`/trip/${travelId}/exchange`);
      return;
    }
    if (menu === "소비관리") {
      navigate(`/trip/${travelId}/spendingmanagement`);
      return;
    }
    if (menu === "인원관리") {
      navigate(`/trip/${travelId}/participantmanagement`);
      return;
    }
  };

  const calculateLeftDay = () => {
    const start = new Date(travelInfo.startDate);
    const now = new Date();
    const diff = start - now;
    const diffDays = Math.ceil(diff / (1000 * 60 * 60 * 24));

    return diffDays;
  };

  return (
    <>
      <Header
        LeftIcon="Cancel"
        Title={travelInfo.title}
        onClick={handleLeftIconClick}
      />
      <div
        style={{ marginTop: "6.67vw", cursor: "pointer" }}
        onClick={handleDetailClick}
      >
        <DoughnutChart percent={percent} />
        <div
          className={styles.detailParagraph}
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
        <div className={styles.planBorder}>
          {travelInfo.country === null && (
            <div onClick={handleCreatePlanningClick}>
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
                <div
                  className={styles.detailParagraph}
                  onClick={handleCreatePlanningClick}
                >
                  클릭하여 계획작성
                </div>
              </div>
            </div>
          )}
          {travelInfo.country != null && (
            <>
              <div
                className={styles.infoContainer}
                onClick={handlePlanningClick}
              >
                <div className={styles.Dday}>D-{calculateLeftDay()}</div>
                <div className={styles.day}>
                  {travelInfo.startDate.split("T")[0]} ~{" "}
                  {travelInfo.endDate.split("T")[0]}
                </div>
                <div className={styles.bottom}>
                  <div className={styles.country}>{travelInfo.country}</div>
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
