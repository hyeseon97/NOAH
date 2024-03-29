import Header from "../components/common/Header";
import styles from "./SpendingManagementPage.module.css";
import { ReactComponent as CancelGrey } from "./../assets/Icon/CancelGrey.svg";
import DropdownMember from "../components/common/DropdownMember";
import DropdownConsumeType from "../components/common/DropdownConsumeType";

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
      <div className={styles.tradeBox}>
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
            <DropdownMember />
            <DropdownConsumeType />
          </div>
          <div className={styles.paragraphSmall}>잔액 1,112,000원</div>
        </div>
      </div>
      <div className={styles.line}></div>
    </>
  );
}
