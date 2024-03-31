import Header from "../components/common/Header";
import styles from "./SpendingManagementPage.module.css";
import { ReactComponent as CancelGrey } from "./../assets/Icon/CancelGrey.svg";
import DropdownMember from "../components/common/DropdownMember";
import DropdownConsumeType from "../components/common/DropdownConsumeType";
import AOS from "aos";
import "aos/dist/aos.css";
import { useState, useEffect } from "react";
import Spending from "../components/SpendingManagement/Spending";

export default function SpendingManagemnetPage() {
  const [isFilter, setIsFilter] = useState(false);

  useEffect(() => {
    AOS.init({
      duration: 200,
    });
  });

  const handleRightIconClick = () => {
    setIsFilter((prev) => !prev);
  };
  return (
    <>
      <Header
        LeftIcon="Arrow"
        Title="소비관리"
        RightIcon="Filter"
        onRightIconClick={handleRightIconClick}
      />
      {!isFilter && (
        <>
          <div className={styles.tradeHeader}>
            <div className={styles.labelMedium}>2024.03.19</div>
            <div className={styles.headerRight}>
              <div className={styles.labelSmallGray}>사용금액</div>
              <div className={styles.labelSmall}>395,374원</div>
            </div>
          </div>
          <div className={styles.line}></div>

          <Spending />
          <Spending />
          <Spending />
        </>
      )}
      {isFilter && <>필터링</>}
    </>
  );
}
