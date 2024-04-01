import Exchange from "../components/exchange/Exchange";
import Header from "./../components/common/Header";
import styles from "./ExchangePage.module.css";
import ExchangeButton from "../components/common/ExchangeButton";
import DropdownSmall from "../components/common/DropdownSmall";
import {
  exchange,
  getExchangeAmount,
  getExchangeRate,
  setNotification,
} from "../api/exchange/Exchange";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import showToast from "./../components/common/Toast";
import ClipLoader from "react-spinners/ClipLoader";
export default function ExchangePage() {
  const [exchangeInfo, setExchangeInfo] = useState([]);
  const [exchangeRate, setExchangeRate] = useState([]);
  const [symbol, setSymbol] = useState("");
  const { travelId } = useParams();
  const [krwAmount, setKrwAmount] = useState();
  const [foreignAmount, setForeignAmount] = useState("1");
  const [currency, setCurrency] = useState("USD");
  const [warningText, setWarningText] = useState("");
  const [targetExchangeCurrency, setTargetExchangeCurrency] = useState("USD"); // 기본값으로 "USD" 설정
  const [targetExchangeRate, setTargetExchangeRate] = useState("1300"); // 사용자가 입력할 목표 환율 값
  const [isLoading, setIsLoading] = useState(true);

  function formatTime(date) {
    const hours = date.getHours();
    const minutes = date.getMinutes();

    // 시간과 분이 10보다 작으면 앞에 '0'을 붙여 두 자리로 만듦
    const formattedHours = hours < 10 ? `0${hours}` : hours;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;

    // '15:36' 형태의 문자열로 결합
    return `${formattedHours}:${formattedMinutes}`;
  }

  const handleExchangeClick = async () => {
    const object = {
      travelId: travelId,
      currency: currency,
      amount: Math.ceil(krwAmount),
      exchangeAmount: foreignAmount,
    };
    const res = await exchange(object);
    if (res.status === "SUCCESS") {
      showToast("환전이 완료되었습니다.");
      setWarningText("");
      window.location.reload();
    } else {
      setWarningText("계좌 잔액이 부족합니다.");
    }
    // const res = exchange();
  };

  const handleExchangeNotificationClick = async () => {
    const object = {
      travelId: travelId,
      targetExchangeCurrency: targetExchangeCurrency,
      targetExchangeRage: targetExchangeRate,
    };
    try {
      const res = await setNotification(object);
      if (res.status === "SUCCESS") {
        showToast("성공적으로 설정되었습니다.");
      } else {
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    const fetchExchangeInfo = async () => {
      try {
        const res = await getExchangeAmount(travelId);
        if (res.status === "SUCCESS") {
          if (res.data === null) {
          } else {
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
        setExchangeRate(res.data);
      } catch (error) {
        console.log(error);
      } finally {
        setTimeout(() => setIsLoading(false), 200);
      }
    };
    fetchExchangeRate();
  }, []);
  return (
    <>
      <Header LeftIcon="Arrow" Title="환전" />
      {isLoading && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "80vh",
            }}
          >
            <ClipLoader />
          </div>
        </>
      )}
      {!isLoading && (
        <>
          <div className={styles.ExchangeContainer}>
            <div className={styles.ExchangeContainerTop}>
              <div className={styles.labelXL}>
                {symbol} {exchangeInfo.exchangeAmount}
              </div>
              <div className={styles.paragraphSmallTop}>
                현재까지 환전한 금액
              </div>
            </div>
            <Exchange
              exchangeRateInfo={exchangeRate}
              krwAmount={krwAmount}
              foreignAmount={foreignAmount}
              setKrwAmount={setKrwAmount}
              setForeignAmount={setForeignAmount}
              currency={currency}
              setCurrency={setCurrency}
            />
            <div className={styles.paragraphSmallExchangeRight}>
              {formatTime(new Date())} 환율 기준
            </div>
            <div
              onClick={() => {
                handleExchangeClick();
              }}
            >
              <ExchangeButton buttonText="환전" warningText={warningText} />
            </div>
            <div className={styles.notificationContainer}>
              <div className={styles.notificationContainerTop}>
                <div className={styles.labelMedium}>환전알림</div>
                <DropdownSmall
                  selectedCurrency={targetExchangeCurrency}
                  setSelectedCurrency={setTargetExchangeCurrency}
                />
              </div>
              <div className={styles.notificationBox}>
                <div className={styles.boxLeft}>
                  <div className={styles.paragraphSmallExchangeLeft}>
                    {formatTime(new Date())} 환율 기준
                  </div>
                  <div className={styles.paragraphSmall}>
                    {exchangeRate[targetExchangeCurrency.toLowerCase()]}
                  </div>
                </div>
                <div className={styles.boxRight}>
                  <div className={styles.setRate}>
                    <input
                      type="number"
                      value={targetExchangeRate}
                      onChange={(e) => setTargetExchangeRate(e.target.value)}
                      className={styles.headingLarge}
                    ></input>
                    <div className={styles.paragraphSmallGrey}>
                      이하일때 알림
                    </div>
                  </div>
                  <div
                    className={styles.labelSmallBlue}
                    onClick={handleExchangeNotificationClick}
                  >
                    설정
                  </div>
                </div>
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
}
