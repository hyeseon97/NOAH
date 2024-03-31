import Header from "../components/common/Header";
import styles from "./PlanningCreatePage.module.css";
import { ReactComponent as Airplane } from "./../assets/Icon/Airplane.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import { ReactComponent as Calender } from "./../assets/Icon/Calender.svg";
import RoundButton from "../components/common/RoundButton";

export default function PlanningCreatePage() {
  return (
    <>
      <Header LeftIcon="Arrow" Title="계획 생성" />
      <div className={styles.planningCreateContainer}>
        <div className={styles.iconBox}>
          <Airplane className={styles.icon} />
        </div>
        <div className={styles.labelMedium}>여행 국가</div>
        <div className={styles.setBox}>
          <Mark className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            placeholder="장소를 입력하세요"
          ></input>
        </div>
        <div className={styles.labelMedium}>여행 날짜</div>
        <div className={styles.setBox}>
          <Calender className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            placeholder="날짜를 설정하세요"
          ></input>
        </div>
        <div className={styles.buttonBox}>
          <RoundButton className={styles.button} buttonText="새 계획 생성" />
        </div>
      </div>
    </>
  );
}
