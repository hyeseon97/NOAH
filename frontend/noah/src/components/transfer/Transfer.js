import Stick from "../common/Stick";
import Dropdown from "./../common/Dropdown";
import { useState, useEffect } from "react";
export default function Transfer() {
  const borderStyle = {
    border: "0.277vw solid #E1E1E1",
    backdropFilter: "blur(0.277vw)",
    borderRadius: "2.77vw",
    width: "83vw",
    height: "18.5vw",
    display: "flex",
    //alignItems: "baseline",
    justifyContent: "space-between",
  };

  const leftCurrencyContainer = {
    marginLeft: "2.22vw",
    marginTop: "4.44vw",
  };

  const paragraphSmallContainer = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 500,
    fontSize: "3.33vw",
    lineHeight: "160%",
    color: "black",
    textAlign: "center",
    width: "22.22vw",
    height: "8.33vw",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  };

  const rightCurrencyContainer = {
    marginTop: "1.11vw",
    marginRight: "3.33vw",
  };
  const inputStyle = {
    width: "50vw",
    height: "8.33vw",

    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: "700",
    fontSize: "5.92vw",
    lineHeight: "140%",
    textAlign: "right",
    border: "none",
    outline: "none",
    color: "#000000",
  };
  const resultStyle = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 500,
    fontSize: "3.33vw",
    lineHeight: "160%",
    color: "black",

    width: "50vw",
    height: "8.33vw",
    textAlign: "right",
  };

  const [currency, setCurrency] = useState("USD");
  const [krwAmount, setKrwAmount] = useState("1350.01");
  const [foreignAmount, setForeignAmount] = useState("1");

  // KRW 1원일 때의 환율
  const exchangeRates = {
    USD: 0.00074,
    KRW: 1,
    CNY: 0.0054,
    EUR: 0.00068,
    JPY: 0.11,
  };

  // 화폐 단위 표시를 위한 데이터
  const currencyLabels = {
    USD: "달러",
    KRW: "원",
    CNY: "위안",
    EUR: "유로",
    JPY: "엔",
  };

  // 선택된 화폐 단위에 따라 표시되는 텍스트 변경
  const currencyLabel = currencyLabels[currency];

  // 외화 금액 변경 핸들러
  const handleForeignAmountChange = (e) => {
    if (isNaN(e.target.value) || e.target.value.includes("e")) {
      return;
    }

    const newForeignAmount = e.target.value;
    setForeignAmount(newForeignAmount);

    const newKrwAmount =
      newForeignAmount / exchangeRates[currency] / exchangeRates["KRW"];
    setKrwAmount(newKrwAmount.toFixed(2));
  };

  // KRW 금액 변경 핸들러
  const handleKrwAmountChange = (e) => {
    if (isNaN(e.target.value) || e.target.value.includes("e")) {
      return;
    }

    const newKrwAmount = e.target.value;
    setKrwAmount(newKrwAmount);

    const newForeignAmount =
      newKrwAmount * exchangeRates[currency] * exchangeRates["KRW"];
    setForeignAmount(newForeignAmount.toFixed(2));
  };

  useEffect(() => {
    // 환율정보 받아오는 API
  }, []);

  useEffect(() => {
    const newKrwAmount =
      foreignAmount / exchangeRates[currency] / exchangeRates["KRW"];
    setKrwAmount(newKrwAmount.toFixed(2));
  }, [currency]);

  return (
    <>
      <div style={borderStyle}>
        <div style={leftCurrencyContainer}>
          <Dropdown
            selectedCurrency={currency}
            setSelectedCurrency={setCurrency}
          />
        </div>
        <div style={rightCurrencyContainer}>
          <input
            style={inputStyle}
            value={foreignAmount}
            onChange={handleForeignAmountChange}
          ></input>
          <div style={resultStyle}>
            {new Intl.NumberFormat("ko-KR").format(foreignAmount)}{" "}
            {currencyLabel}
          </div>
        </div>
      </div>
      <Stick />
      <div style={borderStyle}>
        <div style={leftCurrencyContainer}>
          <div style={paragraphSmallContainer}>한국 KRW</div>
        </div>
        <div style={rightCurrencyContainer}>
          <input
            style={inputStyle}
            value={krwAmount}
            onChange={handleKrwAmountChange}
          ></input>
          <div style={resultStyle}>
            {new Intl.NumberFormat("ko-KR").format(krwAmount)} 원
          </div>
        </div>
      </div>
    </>
  );
}
