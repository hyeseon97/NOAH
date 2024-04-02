import { useState } from "react";
import Header from "./../components/common/Header";

export default function MarketPage() {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handlePriceChange = (e) => {
    setPrice(e.target.value);
  };

  const handleClickPayment = () => {
    // 결제 요청 보내기
  };

  return (
    <>
      <Header LeftIcon="none" Title="결제 승인" />
      <input onChange={handleNameChange} value={name} />
      <input onChang={handlePriceChange} value={price} />
      <div onClick={handleClickPayment}>결제</div>
    </>
  );
}
