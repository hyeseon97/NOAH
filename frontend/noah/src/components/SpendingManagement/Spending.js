import styles from "./Spending.module.css";
import AOS from "aos";
import "aos/dist/aos.css";
import { useEffect, useState } from "react";
import { ReactComponent as CancelGrey } from "../../assets/Icon/CancelGrey.svg";
import DropdownMember from "../common/DropdownMember";
import DropdownConsumeType from "../common/DropdownConsumeType";
import { changeTrade } from "../../api/trade/Trade";

export default function Spending({ transaction, people }) {
  function formatTime(timeString) {
    // HHMMSS 형식의 문자열을 입력받아 HH:MM:SS 형식으로 변환합니다.
    return timeString.replace(/^(\d{2})(\d{2})(\d{2})$/, "$1:$2:$3");
  }

  const [person, setPerson] = useState(
    transaction.memberName === null ? "공통" : transaction.memberName
  );
  const [consumeType, setConsumeType] = useState(transaction.consumeType);

  useEffect(() => {
    AOS.init({
      duration: 200,
    });
  });

  useEffect(() => {
    const selectedPerson = people.find((p) => p.name === person);
    const memberId = selectedPerson ? selectedPerson.id : null; // 만약 해당 person을 찾지 못했다면 null

    const res = changeTrade({
      tradeId: transaction.tradeId,
      memberId: memberId,
      consumeType: consumeType,
    });
    console.log(res);
  }, [person, consumeType]);

  return (
    <>
      <div className={styles.tradeBox} data-aos="fade-up">
        <div className={styles.tradeBoxTop}>
          <div className={styles.labelSmallGray}>
            {formatTime(transaction.time)}
          </div>
          {/* <CancelGrey className={styles.icon} /> */}
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
                <DropdownMember
                  selected={person}
                  setSelected={setPerson}
                  people={people}
                />
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
