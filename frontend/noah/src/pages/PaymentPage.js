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
      setImageUrl(`data:image/png;base64,${res}`);
    };

    getQR();
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="결제" />
      {imageUrl !== null && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              width: "100vw",
              height: "80vh",
            }}
          >
            <img
              src={imageUrl}
              alt=""
              style={{ width: "60vw", height: "60vw" }}
            ></img>
          </div>
        </>
      )}
    </>
  );
}
