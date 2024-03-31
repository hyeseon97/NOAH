import Header from "./../components/common/Header";
import styles from "./GoalPage.module.css";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as GreyPeople } from "./../assets/Icon/GreyPeople.svg";
import { ReactComponent as BluePeople } from "./../assets/Icon/BluePeople.svg";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getGroupAccount } from "../api/groupaccount/GroupAccount";
import showToast from "../components/common/Toast";

export default function GoalPage() {
  const { travelId } = useParams();
  const [groupAccountInfo, setGroupAccountInfo] = useState([]);
  const [date, setDate] = useState("");

  function formatDate(dateString) {
    const pattern = /(\d{4})(\d{2})(\d{2})/;
    return dateString.replace(pattern, "$1-$2-$3");
  }

  useEffect(() => {
    const fetchGroupAccounts = async () => {
      try {
        const response = await getGroupAccount(travelId);
        console.log(response);
        if (response.status === "SUCCESS") {
          if (response.data !== null) {
            console.log("모임통장이 성공적으로 조회되었습니다.");
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
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>
                {new Intl.NumberFormat("ko-KR").format(
                  groupAccountInfo.targetAmount
                )}{" "}
                원
              </div>
            </div>
            <div className={styles.line}></div>
          </div>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>목표기간</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>{date} 일</div>
            </div>
            <div className={styles.line}></div>
          </div>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>월별 납입금액</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>
                {new Intl.NumberFormat("ko-KR").format(
                  groupAccountInfo.perAmount
                )}{" "}
                원
              </div>
            </div>
            <div className={styles.line}></div>
          </div>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>납입날짜</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>
                {groupAccountInfo.paymentDate}일
              </div>
            </div>
          </div>
        </div>
        <div className={styles.labelMedium}>달성인원</div>
        <div className={styles.memberContainer}>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <GreyPeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>핸섬건영</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <GreyPeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>핸섬건영</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
        </div>
      </div>
    </>
  );
}
