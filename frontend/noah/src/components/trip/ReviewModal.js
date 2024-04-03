import React, { useState } from "react";
import Modal from "react-modal";
import { updateComment } from "../../api/comment/Comment";
import style from "../trip/ReviewModel.module.css";

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
          width: "63vw",
          height: "45vh",
          transform: "translate(-50%, -50%)",
        },
      }}
    >
      <div className={style.title}>{travel.travelTitle}</div>
      <div className={style.country}>{travel.country}</div>
      <div className={style.date}>
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
      </div>
      <div className={style.textarea}>
        <textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
          style={{ width: "50vw", height: "15vh", padding: "1vw", paddingTop:"1vh" }}
          placeholder="여행 소감을 남겨주세요!"
        />
      </div>
      <button onClick={handleSubmit} className={style.button}>
        코멘트 작성
      </button>
    </Modal>
  );
}

export default ReviewModal;
