import Header from "../components/common/Header";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getQRImage } from "../api/payment/Payment";
import { useParams } from "react-router-dom";

export default function PaymentPage() {
  const navigate = useNavigate();
  const { travelId } = useParams();
  const [imageUrl, setImageUrl] = useState(null);

  useEffect(() => {
    const getQR = async () => {
      const res = await getQRImage(travelId);
    };

    getQR();
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="결제" />
      {imageUrl !== null && (
        <>
          <div>
            <img src={imageUrl} alt=""></img>
          </div>
        </>
      )}
    </>
  );
}
