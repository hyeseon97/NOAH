import Header from "./../components/common/Header";
import styles from "./GoalPage.module.css";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as GreyPeople } from "./../assets/Icon/GreyPeople.svg";
import { ReactComponent as BluePeople } from "./../assets/Icon/BluePeople.svg";
import { ReactComponent as Check } from "./../assets/Icon/check.svg";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import {
  getGroupAccount,
  getGroupAccountMemberAndTotalDue,
  getGroupAccountTotalDue,
  updateGroupAccountInfo,
} from "../api/groupaccount/GroupAccount";
import showToast from "../components/common/Toast";

export default function GoalPage() {
  const { travelId } = useParams();
  const [groupAccountInfo, setGroupAccountInfo] = useState([]);
  const [date, setDate] = useState("");
  const [editModeTargetAmount, setEditModeTargetAmount] = useState(false);
  const [editModeTargetDate, setEditModeTargetDate] = useState(false);
  const [editModePerAmount, setEditModePerAmount] = useState(false);
  const [editModePaymentDate, setEditModePaymentDate] = useState(false);
  const [newTargetAmount, setNewTargetAmount] = useState(
    groupAccountInfo.targetAmount
  );
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
    return dateString.replace(pattern, "$1-$2-$3");
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
        // ...groupAccountInfo, // 기존 정보를 유지하면서
        groupAccountId: groupAccountInfo.groupAccountId,
        targetAmount: newTargetAmount, // 새로운 목표금액으로 업데이트
        targetDate: newTargetDate,
        perAmount: newPerAmount,
        paymentDate: newPaymentDate,
      };
      await updateGroupAccountInfo(updatedInfo); // API 호출로 정보 업데이트
      window.location.reload();
    } catch (error) {
      console.error("모임통장 정보 업데이트 실패", error);
      showToast("정보 업데이트에 실패했습니다.");
    }
  };

  useEffect(() => {
    const fetchGroupAccounts = async () => {
      try {
        const response = await getGroupAccount(travelId);
        if (response.status === "SUCCESS") {
          if (response.data !== null) {
            setGroupAccountInfo(response.data);
            const dateString = String(response.data.targetDate);
            setDate(formatDate(dateString));
          }
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchGroupAccounts();

    const fetchMemberInfo = async () => {
      try {
        const res = await getGroupAccountMemberAndTotalDue(travelId);
        if (res.status === "SUCCESS") {
          setMemberInfo(res.data);
          console.log(memberInfo);
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
      <div className={styles.goalPageContainer}>
        <div className={styles.labelMedium}>목표설정</div>
        <div className={styles.goalContainer}>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>목표금액</div>
            <div className={styles.amount}>
              {editModeTargetAmount ? (
                <>
                  <Check className={styles.icon} onClick={handleSaveChanges}>
                    확인
                  </Check>
                  <input
                    type="number"
                    value={newTargetAmount}
                    onChange={(e) => setNewTargetAmount(e.target.value)}
                    className={styles.inputBox}
                    placeholder={new Intl.NumberFormat("ko-KR").format(
                      groupAccountInfo.targetAmount
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
                    {new Intl.NumberFormat("ko-KR").format(
                      groupAccountInfo.targetAmount
                    )}{" "}
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
                  <Check className={styles.icon} onClick={handleSaveChanges}>
                    확인
                  </Check>
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
                  <div className={styles.labelMedium}>{date} 일</div>
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
                  <Check className={styles.icon} onClick={handleSaveChanges}>
                    확인
                  </Check>
                  <input
                    type="number"
                    value={newPerAmount}
                    onChange={(e) => setNewperAmount(e.target.value)}
                    className={styles.inputBox}
                    placeholder={new Intl.NumberFormat("ko-KR").format(
                      groupAccountInfo.perAmount
                    )}
                    일
                  />
                </>
              ) : (
                <>
                  <Edit className={styles.icon} onClick={handleEditPerAmount} />
                  <div className={styles.labelMedium}>
                    {new Intl.NumberFormat("ko-KR").format(
                      groupAccountInfo.perAmount
                    )}{" "}
                    원
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
                  <Check className={styles.icon} onClick={handleSaveChanges}>
                    확인
                  </Check>
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
                    {groupAccountInfo.paymentDate}일
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
  );
}
