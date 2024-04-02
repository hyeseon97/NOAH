import { useNavigate } from "react-router-dom";
import { ReactComponent as Plus } from "../../assets/Icon/Plus.svg";
import ClipLoader from "react-spinners/ClipLoader";
import showToast from "../common/Toast";

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

const labelMedium = {
  fontFamily: "Pretendard",
  fontStyle: "normal",
  fontWeight: "600",
  fontSize: "4.44vw",
  lineHeight: "150%",
  textAlign: "center",
  color: "#000000",
};

const labelXL = {
  fontFamily: "Pretendard",
  fontStyle: "normal",
  fontWeight: "600",
  fontSize: "7.90vw",
  lineHeight: "150%",
  textAlign: "center",
  color: "#000000",
};

const paragraphSmall = {
  fontFamily: "Pretendard",
  fontStyle: "normal",
  fontWeight: 500,
  fontSize: "3.33vw",
  lineHeight: "160%",
  color: "black",
  textAlign: "center",
};

const loadingStyle = {
  ...borderStyle,
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
};

export default function Trip({
  onClick,
  isLast = false,
  fromHome = false,
  groupAccountId,
  travelId,
  title,
  bankName,
  accountNumber,
  amount,
  targetAmount,
  isLoading = false,
}) {
  const navigate = useNavigate();
  const handleAccountClick = (e) => {
    e.stopPropagation();
    navigate(`/transfer/${travelId}`, {
      state: {
        title,
        bankName,
        accountNumber,
      },
    });
  };
  const accountStyle = fromHome
    ? {
        textDecoration: "underline",
        cursor: "pointer",
        color: "#898989",
      }
    : {};

  if (isLoading) {
    return (
      <>
        <div style={loadingStyle}>
          <ClipLoader />
        </div>
      </>
    );
  }

  return (
    <>
      {isLast ? (
        <div
          style={lastTripStyle}
          onClick={() => {
            if (localStorage.getItem("accessToken") === null) {
              showToast("로그인 후 이용해보세요.");
              navigate("/login");
              return;
            }
            navigate("/tripcreate");
          }}
        >
          <Plus style={iconStyle} />
          <div>여행 계획을 세우고, 자금을 모아보세요</div>
        </div>
      ) : (
        <div style={borderStyle} onClick={onClick}>
          <div style={{ ...labelMedium, marginTop: "3.33vw" }}>{title}</div>
          {fromHome && (
            <div
              style={{ ...paragraphSmall, ...accountStyle }}
              onClick={handleAccountClick}
            >
              {bankName} {accountNumber} 입금
            </div>
          )}
          {!fromHome && (
            <div style={{ ...paragraphSmall, color: "#898989" }}>
              {bankName} {accountNumber}
            </div>
          )}
          <div style={{ ...labelXL, marginTop: "5vw", marginBottom: "6vw" }}>
            {new Intl.NumberFormat("ko-KR").format(amount)}원
          </div>
          <div style={paragraphSmall}>
            목표금액: {new Intl.NumberFormat("ko-KR").format(targetAmount)}원
          </div>
        </div>
      )}
    </>
  );
}
