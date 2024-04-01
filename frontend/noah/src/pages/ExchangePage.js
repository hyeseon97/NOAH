import Exchange from "../components/exchange/Exchange";
import Header from "./../components/common/Header";
import styles from "./ExchangePage.module.css";
import ExchangeButton from "../components/common/ExchangeButton";
import DropdownSmall from "../components/common/DropdownSmall";
import { getExchangeAmount, getExchangeRate } from "../api/exchange/Exchange";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
export default function ExchangePage() {
  const [exchangeInfo, setExchangeInfo] = useState([]);
  const [exchangeRate, setExchangeRate] = useState([]);
  const [symbol, setSymbol] = useState("");
  const { travelId } = useParams();

  function formatTime(date) {
    const hours = date.getHours();
    const minutes = date.getMinutes();

    // 시간과 분이 10보다 작으면 앞에 '0'을 붙여 두 자리로 만듦
    const formattedHours = hours < 10 ? `0${hours}` : hours;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;

    // '15:36' 형태의 문자열로 결합
    return `${formattedHours}:${formattedMinutes}`;
  }

  const handleExchangeClick = () => {
    const object = {
      travelId: travelId,
      // currency:
      // amount:
      // exchangeAmount:
    };
    // const res = exchange();
  };

  useEffect(() => {
    const fetchExchangeInfo = async () => {
      try {
        const res = await getExchangeAmount(travelId);
        if (res.status === "SUCCESS") {
          if (res.data === null) {
            console.log("내역없음");
          } else {
            console.log("정보 불러오기 성공", res.data);
            setExchangeInfo(res.data);
            switch (res.data.currency) {
              case "USD":
                setSymbol("$");
                break;
              case "JPY":
                setSymbol("¥");
                break;
              case "CNY":
                setSymbol("元");
                break;
              case "EUR":
                setSymbol("€");
                break;
              default:
            }
          }
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchExchangeInfo();

    const fetchExchangeRate = async () => {
      try {
        const res = await getExchangeRate();
        console.log(res.data);
        setExchangeRate(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchExchangeRate();
  }, []);
  return (
    <>
      <Header LeftIcon="Arrow" Title="환전" />
      <div className={styles.ExchangeContainer}>
        <div className={styles.ExchangeContainerTop}>
          <div className={styles.labelXL}>
            {symbol} {exchangeInfo.exchangeAmount}
          </div>
          <div className={styles.paragraphSmallTop}>현재까지 환전한 금액</div>
        </div>
        <Exchange exchangeRateInfo={exchangeRate} />
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
              <div className={styles.paragraphSmall}>{exchangeRate.usd}</div>
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
