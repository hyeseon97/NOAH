import { useState } from "react";
import { uplodaImage } from "../../api/image/Image";
import { createReview } from "../../api/review/Review";

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

export default function TravelHistory({ travel }) {
  const [files, setFiles] = useState(null);

  const handleFileChange = (event) => {
    setFiles(event.target.files);
  };

  const upload = async () => {
    const formData = new FormData();
    formData.append("images", files[0]);
    const res = await uplodaImage(formData);
    console.log(res);

    const handleCreateReview = async () => {
      const object = {
        travelId: travel.travelId,
        imageIdList: res.imageIds,
      };
      try {
        const response = await createReview(object);
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    handleCreateReview();
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
            <input type="file" onChange={handleFileChange} multiple></input>
            <div style={labelSmall}>
              {travel.country && <span>{travel.country}, </span>}
              {travel.people}명
            </div>
            {travel.planId === null && (
              <div style={review} onClick={upload}>
                후기 작성
              </div>
            )}
            {travel.planId !== null && <div style={review}>후기 확인</div>}
          </div>
        </div>
      </div>
      <div style={line}></div>
    </>
  );
}
