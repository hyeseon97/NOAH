import styles from "./Spending.module.css";
import AOS from "aos";
import "aos/dist/aos.css";
import { useEffect, useState } from "react";
import { ReactComponent as CancelGrey } from "../../assets/Icon/CancelGrey.svg";
import DropdownMember from "../common/DropdownMember";
import DropdownConsumeType from "../common/DropdownConsumeType";

export default function Spending({ transaction }) {
  function formatTime(timeString) {
    // HHMMSS 형식의 문자열을 입력받아 HH:MM:SS 형식으로 변환합니다.
    return timeString.replace(/^(\d{2})(\d{2})(\d{2})$/, "$1:$2:$3");
  }

  const [person, setPerson] = useState("강준규");
  const [consumeType, setConsumeType] = useState("공통");

  useEffect(() => {
    AOS.init({
      duration: 200,
    });
  });
  return (
    <>
      <div className={styles.tradeBox} data-aos="fade-up">
        <div className={styles.tradeBoxTop}>
          <div className={styles.labelSmallGray}>
            {formatTime(transaction.time)}
          </div>
          <CancelGrey className={styles.icon} />
        </div>
        <div className={styles.tradeBoxMiddle}>
          <div className={styles.labelMedium}>{transaction.name}</div>

          {transaction.type === 1 && (
            <>
              <div className={styles.labelMedium} style={{ color: "blue" }}>
                {new Intl.NumberFormat("ko-KR").format(transaction.cost)}원
              </div>
            </>
          )}
          {transaction.type === 2 && (
            <>
              <div className={styles.labelMedium}>
                {new Intl.NumberFormat("ko-KR").format(transaction.cost)}원
              </div>
            </>
          )}
        </div>
        <div className={styles.tradeBoxBottom}>
          <div className={styles.tradeBoxBottomLeft}>
            {transaction.type === 2 && (
              <>
                <DropdownMember selected={person} setSelected={setPerson} />
                <DropdownConsumeType
                  selected={consumeType}
                  setSelected={setConsumeType}
                />
              </>
            )}
          </div>
          <div className={styles.paragraphSmall}>
            잔액 {new Intl.NumberFormat("ko-KR").format(transaction.amount)}원
          </div>
        </div>
      </div>
      <div className={styles.line}></div>
    </>
  );
}
