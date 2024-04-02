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
        <option value="강준규">강준규</option>
        <option value="여진구">여진구</option>
        <option value="박혜선">박혜선</option>
        <option value="이우진">이우진</option>
      </select>
    </div>
  );
}

export default Dropdown;
