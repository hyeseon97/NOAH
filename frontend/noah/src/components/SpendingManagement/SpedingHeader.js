import styles from "./SpendingHeader.module.css";

export default function SpendingHeader({ date, totalCost }) {
  function formatDate(dateString) {
    // YYYYMMDD 형식의 문자열을 입력받아 YY.MM.DD 형식으로 변환합니다.
    return dateString.replace(/^(\d{2})(\d{2})(\d{2})(\d{2})$/, "$2.$3.$4");
  }

  return (
    <>
      <div className={styles.tradeHeader}>
        <div className={styles.labelMedium}>{formatDate(date)}</div>
        <div className={styles.headerRight}>
          <div className={styles.labelSmallGray}>사용금액</div>
          <div className={styles.labelSmall}>
            {new Intl.NumberFormat("ko-KR").format(totalCost)}원
          </div>
        </div>
      </div>
      <div className={styles.line}></div>
    </>
  );
}
