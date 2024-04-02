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

const labelMedium = {};
const labelSmall = {};

export default function TravelHistory() {
  return (
    <>
      <div style={container}>
        <div style={{ width: "80%" }}>
          <div style={flexContainer}>
            <div>B106 여행가자</div>
            <div>24.03.15 ~ 24.03.19</div>
          </div>
          <div style={flexContainer}>
            <div>일본, 6명</div>
            <div>후기 작성</div>
          </div>
        </div>
      </div>
      <div style={line}></div>
    </>
  );
}
