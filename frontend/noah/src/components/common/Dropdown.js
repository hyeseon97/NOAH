import React, { useState } from "react";

function Dropdown({ selectedCurrency, setSelectedCurrency }) {
  const handleChange = (event) => {
    setSelectedCurrency(event.target.value); // 선택된 옵션의 value로 상태 업데이트
  };

  const dropdownStyle = {
    width: "22.22vw",
    height: "8.33vw",
    border: "0.277vw solid #E1E1E1",
    backdropFilter: "blur(0.277vw)",
    borderRadius: "1.385vw",
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 500,
    fontSize: "3.33vw",
    lineHeight: "160%",
    color: "black",
    textAlign: "center",
    cursor: "pointer",
  };

  return (
    <div style={{ position: "relative" }}>
      <select
        value={selectedCurrency}
        onChange={handleChange}
        style={dropdownStyle}
      >
        <option value="USD">미국 USD</option>
        <option value="JPY">일본 JPY</option>
        <option value="EUR">유럽 EUR</option>
        <option value="CNY">중국 CNY</option>
      </select>
    </div>
  );
}

export default Dropdown;
