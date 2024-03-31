import Header from "../components/common/Header";
import styles from "./SpendingManagementPage.module.css";
import { ReactComponent as CancelGrey } from "./../assets/Icon/CancelGrey.svg";
import AOS from "aos";
import "aos/dist/aos.css";
import { useState, useEffect } from "react";
import Spending from "../components/SpendingManagement/Spending";
import DoughnutChartSmall from "../components/SpendingManagement/DoughnutChartSmall";
import SumBox from "../components/SpendingManagement/SumBox";

export default function SpendingManagemnetPage() {
  const [isFilter, setIsFilter] = useState(false);
  const [currentViewInFilter, setCurrentViewInFilter] =
    useState("participants");

  useEffect(() => {
    AOS.init({
      duration: 200,
    });
  });

  const handleRightIconClick = () => {
    setIsFilter((prev) => !prev);
  };
  return (
    <>
      <Header
        LeftIcon="Arrow"
        Title="소비관리"
        RightIcon="Filter"
        onRightIconClick={handleRightIconClick}
      />
      {!isFilter && (
        <>
          <div className={styles.tradeHeader}>
            <div className={styles.labelMedium}>2024.03.19</div>
            <div className={styles.headerRight}>
              <div className={styles.labelSmallGray}>사용금액</div>
              <div className={styles.labelSmall}>395,374원</div>
            </div>
          </div>
          <div className={styles.line}></div>

          <Spending />
          <Spending />
          <Spending />
        </>
      )}
      {isFilter && (
        <>
          <div className={styles.container}>
            <div className={styles.topContainer}>
              <div className={styles.graphContainer}>
                <div className={styles.labelMedium}>입금</div>
                <DoughnutChartSmall percent={85} />
                <div
                  className={styles.labelSmall}
                  style={{ marginTop: "1.11vw" }}
                >
                  15,000,000원
                </div>
              </div>
              <div className={styles.graphContainer}>
                <div className={styles.labelMedium}>지출</div>
                <DoughnutChartSmall percent={70} isRed={true} />
                <div
                  className={styles.labelSmall}
                  style={{ marginTop: "1.11vw" }}
                >
                  8,500,000원
                </div>
              </div>
              <div className={styles.sumContainer}>
                <div className={styles.labelSmall}>합계</div>
                <div className={styles.labelSmall}>6,250,000원</div>
                <div
                  className={styles.labelMedium}
                  style={{
                    marginTop: "1vw",
                    color: "blue",
                    cursor: "pointer",
                  }}
                >
                  내역 상세
                </div>
              </div>
            </div>
            <div className={`${styles.menuSelect} ${styles.labelMedium}`}>
              <div
                style={{
                  cursor: "pointer",
                  color:
                    currentViewInFilter === "participants"
                      ? "black"
                      : "#898989",
                  textDecoration:
                    currentViewInFilter === "participants"
                      ? "underline"
                      : "none",
                }}
                onClick={() => setCurrentViewInFilter("participants")}
              >
                참가자
              </div>
              <div
                style={{
                  cursor: "pointer",
                  color:
                    currentViewInFilter === "expences" ? "black" : "#898989",
                  textDecoration:
                    currentViewInFilter === "expences" ? "underline" : "none",
                }}
                onClick={() => setCurrentViewInFilter("expences")}
              >
                지출항목
              </div>
            </div>
            <div className={styles.line}></div>
            <SumBox title="전체" sum="8750000" />
            <div className={styles.line}></div>
            <SumBox title="강준규" sum="1750000" isClicked={true} />
            <SumBox title="박혜선" sum="2750000" />
            <SumBox title="이우진" sum="3750000" isClicked={true} />
            <SumBox title="전현철" sum="4750000" />
          </div>
        </>
      )}
    </>
  );
}
