import Header from "./../components/common/Header";
import { useNavigate } from "react-router-dom";

export default function TripPage() {
  const navigate = useNavigate();
  const handleLeftIconClick = () => {
    navigate("/home");
  };
  return (
    <>
      <Header
        LeftIcon="Cancel"
        Title="여행이름"
        onClick={handleLeftIconClick}
      />
    </>
  );
}
