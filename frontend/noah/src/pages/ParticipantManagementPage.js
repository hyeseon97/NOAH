import Header from "./../components/common/Header";
import styles from "./ParticipantManagementPage.module.css";
export default function ParticipantManagementPage() {
  return (
    <>
      <Header LeftIcon="Arrow" Title="인원관리" />
      <div>
        <div className={styles.memberBox}>
          <div className={styles.memberBoxLeft}>
            <div className={styles.labelLarge}>준규</div>
            <div className={styles.paragraphSmall}>wnsrb123@naver.com</div>
            <div className={styles.paragraphSmall}>강준규</div>
          </div>
          <div className={styles.memberBoxRight}>
            <div className={styles.paragraphSmall}>2024-03-19 14:28:56</div>
          </div>
        </div>
        <div className={styles.line}></div>
      </div>
    </>
  );
}
