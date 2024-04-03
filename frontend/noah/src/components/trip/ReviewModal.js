import React, { useState } from "react";
import Modal from "react-modal";
import {updateComment} from "../../api/comment/Comment"

Modal.setAppElement("#root"); // 이 부분은 앱의 최상위 컴포넌트에 한 번만 호출되어야 합니다.

function ReviewModal({ isOpen, onRequestClose, travel }) {
  const [content, setContent] = useState("");

  const handleSubmit = async () => {
    try {
      await updateComment(travel.reviewId, content); 
      onRequestClose();
    } catch (error) {
      console.error(error);
    }
  };
  

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={{
        content: {
          top: "50%",
          left: "50%",
          width: "60vw",
          height: "40vh",
          transform: "translate(-50%, -50%)",
        },
      }}
    >
      <div>{travel.travelTitle}</div>
      <div>{travel.country}</div>
      {new Date(travel.startDate)
        .toLocaleDateString("ko-KR", {
          year: "numeric",
          month: "2-digit",
          day: "2-digit",
        })
        .replace(/\.\s?/g, "-")
        .slice(0, -1)}{" "}
      ~{" "}
      {new Date(travel.endDate)
        .toLocaleDateString("ko-KR", {
          year: "numeric",
          month: "2-digit",
          day: "2-digit",
        })
        .replace(/\.\s?/g, "-")
        .slice(0, -1)}
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        style={{ width: "100%", height: "100px" }}
      />
      <button onClick={handleSubmit}>후기 제출</button>
    </Modal>
  );
}

export default ReviewModal;
