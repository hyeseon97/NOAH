import Exchange from "../components/exchange/Exchange";
import Header from "./../components/common/Header";
import styles from "./ExchangePage.module.css";
import ExchangeButton from "../components/common/ExchangeButton";
export default function ExchangePage() {
  function formatTime(date) {
    const hours = date.getHours();
    const minutes = date.getMinutes();

    // 시간과 분이 10보다 작으면 앞에 '0'을 붙여 두 자리로 만듦
    const formattedHours = hours < 10 ? `0${hours}` : hours;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;

    // '15:36' 형태의 문자열로 결합
    return `${formattedHours}:${formattedMinutes}`;
  }
  return (
    <>
      <Header LeftIcon="Arrow" Title="환전" />
      <div className={styles.ExchangeContainer}>
        <div className={styles.ExchangeContainerTop}>
          <div className={styles.labelXL}>$1300</div>
          <div className={styles.paragraphSmall}>현재까지 환전한 금액</div>
        </div>
        <Exchange />
        <div className={styles.paragraphSmallExchange}>
          {formatTime(new Date())} 환율 기준
        </div>
        <ExchangeButton
          buttonText="환전"
          warningText="계좌에 잔액이 부족합니다."
        />
      </div>
    </>
  );
}
