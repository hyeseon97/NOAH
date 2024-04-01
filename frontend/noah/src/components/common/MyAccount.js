export default function MyAccount({
  type,
  accountNumber,
  sum,
  onClick,
  from = "transfer",
  autoTransfer,
}) {
  const typeMapping = {
    한국은행: "한국",
    기업은행: "기업",
    산업은행: "산업",
    국민은행: "국민",
  };

  const myAccountStyle = {
    width: "100vw",
    height: "27.77vw",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    paddingLeft: "8.88vw",
    paddingRight: "6.67vw",
    cursor: "pointer",
  };

  const labelSmall = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 600,
    fontSize: "3.35vw",
    lineHeight: "150%",
    color: "#000000",
  };
  const labelMedium = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 600,
    fontSize: "4.45vw",
    lineHeight: "150%",
    color: "#000000",
  };

  const paragraphSmall = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 500,
    fontSize: "3.33vw",
    lineHeight: "160%",
    color: "#898989",
  };

  const labelLarge = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: 600,
    fontSize: "5.92vw",
    lineHeight: "150%",
    color: "#000000",
  };

  const line = {
    width: "100vw",
    height: "1.39vw",
    background: "#f6f6f6",
  };

  return (
    <>
      <div style={myAccountStyle} onClick={onClick}>
        <div>
          <div style={paragraphSmall}>
            {typeMapping[type]} {accountNumber}
          </div>
          <div style={labelLarge}>
            {new Intl.NumberFormat("ko-KR").format(sum)} 원
          </div>
        </div>
        {from === "transfer" && <div style={labelMedium}>선택</div>}
        {from === "automatic" && !autoTransfer && (
          <div style={labelMedium}>선택</div>
        )}
        {from === "automatic" && autoTransfer && (
          <div style={{ ...labelMedium, color: "#0075ff" }}>자동이체 계좌</div>
        )}
      </div>
      <div style={line}></div>
    </>
  );
}
