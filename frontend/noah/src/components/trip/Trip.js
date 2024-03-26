import { useNavigate } from "react-router-dom";
import { ReactComponent as Plus } from "../../assets/Icon/Plus.svg";

const borderStyle = {
  border: "0.277vw solid #E1E1E1",
  backdropFilter: "blur(0.277vw)",
  borderRadius: "2.77vw",
  minWidth: "83vw",
  height: "50vw",
  cursor: "pointer",
};

const lastTripStyle = {
  ...borderStyle,
  flexDirection: "column",
  justifyContent: "center", // 전체 컨텐츠를 세로 중앙 정렬
  alignItems: "center", // 전체 컨텐츠를 가로 중앙 정렬
  textAlign: "center", // 텍스트를 중앙 정렬

  /* Paragraph/Small */
  fontFamily: "Pretendard",
  fontStyle: "normal",
  fontWeight: 600,
  fontSize: "3.33vw",
  lineHeight: "160%",
  color: "black",
};

const iconStyle = {
  marginTop: "22.2vw",
  marginBottom: "8.33vw",
  width: "6.67vw",
  height: "6.67vw",
};

export default function Trip({ onClick, isLast = false }) {
  const navigate = useNavigate();

  return (
    <>
      {isLast ? (
        <div style={lastTripStyle} onClick={() => navigate("/tripcreate")}>
          <Plus style={iconStyle} />
          <div>여행 계획을 세우고, 자금을 모아보세요</div>
        </div>
      ) : (
        <div style={borderStyle} onClick={onClick}></div>
      )}
    </>
  );
}
