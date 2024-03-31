import React, { useState } from "react";

function Dropdown({ selected, setSelected }) {
  const handleChange = (event) => {
    setSelected(event.target.value); // 선택된 옵션의 value로 상태 업데이트
  };

  const dropdownStyle = {
    width: "22.22vw",
    height: "5.55vw",
    border: "0.277vw solid #E1E1E1",
    backdropFilter: "blur(0.277vw)",
    borderRadius: "4vw",
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
      <select value={selected} onChange={handleChange} style={dropdownStyle}>
        <option value="공통">공통</option>
        <option value="식비">식비</option>
        <option value="숙박">숙박</option>
        <option value="항공/교통">항공/교통</option>
        <option value="환전">환전</option>
        <option value="쇼핑">쇼핑</option>
        <option value="기타">기타</option>
      </select>
    </div>
  );
}

export default Dropdown;
