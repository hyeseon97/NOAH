import Header from "../components/common/Header";
import { useNavigate } from "react-router-dom";

export default function PaymentPage() {
  const navigate = useNavigate();

  return (
    <>
      <Header LeftIcon="Arrow" Title="결제" />
      <div onClick={() => navigate("/market")}>마켓페이지로 이동</div>
    </>
  );
}
