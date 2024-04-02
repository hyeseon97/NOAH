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
      console.log(res);
    };

    getQR();
    setImageUrl(`http://localhost:8080/api/v1/qrcode/image/${travelId}`);
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="결제" />
      <div>{imageUrl}</div>
      {imageUrl !== null && (
        <>
          <div>
            <a href={imageUrl}>QR 코드</a>
          </div>
        </>
      )}
    </>
  );
}
