import React, { useState } from "react";

function EditModal({ plan, onSubmit, onClose }) {
  const [updatedPlan, setUpdatedPlan] = useState({
    planId: plan.planId,
    start_date: plan.startDate.split("T")[0],
    end_date: plan.endDate.split("T")[0],
    country: plan.country,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedPlan((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // 폼 제출 기본 동작 방지
    onSubmit(updatedPlan); // 상위 컴포넌트의 onSubmit 함수를 호출하여 변경된 데이터 전달
  };

  return (
    <div className="modal-backdrop">
      <div className="modal-content">
        <form onSubmit={handleSubmit}>
          <label htmlFor="country">제목:</label>
          <input
            id="country"
            name="country"
            value={updatedPlan.country}
            onChange={handleChange}
          />
          <br />
          <label htmlFor="start_date">시작일:</label>
          <input
            id="start_date"
            name="start_date"
            value={updatedPlan.start_date}
            onChange={handleChange}
          />
          <br />
          <label htmlFor="end_date">마지막 날:</label>
          <input
            id="end_date"
            name="end_date"
            value={updatedPlan.end_date}
            onChange={handleChange}
          />
          {/* "닫기" 버튼은 기존대로 onClose 함수를 호출 */}
          <button type="button" onClick={onClose}>닫기</button>
          {/* "수정" 버튼은 form의 submit 이벤트를 통해 handleSubmit 함수를 호출 */}
          <button type="submit">수정</button>
        </form>
      </div>
    </div>
  );
}

export default EditModal;
