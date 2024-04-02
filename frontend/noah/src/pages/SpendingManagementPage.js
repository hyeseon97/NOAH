import Header from "../components/common/Header";
import styles from "./SpendingManagementPage.module.css";
import { ReactComponent as CancelGrey } from "./../assets/Icon/CancelGrey.svg";
import AOS from "aos";
import ClipLoader from "react-spinners/ClipLoader";
import "aos/dist/aos.css";
import { useState, useEffect } from "react";
import Spending from "../components/SpendingManagement/Spending";
import DoughnutChartSmall from "../components/SpendingManagement/DoughnutChartSmall";
import SumBox from "../components/SpendingManagement/SumBox";
import SpendingHeader from "../components/SpendingManagement/SpedingHeader";
import { getAllTrade } from "../api/trade/Trade";
import { useParams } from "react-router-dom";

export default function SpendingManagemnetPage() {
  const { travelId } = useParams();
  const [isFilter, setIsFilter] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [currentViewInFilter, setCurrentViewInFilter] =
    useState("participants");
  const [allSpendingHistory, setAllSpendingHistory] = useState([]);
  const [groupedByDate, setGroupedByDate] = useState([]);

  useEffect(() => {
    AOS.init({
      duration: 200,
    });
  });

  const handleRightIconClick = () => {
    setIsFilter((prev) => !prev);
  };

  function groupTransactionsByDate(transactions) {
    const groupedByDate = transactions.reduce((acc, cur) => {
      // 현재 항목의 날짜를 키로 사용합니다.
      const date = cur.date;

      // 해당 날짜의 그룹이 없으면 새로 만들어줍니다.
      if (!acc[date]) {
        acc[date] = {
          transactions: [], // 해당 날짜의 모든 트랜잭션을 저장합니다.
          totalCost: 0, // 해당 날짜의 amount 합산값을 저장합니다. 여기서는 type 2인 경우만 더합니다.
        };
      }

      // 현재 트랜잭션을 해당 날짜의 배열에 추가합니다.
      acc[date].transactions.push(cur);

      // 현재 트랜잭션의 type이 2(출금)인 경우만 amount를 합산값에 추가합니다.
      if (cur.type === 2) {
        acc[date].totalCost += cur.cost;
      }

      return acc;
    }, {});

    return groupedByDate;
  }

  useEffect(() => {
    (async () => {
      try {
        const res = await getAllTrade(travelId);

        setAllSpendingHistory(res.data);
        setGroupedByDate(groupTransactionsByDate(res.data));
      } catch (e) {
      } finally {
        setTimeout(() => setIsLoading(false), 500);
        console.log(allSpendingHistory);
        console.log(groupedByDate);
      }
    })();
  }, []);

  return (
    <>
      <Header
        LeftIcon="Arrow"
        Title="소비관리"
        RightIcon="Filter"
        onRightIconClick={handleRightIconClick}
      />
      {!isFilter && !isLoading && (
        <>
          {Object.entries(groupedByDate).map(([date, data]) => (
            <div key={date}>
              <SpendingHeader date={date} totalCost={data.totalCost} />
              {data.transactions.map((transaction) => (
                <Spending key={transaction.tradeId} transaction={transaction} />
              ))}
            </div>
          ))}
          {groupedByDate.length === 0 && (
            <>
              <div className={styles.nonHistory}>조회된 내역이 없습니다.</div>
            </>
          )}
        </>
      )}
      {!isFilter && isLoading && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "80vh",
            }}
          >
            <ClipLoader />
          </div>
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
