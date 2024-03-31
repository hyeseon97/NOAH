import toast from "react-hot-toast";

export default function showToast(string) {
  toast.remove();

  toast(`${string}`, {
    style: {
      border: "0.277vw solid #E1E1E1",
      backdropFilter: "blur(0.277vw)",
      color: "black",
      zIndex: "100",
      fontFamily: "Pretendard",
      fontStyle: "normal",
      fontWeight: 500,
      fontSize: "2.22vw",
      width: "auto", // 너비를 자동으로 조정
      maxWidth: "50vw", // 최대 너비를 50vw로 설정
      overflow: "hidden", // 내용이 넘치면 숨김
    },
    iconTheme: {
      primary: "black",
    },
    position: "top-center",
  });
}
