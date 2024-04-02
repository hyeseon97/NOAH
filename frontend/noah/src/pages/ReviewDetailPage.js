import { useParams } from "react-router-dom";
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import { getReview } from "../api/review/Review";
import styles from "./ReviewDetailPage.module.css";
import { ReactComponent as Comment } from "./../assets/Icon/Comment.svg";
import { ReactComponent as User } from "./../assets/Icon/User.svg";

export default function ReviewDetailPage() {
  const { reviewId } = useParams();
  const [reviewInfo, setReviewInfo] = useState([]);
  const [comment, setComment] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [imgList, setImgList] = useState([]);
  const [expandedImg, setExpandedImg] = useState(null); // 확대된 이미지 상태 관리

  const handleImageClick = (imgUrl) => {
    setExpandedImg(imgUrl);
  };

  function calculateDays(startDate, endDate) {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const timeDiff = end - start; // 두 날짜의 차이를 밀리초 단위로 계산
    const daysDiff = timeDiff / (1000 * 60 * 60 * 24); // 밀리초를 일 단위로 변환
    return daysDiff + 1; // 시작일과 종료일을 포함하기 위해 1을 더함
  }

  function formatDate(isoString) {
    const date = new Date(isoString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0"); // 월은 0부터 시작하므로 1을 더해주고, 2자리로 맞춤
    const day = date.getDate().toString().padStart(2, "0"); // 일자를 2자리로 맞춤

    return `${year}. ${month}. ${day}`;
  }
  function formatExpense(expense) {
    // 총 경비를 만 단위로 변환
    const amountInTenThousands = expense / 10000;
    // 소수점 아래 두 자리까지 포맷팅하고, 필요 없는 소수점을 제거하기 위해 Number로 변환
    const formattedAmount = Number(amountInTenThousands.toFixed(0)) + 1;
    return `${formattedAmount} 만원`;
  }

  useEffect(() => {
    const fetchReviewInfo = async () => {
      try {
        const res = await getReview(reviewId);
        if (res.status === "SUCCESS") {
          setReviewInfo(res.data);
          const startDateString = String(res.data.start_date);
          setStartDate(formatDate(startDateString));
          const endDateString = String(res.data.end_date);
          setEndDate(formatDate(endDateString));
          setImgList(res.data.imageList); // imageList 상태 업데이트
          setComment(res.data.commentList);
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
      <div className={styles.reviewDetailContainer}>
        <div className={styles.containerHead}>
          <div className={styles.title}>
            <div className={styles.headingLarge}>{reviewInfo.country}</div>
            <div className={styles.labelSmallGrey}>
              {startDate}~{endDate}
            </div>
          </div>
          <div className={styles.containerContents}>
            <div className={styles.contentsRow}>
              <div className={styles.labelSmall}>여행기간</div>
              <div className={styles.labelMedium}>
                {calculateDays(reviewInfo.start_date, reviewInfo.end_date)} 일
              </div>
            </div>
            <div className={styles.contentsRow}>
              <div className={styles.labelSmall}>인원 수</div>
              <div className={styles.labelMedium}> {reviewInfo.people} 명</div>
            </div>
            <div className={styles.contentsRow}>
              <div className={styles.labelSmall}>총 경비</div>
              <div className={styles.labelMedium}>
                {formatExpense(reviewInfo.expense)}
              </div>
            </div>
          </div>
        </div>
        <div className={styles.imgBox}>
          {imgList.length === 1 ? (
            // 이미지가 하나일 경우
            <img
              src={imgList[0].url}
              alt="Review"
              className={styles.singleImage} // 새로운 스타일 적용
              onClick={() => handleImageClick(imgList[0].url)}
            />
          ) : (
            // 이미지가 여러 개일 경우
            imgList.map((img, index) => (
              <img
                key={index}
                src={img.url}
                alt={`Review ${index}`}
                className={styles.reviewImage}
                onClick={() => handleImageClick(img.url)}
              />
            ))
          )}
        </div>
        {expandedImg && (
          <div
            className={styles.expandedImgModal}
            onClick={() => setExpandedImg(null)} // 모달 바깥을 클릭하면 닫힘
          >
            <img src={expandedImg} className={styles.expandedImg} />
          </div>
        )}
        <div className={styles.commentContainer}>
          <div className={styles.commentHead}>
            <Comment />
            <div className={styles.labelMedium}>여행자들의 한줄평</div>
          </div>
          <div className={styles.line}></div>
          <div>
            {comment.map((comment, index) => (
              <div className={styles.commentBox}>
                <User />
                <div className={styles.labelSmall}>{comment?.content}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
