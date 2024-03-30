import Exchange from "../components/exchange/Exchange";
import Header from "./../components/common/Header";
import styles from "./ExchangePage.module.css";
import ExchangeButton from "../components/common/ExchangeButton";
import DropdownSmall from "../components/common/DropdownSmall";
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
          <div className={styles.paragraphSmallTop}>현재까지 환전한 금액</div>
        </div>
        <Exchange />
        <div className={styles.paragraphSmallExchangeRight}>
          {formatTime(new Date())} 환율 기준
        </div>
        <ExchangeButton
          buttonText="환전"
          warningText="계좌에 잔액이 부족합니다."
        />
        <div className={styles.notificationContainer}>
          <div className={styles.notificationContainerTop}>
            <div className={styles.labelMedium}>환전알림</div>
            <DropdownSmall />
          </div>
          <div className={styles.notificationBox}>
            <div className={styles.boxLeft}>
              <div className={styles.paragraphSmallExchangeLeft}>
                {formatTime(new Date())} 환율 기준
              </div>
              <div className={styles.paragraphSmall}>1145.70</div>
            </div>
            <div className={styles.boxRight}>
              <div className={styles.setRate}>
                <div className={styles.headingLarge}>1100</div>
                <div className={styles.paragraphSmallGrey}>이하일때 알림</div>
              </div>
              <div className={styles.labelSmallBlue}> 설정</div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
