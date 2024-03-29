import Header from "./../components/common/Header";
import styles from "./MyPage.module.css";

export default function MyPage() {
  return (
    <>
      <Header LeftIcon="Cancel" Title="마이페이지" />
      <div className={styles.infoContainer}>
        <div className={styles.labelLarge}>큐티핸섬준규</div>
        <div className={styles.labelMedium}>강준규</div>
        <div className={styles.labelSmall}>2024-03-01 가입</div>
      </div>
      <div className={styles.menuContainer}>ㅎㅇㅎㅇ</div>
    </>
  );
}
