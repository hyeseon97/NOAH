import styles from "./Spending.module.css";
import AOS from "aos";
import "aos/dist/aos.css";
import { useEffect, useState } from "react";
import { ReactComponent as CancelGrey } from "../../assets/Icon/CancelGrey.svg";
import DropdownMember from "../common/DropdownMember";
import DropdownConsumeType from "../common/DropdownConsumeType";

export default function Spending() {
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
          <div className={styles.labelSmallGray}>20:00:13</div>
          <CancelGrey className={styles.icon} />
        </div>
        <div className={styles.tradeBoxMiddle}>
          <div className={styles.labelMedium}>아시아나항공</div>
          <div className={styles.labelMedium}>380,000원</div>
        </div>
        <div className={styles.tradeBoxBottom}>
          <div className={styles.tradeBoxBottomLeft}>
            <DropdownMember selected={person} setSelected={setPerson} />
            <DropdownConsumeType
              selected={consumeType}
              setSelected={setConsumeType}
            />
          </div>
          <div className={styles.paragraphSmall}>잔액 1,112,000원</div>
        </div>
      </div>
      <div className={styles.line}></div>
    </>
  );
}
