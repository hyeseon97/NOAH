import Header from "./../components/common/Header";
import styles from "./ParticipantManagementPage.module.css";
import { ReactComponent as Plus } from "./../assets/Icon/Plus.svg";
import AOS from "aos";
import "aos/dist/aos.css";
import { useEffect } from "react";
export default function ParticipantManagementPage() {
  useEffect(() => {
    AOS.init({
      duration: 200,
    });
  });

  return (
    <>
      <Header LeftIcon="Arrow" Title="인원관리" />
      <div>
        <div className={styles.memberBox}>
          <div className={styles.memberBoxLeft}>
            <div className={styles.labelLarge}>준규</div>
            <div className={styles.paragraphSmallGrey}>wnsrb123@naver.com</div>
            <div className={styles.paragraphSmallGrey}>강준규</div>
          </div>
          <div className={styles.memberBoxRight}>
            <div className={styles.paragraphSmallGrey}>2024-03-19 14:28:56</div>
          </div>
        </div>
        <div className={styles.line}></div>
      </div>
      <div className={styles.inviteBox}>
        <Plus className={styles.icon} />
        <div className={styles.paragraphSmall}>새로운 멤버를 추가</div>
      </div>
      <div className={styles.line}></div>
    </>
  );
}
