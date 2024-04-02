const container = {
  width: "100vw",
  height: "27.7vw",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
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

export default function TravelHistory() {
  return (
    <>
      <div style={container}>
        <div style={{ width: "80%" }}>
          <div style={flexContainer}>
            <div style={labelMedium}>B106 여행가자</div>
            <div style={labelSmall}>24.03.15 ~ 24.03.19</div>
          </div>
          <div style={flexContainer}>
            <div style={labelSmall}>일본, 6명</div>
            <div style={review}>후기 작성</div>
          </div>
        </div>
      </div>
      <div style={line}></div>
    </>
  );
}
