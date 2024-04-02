import Header from "./../components/common/Header";
import styles from "./GoalPage.module.css";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as GreyPeople } from "./../assets/Icon/GreyPeople.svg";
import { ReactComponent as BluePeople } from "./../assets/Icon/BluePeople.svg";
import { ReactComponent as Check } from "./../assets/Icon/check.svg";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ClipLoader from "react-spinners/ClipLoader";
import {
  getGroupAccount,
  getGroupAccountMemberAndTotalDue,
  getGroupAccountTotalDue,
  updateGroupAccountInfo,
} from "../api/groupaccount/GroupAccount";
import showToast from "../components/common/Toast";

export default function GoalPage() {
  const [isLoading, setIsLoading] = useState(true);
  const { travelId } = useParams();
  const [groupAccountInfo, setGroupAccountInfo] = useState([]);
  const [date, setDate] = useState("");
  const [editModeTargetAmount, setEditModeTargetAmount] = useState(false);
  const [editModeTargetDate, setEditModeTargetDate] = useState(false);
  const [editModePerAmount, setEditModePerAmount] = useState(false);
  const [editModePaymentDate, setEditModePaymentDate] = useState(false);
  const [newTargetAmount, setNewTargetAmount] = useState();
  const [newTargetDate, setNewTargetDate] = useState(
    groupAccountInfo.targetDate
  );
  const [newPerAmount, setNewperAmount] = useState(groupAccountInfo.perAmount);
  const [newPaymentDate, setNewpaymentDate] = useState(
    groupAccountInfo.paymentDate
  );
  const [memberInfo, setMemberInfo] = useState([]);
  const [totalDue, setTotalDue] = useState("");

  function formatDate(dateString) {
    const pattern = /(\d{4})(\d{2})(\d{2})/;
    return dateString.replace(pattern, "$1. $2. $3");
  }

  const handleEditTargetAmount = () => {
    setEditModeTargetAmount(true);
  };
  const handleEditTargetDate = () => {
    setEditModeTargetDate(true);
  };
  const handleEditPerAmount = () => {
    setEditModePerAmount(true);
  };
  const handleEditPaymentDate = () => {
    setEditModePaymentDate(true);
  };

  // 변경사항 저장 함수
  const handleSaveChanges = async () => {
    try {
      const updatedInfo = {
        groupAccountId: groupAccountInfo.groupAccountId,
        targetAmount: newTargetAmount, // 새로운 목표금액으로 업데이트
        targetDate: newTargetDate,
        perAmount: newPerAmount,
        paymentDate: newPaymentDate,
      };
      const res = await updateGroupAccountInfo(updatedInfo); // API 호출로 정보 업데이트
      console.log(res);
      if (res.status === "SUCCESS") {
        // 상태 업데이트로 UI 변경
        console.log(newTargetAmount);
        setGroupAccountInfo(() => ({
          targetAmount: newTargetAmount,
          targetDate: newTargetDate,
          perAmount: newPerAmount,
          paymentDate: newPaymentDate,
        }));
        // 모든 수정 모드 비활성화
        setEditModeTargetAmount(false);
        setEditModeTargetDate(false);
        setEditModePerAmount(false);
        setEditModePaymentDate(false);
        showToast("정보가 업데이트되었습니다.");
      } else if (res.status === "ERROR") {
        showToast("모임 통장의 소유주만 변경 가능합니다.");
        setTimeout(() => {
          window.location.reload();
        }, 500);
      }
    } catch (error) {
      showToast("정보 업데이트에 실패했습니다.");
    }
  };

  useEffect(() => {
    const fetchGroupAccounts = async () => {
      try {
        setIsLoading(true);
        const response = await getGroupAccount(travelId);
        if (response.status === "SUCCESS") {
          if (response.data !== null) {
            setGroupAccountInfo(response.data);
            const dateString = String(response.data.targetDate);
            setDate(formatDate(dateString));
            setNewTargetAmount(response.data.targetAmount);
            setNewTargetDate(response.data.targetDate);
            setNewpaymentDate(response.data.paymentDate);
            setNewperAmount(response.data.perAmount);
          }
        }
      } catch (error) {
        console.log(error);
      } finally {
        setIsLoading(false);
      }
    };
    fetchGroupAccounts();

    const fetchMemberInfo = async () => {
      try {
        const res = await getGroupAccountMemberAndTotalDue(travelId);
        if (res.status === "SUCCESS") {
          setMemberInfo(res.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchMemberInfo();

    const fetchTotalDue = async () => {
      try {
        const res = await getGroupAccountTotalDue(travelId);
        if (res.status === "SUCCESS") {
          setTotalDue(res.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchTotalDue();
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="여행 이름" />
      {isLoading && (
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
      {!isLoading && (
        <>
          <div className={styles.goalPageContainer}>
            <div className={styles.containerHeader}>
              <div className={styles.labelMedium}>목표설정</div>
              {(editModeTargetAmount ||
                editModeTargetDate ||
                editModePerAmount ||
                editModePaymentDate) && (
                <div
                  className={styles.labelSmallCheck}
                  onClick={handleSaveChanges}
                >
                  확인
                </div>
              )}
            </div>
            <div className={styles.goalContainer}>
              <div className={styles.goalColumn}>
                <div className={styles.labelSmall}>목표금액</div>
                <div className={styles.amount}>
                  {editModeTargetAmount ? (
                    <>
                      <input
                        type="number"
                        value={newTargetAmount}
                        onChange={(e) => setNewTargetAmount(e.target.value)}
                        className={styles.inputBox}
                        placeholder={new Intl.NumberFormat("ko-KR").format(
                          newTargetAmount
                        )}
                      />
                    </>
                  ) : (
                    <>
                      <Edit
                        className={styles.icon}
                        onClick={handleEditTargetAmount}
                      />
                      <div className={styles.labelMedium}>
                        {new Intl.NumberFormat("ko-KR").format(newTargetAmount)}{" "}
                        원
                      </div>
                    </>
                  )}
                </div>
                <div className={styles.line}></div>
              </div>
              <div className={styles.goalColumn}>
                <div className={styles.labelSmall}>목표기간</div>
                <div className={styles.amount}>
                  {editModeTargetDate ? (
                    <>
                      <input
                        type="number"
                        value={newTargetDate}
                        onChange={(e) => setNewTargetDate(e.target.value)}
                        className={styles.inputBox}
                        placeholder={date}
                        일
                      />
                    </>
                  ) : (
                    <>
                      <Edit
                        className={styles.icon}
                        onClick={handleEditTargetDate}
                      />
                      <div className={styles.labelMedium}>{date}</div>
                    </>
                  )}
                </div>
                <div className={styles.line}></div>
              </div>
              <div className={styles.goalColumn}>
                <div className={styles.labelSmall}>월별 납입금액</div>
                <div className={styles.amount}>
                  {editModePerAmount ? (
                    <>
                      <input
                        type="number"
                        value={newPerAmount}
                        onChange={(e) => setNewperAmount(e.target.value)}
                        className={styles.inputBox}
                        placeholder={new Intl.NumberFormat("ko-KR").format(
                          newPerAmount
                        )}
                        일
                      />
                    </>
                  ) : (
                    <>
                      <Edit
                        className={styles.icon}
                        onClick={handleEditPerAmount}
                      />
                      <div className={styles.labelMedium}>
                        {new Intl.NumberFormat("ko-KR").format(newPerAmount)} 원
                      </div>
                    </>
                  )}
                </div>
                <div className={styles.line}></div>
              </div>
              <div className={styles.goalColumn}>
                <div className={styles.labelSmall}>납입날짜</div>
                <div className={styles.amount}>
                  {editModePaymentDate ? (
                    <>
                      <input
                        type="number"
                        value={newPaymentDate}
                        onChange={(e) => setNewpaymentDate(e.target.value)}
                        className={styles.inputBox}
                        placeholder={groupAccountInfo.paymentDate}
                      />
                    </>
                  ) : (
                    <>
                      <Edit
                        className={styles.icon}
                        onClick={handleEditPaymentDate}
                      />
                      <div className={styles.labelMedium}>
                        {groupAccountInfo.paymentDate} 일
                      </div>
                    </>
                  )}
                </div>
              </div>
            </div>
            <div className={styles.labelMedium}>달성인원</div>
            <div className={styles.memberContainer}>
              {memberInfo.map((member, index) => (
                <div key={index} className={styles.item}>
                  {member.payment_amount >= totalDue ? (
                    <BluePeople className={styles.iconPeople} />
                  ) : (
                    <GreyPeople className={styles.iconPeople} />
                  )}
                  <div className={styles.paragraphSmall}>
                    {member.memberNickname}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </>
      )}
    </>
  );
}
