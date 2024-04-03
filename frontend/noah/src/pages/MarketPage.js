import { useState, useEffect } from "react";
import Header from "./../components/common/Header";
import { ReactComponent as Store } from "./../assets/Icon/Store.svg";
import styles from "./MarketPage.module.css";
import { withdrawByQR } from "../api/payment/Payment";

export default function MarketPage() {
  const [name, setName] = useState("");
  const [rawPrice, setRawPrice] = useState(""); // 실제 숫자 값을 저장하는 상태
  const [displayPrice, setDisplayPrice] = useState(""); // 화면에 표시될 포맷된 값
  const [warningText, setWarningText] = useState("");
  const [type, setType] = useState("공통");

  useEffect(() => {
    // rawPrice가 변경될 때마다 displayPrice를 업데이트
    const formattedPrice = rawPrice.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    setDisplayPrice(formattedPrice);
  }, [rawPrice]);

  const handlePriceChange = (e) => {
    const value = e.target.value.replace(/\D/g, ""); // 숫자가 아닌 모든 문자 제거
    setRawPrice(value); // 숫자만 저장
  };

  const handleNameChange = (e) => {
    const value = e.target.value;
    if (value.length > 20) {
      return;
    }

    setName(value);
  };

  const handleTypeChange = (e) => {
    const value = e.target.value;

    setType(value);
  };

  const handleClickPayment = async () => {
    // 결제 요청 보내기
    if (name.length === 0 || rawPrice.length === 0) {
      return;
    }
    const hash = window.location.hash;

    const queryString = hash.substring(hash.indexOf("?") + 1);

    const params = new URLSearchParams(queryString);

    const memberId = params.get("memberId");
    const travelId = params.get("travelId");

    console.log({
      memberId: memberId,
      travelId: travelId,
      transactionBalance: rawPrice,
      transactionSummary: name,
      consumeType: type,
    });

    const res = await withdrawByQR({
      memberId: memberId,
      travelId: travelId,
      transactionBalance: rawPrice,
      transactionSummary: name,
      consumeType: "공통",
    });
    console.log(res);
    if (res.status === "SUCCESS") {
      window.alert("결제가 승인되었습니다.");
      window.location.reload();
      setWarningText("");
    } else {
      setWarningText("결제가 거절되었습니다.");
    }
  };

  const warningStyle = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    lineHeight: "160%",
    color: "#E11900",
    fontSize: "3.3vw",
    marginLeft: "8vw",
  };

  return (
    <>
      <Header LeftIcon="none" Title="결제 승인" />
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          width: "100vw",
          height: "20vh",
        }}
      >
        <Store style={{ width: "22.2vw", height: "22.2vw" }} />
      </div>

      <div className={styles.inputBorder}>
        {name !== "" && <div className={styles.label}>결제 장소</div>}
        {name === "" && <div className={styles.label}></div>}
        <div style={{ display: "flex" }}>
          <input
            placeholder="결제 장소를 입력하세요"
            className={styles.input}
            value={name}
            onChange={handleNameChange}
          ></input>
          <div style={{ height: "100%", width: "4.44vw" }}></div>
        </div>
      </div>
      <div className={styles.inputBorder}>
        {displayPrice !== "" && (
          <div className={styles.label}>결제 가격 (원)</div>
        )}
        {displayPrice === "" && <div className={styles.label}></div>}
        <div style={{ display: "flex" }}>
          <input
            placeholder="결제 가격을 입력하세요"
            className={styles.input}
            value={displayPrice}
            onChange={handlePriceChange}
          ></input>
          <div style={{ height: "100%", width: "4.44vw" }}></div>
        </div>
      </div>
      <div className={styles.inputBorder}>
        <div className={styles.label}>소비 분류</div>

        <div style={{ display: "flex" }}>
          <div>
            <select
              className={styles.dropdownStyle}
              value={type}
              onChange={handleTypeChange}
            >
              <option value="공통">공통</option>
              <option value="식비">식비</option>
              <option value="숙박">숙박</option>
              <option value="항공/교통">항공/교통</option>
              <option value="환전">환전</option>
              <option value="쇼핑">쇼핑</option>
              <option value="기타">기타</option>
            </select>
          </div>
          <div style={{ height: "100%", width: "4.44vw" }}></div>
        </div>
      </div>

      <div style={warningStyle}>{warningText}</div>
      <div className={styles.button} onClick={handleClickPayment}>
        결제 승인
      </div>
    </>
  );
}
