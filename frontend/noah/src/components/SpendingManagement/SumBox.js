import { ReactComponent as WhiteArrow } from "../../assets/Icon/WhiteArrow.svg";

export default function SumBox({ isClicked, title, sum }) {
  const container = {
    width: "88.89vw",
    height: "10vw",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: "600",
    fontSize: "4.45vw",
    lineHeight: "150%",
    color: isClicked ? "black" : "#898989",
    cursor: "pointer",
  };
  const box = {
    minWidth: "5.55vw",
    minHeight: "5.55vw",
    borderRadius: "0.833vw",
    border: "0.277vw solid rgba(137, 137, 137, 0.3)",
  };

  const checkbox = {
    minWidth: "5.55vw",
    minHeight: "5.55vw",
    borderRadius: "0.833vw",
    border: "black solid",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#E74733",
  };

  const left = {
    display: "flex",
    alignItems: "center",
    gap: "5.55vw",
    whiteSpace: "nowrap",
    maxWidth: "60vw",
  };

  const rightText = {
    textAlign: "right",
    whiteSpace: "nowrap", // 줄바꿈 방지
    fontWeight: "500",
  };

  return (
    <>
      <div style={container}>
        <div style={left}>
          {isClicked && (
            <>
              <div style={checkbox}>
                <WhiteArrow style={{ width: "4.44vw", height: "4.44vw" }} />
              </div>
            </>
          )}
          {!isClicked && (
            <>
              <div style={box}></div>
            </>
          )}

          <div style={{ textOverflow: "ellipsis" }}>{title}</div>
        </div>
        <div style={rightText}>
          {new Intl.NumberFormat("ko-KR").format(sum)}원
        </div>
      </div>
    </>
  );
}
