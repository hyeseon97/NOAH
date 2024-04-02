import { useParams } from "react-router-dom";
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import { getReview } from "../api/review/Review";

export default function ReviewDetailPage() {
  const { reviewId } = useParams();
  const [reviewInfo, setReviewInfo] = useState([]);
  useEffect(() => {
    const fetchReviewInfo = async () => {
      try {
        const res = await getReview(reviewId);
        if (res.status === "SUCCESS") {
          console.log(res);
          setReviewInfo(res.data);
        } else {
          console.log(res);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchReviewInfo();
  }, []);
  return (
    <>
      <Header LeftIcon="Arrow" Title="여행 후기" />
    </>
  );
}
