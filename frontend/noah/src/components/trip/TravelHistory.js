import { useState } from "react";
import { uplodaImage } from "../../api/image/Image";
import { createReview } from "../../api/review/Review";
import showToast from "../common/Toast";
import ReviewModal from "../trip/ReviewModal";
import { useNavigate } from "react-router-dom";

const container = {
  width: "100vw",
  height: "27.7vw",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  gap: "10vw",
};

const line = {
  width: "100vw",
  height: "1.38vw",
  backgroundColor: "#F6F6F6",
};

const flexContainer = {
  display: "flex",
  justifyContent: "space-between",
};

const labelMedium = {
  fontFamily: "Pretendard",
  fontStyle: "normal",
  fontWeight: 600,
  fontSize: "4.45vw",
  lineHeight: "150%",
  color: "#000000",
};
const labelSmall = {
  fontFamily: "Pretendard",
  fontStyle: "normal",
  fontWeight: 600,
  fontSize: "3.34vw",
  lineHeight: "150%",
  color: "#898989",
};

const review = {
  ...labelSmall,
  textDecoration: "underline",
  marginTop: "5px",
  color: "black",
};

function convertDateFormat(dateStr) {
  const date = new Date(dateStr);
  const year = date.getFullYear() - 2000;
  // getMonth()는 0부터 시작하므로 1을 더해줍니다.
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const day = date.getDate().toString().padStart(2, "0");

  return `${year}.${month}.${day}`;
}

export default function TravelHistory({ travel, fetchTravels }) {
  const [files, setFiles] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  const handleFileChange = (event) => {
    setFiles(event.target.files);
  };

  const handleReviewNav = () => {
    navigate(`/trip/${travel.travelId}/review/${travel.reviewId}`);
  };

  const upload = async () => {
    const formData = new FormData();

    if (!files) {
      showToast("사진을 함께 넣어주세요.");
      return;
    } else {
      formData.append("images", files[0]);
    }
    try {
      const res = await uplodaImage(formData);
      const object = {
        travelId: travel.travelId,
        imageIdList: res.imageIds,
      };
      const response = await createReview(object);
      fetchTravels();
    } catch (error) {
      showToast("계획을 설정하지 않은 여행은 후기를 작성할 수 없습니다.");
      console.log(error);
    }
  };

  const handleReviewClick = () => {
    setIsModalOpen(true);
  };

  return (
    <>
      <div style={container}>
        <div
          style={{
            width: "80%",
            display: "flex",
            flexDirection: "column",
            gap: "1vw",
          }}
        >
          <div style={flexContainer}>
            <div style={labelMedium}>{travel.travelTitle}</div>
            {travel.startDate === null && (
              <div style={labelSmall}>계획 없는 여행</div>
            )}
            {travel.startDate !== null && (
              <div style={labelSmall}>
                {convertDateFormat(travel.startDate)} ~{" "}
                {convertDateFormat(travel.endDate)}
              </div>
            )}
          </div>
          <div style={flexContainer}>
            <div style={labelSmall}>
              {travel.country && <span>{travel.country}, </span>}
              {travel.people}명
            </div>
            {travel.reviewId === null && (
              <div style={review} onClick={upload}>
                후기 작성
                <input
                  type="file"
                  onChange={handleFileChange}
                  multiple
                  style={{ display: "none" }}
                ></input>
              </div>
            )}
            {travel.reviewId !== null && (
              <div>
                <div style={review} onClick={handleReviewNav}>
                  후기 확인
                </div>
                <div style={review} onClick={handleReviewClick}>
                  댓글 입력
                </div>
              </div>
            )}
            <ReviewModal
              isOpen={isModalOpen}
              onRequestClose={() => setIsModalOpen(false)}
              travel={travel}
            />
          </div>
        </div>
      </div>
      <div style={line}></div>
    </>
  );
}
