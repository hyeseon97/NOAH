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
import { getGroupAccountMemberAndTotalDue } from "../api/groupaccount/GroupAccount";

export default function SpendingManagemnetPage() {
  const { travelId } = useParams();
  const [isFilter, setIsFilter] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [currentViewInFilter, setCurrentViewInFilter] =
    useState("participants");
  const [allSpendingHistory, setAllSpendingHistory] = useState([]);
  const [groupedByDate, setGroupedByDate] = useState([]);
  const [people, setPeople] = useState([]); // 여행에 속한 사람의 이름, id
  const [isDetailClick, setIsDetailClick] = useState(false);
  const [selectedSpendingHistory, setSelectedSpendingHistory] = useState([]);
  const [peopleHistory, setPeopleHistory] = useState([]);

  const [selectedNames, setSelectedNames] = useState([]);
  const [selectedConsumeTypes, setSelectedConsumeTypes] = useState([
    "공통",
    "식비",
    "숙박",
    "항공/교통",
    "환전",
    "쇼핑",
    "기타",
  ]);
  const [totalAmountByConsumeType, setTotalAmountByConsumeType] = useState({});
  const [allPeopleDeposit, setAllPeopleDeposit] = useState(0);
  const [allPeopleExpense, setAllPeopleExpense] = useState(0);
  const [depositPercent, setDepositPercent] = useState(0);
  const [expensePercent, setExpensePercent] = useState(0);
  const [depositSum, setDepositSum] = useState(0);
  const [expenseSum, setExpenseSum] = useState(0);

  function filterTransactions(selectedNames, selectedConsumeTypes) {
    return allSpendingHistory.filter(
      (transaction) =>
        selectedNames.includes(transaction.name) &&
        selectedConsumeTypes.includes(transaction.consumeType)
    );
  }

  function calculateTotalAmountByConsumeType(filteredTransactions) {
    // 초기 상태에서 모든 consumeType을 0으로 설정
    const initialAcc = {
      공통: 0,
      식비: 0,
      숙박: 0,
      "항공/교통": 0,
      환전: 0,
      쇼핑: 0,
      기타: 0,
    };

    return filteredTransactions?.reduce((acc, cur) => {
      // cur.consumeType이 null이면 현재 항목을 계산에 포함시키지 않음
      if (
        cur.consumeType === null ||
        !acc.hasOwnProperty(cur.consumeType) ||
        cur.type === 1
      ) {
        return acc;
      }

      // 유효한 consumeType에 대해서만 금액을 누적
      acc[cur.consumeType] += cur.cost; // 가정: transaction 객체에는 소비 금액을 나타내는 cost 속성이 있다.

      return acc;
    }, initialAcc); // 초기 상태를 reduce 함수에 전달
  }

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

    const sortedByDate = Object.entries(groupedByDate).sort((a, b) =>
      b[0].localeCompare(a[0])
    );

    return sortedByDate;
  }

  /* 초기 렌더링 */
  useEffect(() => {
    if (isDetailClick) {
      setSelectedSpendingHistory(
        filterTransactions(selectedNames, selectedConsumeTypes)
      );
      setGroupedByDate(groupTransactionsByDate(selectedSpendingHistory));

      setIsDetailClick(false);
      return;
    }

    (async () => {
      try {
        const res = await getAllTrade(travelId);
        const peopleRes = await getGroupAccountMemberAndTotalDue(travelId);
        const namesOnly = peopleRes.data.map((member) => member.memberName);
        const totalPaymentAmount = peopleRes.data.reduce(
          (total, member) => total + member.payment_amount,
          0
        );

        const totalCostOfType2 = res.data
          .filter((item) => item.type === 2) // type이 2인 요소들 필터링
          .reduce((acc, item) => acc + item.cost, 0); // cost 합 구하기
        setAllPeopleExpense(totalCostOfType2);
        setExpenseSum(totalCostOfType2);

        setAllPeopleDeposit(totalPaymentAmount);
        setDepositSum(totalPaymentAmount);
        setSelectedNames(namesOnly); // 처음엔 모든 이름을 선택
        const idAndNames = peopleRes.data.map((member) => ({
          id: member.member_id,
          name: member.memberName,
          amount: member.payment_amount,
        }));
        setPeople(idAndNames);
        setAllSpendingHistory(res.data);
        setSelectedSpendingHistory(res.data);
        setPeopleHistory(res.data);
        setGroupedByDate(groupTransactionsByDate(res.data)); // 처음 소비내역 보여주기 위함
      } catch (e) {
      } finally {
        setTimeout(() => setIsLoading(false), 500);
      }
    })();
  }, [isFilter]);

  useEffect(() => {
    setPeopleHistory(
      filterTransactions(selectedNames, [
        "공통",
        "식비",
        "숙박",
        "항공/교통",
        "환전",
        "쇼핑",
        "기타",
      ])
    );
    setTotalAmountByConsumeType(
      calculateTotalAmountByConsumeType(peopleHistory)
    );
  }, [selectedNames]);

  useEffect(() => {
    if (allPeopleDeposit !== 0) {
      setDepositPercent((depositSum / allPeopleDeposit) * 100);
    }

    if (allPeopleExpense !== 0) {
      setExpensePercent((expenseSum / allPeopleExpense) * 100);
    }
  }, [depositSum, expenseSum]);

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
          {groupedByDate.map(([date, data]) => (
            <div key={date}>
              <SpendingHeader date={date} totalCost={data.totalCost} />
              {data.transactions.map((transaction) => (
                <Spending
                  key={transaction.tradeId}
                  transaction={transaction}
                  people={people}
                  setAllSpendingHistory={setAllSpendingHistory}
                  travelId={travelId}
                />
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
                <DoughnutChartSmall percent={depositPercent} />
                <div
                  className={styles.labelSmall}
                  style={{ marginTop: "1.11vw" }}
                >
                  {new Intl.NumberFormat("ko-KR").format(depositSum)} 원
                </div>
              </div>
              <div className={styles.graphContainer}>
                <div className={styles.labelMedium}>지출</div>
                <DoughnutChartSmall percent={expensePercent} isRed={true} />
                <div
                  className={styles.labelSmall}
                  style={{ marginTop: "1.11vw" }}
                >
                  {new Intl.NumberFormat("ko-KR").format(expenseSum)} 원
                </div>
              </div>
              <div className={styles.sumContainer}>
                <div className={styles.labelSmall}>합계</div>
                <div className={styles.labelSmall}>
                  {" "}
                  {new Intl.NumberFormat("ko-KR").format(
                    depositSum - expenseSum
                  )}{" "}
                  원
                </div>
                <div
                  className={styles.labelMedium}
                  style={{
                    marginTop: "1vw",
                    color: "blue",
                    cursor: "pointer",
                  }}
                  onClick={() => {
                    setIsDetailClick(true);
                    setIsFilter(false);
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
                    currentViewInFilter === "expense" ? "black" : "#898989",
                  textDecoration:
                    currentViewInFilter === "expense" ? "underline" : "none",
                }}
                onClick={() => {
                  setCurrentViewInFilter("expense");
                  console.log(totalAmountByConsumeType);
                }}
              >
                지출항목
              </div>
            </div>
            <div className={styles.line}></div>
            {currentViewInFilter === "participants" && (
              <>
                <SumBox
                  title="전체"
                  sum={people.reduce((acc, curr) => acc + curr.amount, 0)}
                />
                <div className={styles.line}></div>
                {people.map((person) => (
                  <SumBox
                    key={person.id}
                    title={person.name}
                    sum={person.amount}
                    setDepositSum={setDepositSum}
                  />
                ))}
              </>
            )}
            {currentViewInFilter === "expense" && (
              <>
                <SumBox
                  title="전체"
                  sum={Object.values(totalAmountByConsumeType).reduce(
                    (acc, curr) => acc + curr,
                    0
                  )}
                />
                <div className={styles.line}></div>
                {Object.entries(totalAmountByConsumeType).map(
                  ([consumeType, amount]) => (
                    <SumBox
                      key={consumeType}
                      title={consumeType}
                      sum={amount}
                    />
                  )
                )}
              </>
            )}
          </div>
        </>
      )}
    </>
  );
}
