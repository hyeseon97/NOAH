import { useEffect, useState } from "react";
import Header from "./../components/common/Header";
import { ReactComponent as SmallPeople } from "./../assets/Icon/SmallPeople.svg";
import { ReactComponent as SmallCalendar } from "./../assets/Icon/SmallCalendar.svg";
import { ReactComponent as SmallBill } from "./../assets/Icon/SmallBill.svg";
import styles from "./ReviewPage.module.css";
import { getRecommendReviewList } from "../api/suggest/Suggest";
import { useNavigate, useParams } from "react-router-dom";
import ClipLoader from "react-spinners/ClipLoader";
import AOS from "aos";
import "aos/dist/aos.css";
export default function ReviewPage() {
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(true);
  const [reviewInfo, setReviewInfo] = useState([]);
  const { travelId } = useParams();

  useEffect(() => {
    AOS.init({
      duration: 500,
    });
  });

  useEffect(() => {
    const fetchReviewInfo = async () => {
      try {
        const res = await getRecommendReviewList(travelId);
        if (res.status === "SUCCESS") {
          setReviewInfo(res.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchReviewInfo();
    setTimeout(() => setIsLoading(false), 1000);
  }, []);
  function calculateDays(startDate, endDate) {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const timeDiff = end - start; // 두 날짜의 차이를 밀리초 단위로 계산
    const daysDiff = timeDiff / (1000 * 60 * 60 * 24); // 밀리초를 일 단위로 변환
    return daysDiff + 1; // 시작일과 종료일을 포함하기 위해 1을 더함
  }
  return (
    <>
      <Header LeftIcon="Arrow" Title="추천 후기" />
      {isLoading && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "80vh",
            }}
          >
            <ClipLoader />
          </div>
        </>
      )}
      {!isLoading && (
        <div className={styles.reviewContainer}>
          {reviewInfo.map((review, index) => (
            <div key={index} data-aos="fade-down">
              <div
                onClick={() => navigate(`${review.id}`)}
                className={styles.reviewBox}
              >
                <img
                  src={review.imageList[0]?.imageUrl}
                  alt="여행지 사진"
                  className={styles.reviewImg}
                />
                <div className={styles.boxRight}>
                  <div className={styles.headingLarge}>{review.country}</div>
                  <div className={styles.boxRightRow}>
                    <SmallPeople className={styles.icon} />
                    <div className={styles.labelSmall}> {review.people} 명</div>
                  </div>
                  <div className={styles.boxRightRow}>
                    <SmallBill className={styles.icon} />
                    <div className={styles.labelSmall}>
                      {" "}
                      {new Intl.NumberFormat("ko-KR").format(review.expense)} 원
                    </div>
                  </div>
                  <div className={styles.boxRightRow}>
                    <SmallCalendar className={styles.icon} />
                    <div className={styles.labelSmall}>
                      {calculateDays(review.startDate, review.endDate)}일
                    </div>
                  </div>
                </div>
              </div>
              <div className={styles.line}></div>
            </div>
          ))}
        </div>
      )}
    </>
  );
}
