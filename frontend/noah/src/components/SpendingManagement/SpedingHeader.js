import styles from "./SpendingHeader.module.css";

export default function SpendingHeader() {
  return (
    <>
      <div className={styles.tradeHeader}>
        <div className={styles.labelMedium}>2024.03.19</div>
        <div className={styles.headerRight}>
          <div className={styles.labelSmallGray}>사용금액</div>
          <div className={styles.labelSmall}>395,374원</div>
        </div>
      </div>
      <div className={styles.line}></div>
    </>
  );
}
