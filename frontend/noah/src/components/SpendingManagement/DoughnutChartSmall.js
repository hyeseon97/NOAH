import { useEffect, useState, React } from "react";
import styles from "./DoughnutChartSmall.module.css";

export default function DoughnutChartSmall({ percent, isRed = false }) {
  const [t, setT] = useState(0);
  const donutStyle = {
    background: isRed
      ? `conic-gradient(#E74733 0% ${t}%, #ED7566 ${t}% 100%)`
      : `conic-gradient(#2f80ED 0% ${t}%, #66ACFF ${t}% 100%)`,
  };

  useEffect(() => {
    const donutAnimation = setInterval(() => {
      if (t < percent) {
        // percent가 t보다 큰 경우, t를 1씩 증가
        setT((prevT) => prevT + 1);
      } else if (t > percent) {
        // percent가 t보다 작은 경우, t를 1씩 감소
        setT((prevT) => prevT - 1);
      } else {
        clearInterval(donutAnimation);
      }
    }, 10);

    return () => clearInterval(donutAnimation); // 컴포넌트 언마운트 시 인터벌 클리어
  }, [t, percent]);

  return (
    <>
      <div className={styles.doughnut} style={donutStyle}>
        <div className={styles.percent}>{t}%</div> {/* 중앙에 표시될 퍼센트 */}
      </div>
    </>
  );
}
