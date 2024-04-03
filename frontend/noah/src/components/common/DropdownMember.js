import React, { useState } from "react";

function DropdownMember({ selected, setSelected, people }) {
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
        {people.map((person, index) => (
          <option key={index} value={person.name}>
            {person.name}
          </option>
        ))}
      </select>
    </div>
  );
}

export default DropdownMember;
