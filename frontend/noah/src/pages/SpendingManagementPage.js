import Header from "../components/common/Header";
import styles from "./SpendingManagementPage.module.css";

export default function SpendingManagemnetPage() {
  return (
    <>
      <Header LeftIcon="Arrow" Title="소비관리" RightIcon="Filter" />
      <div className={styles.tradeHeader}>
        <div className={styles.labelMedium}>2024.03.19</div>
        <div className={styles.headerRight}>
          <div className={styles.labelSmallGray}>사용금액</div>
          <div className={styles.labelSmall}>395,374원</div>
        </div>
      </div>
      <div className={styles.line}></div>
      <div>ㅎㅇㅎㅇ</div>
    </>
  );
}
