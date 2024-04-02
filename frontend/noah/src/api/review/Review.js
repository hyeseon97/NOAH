import axiosAPI from "../axios";
const commonUrl = "/api/v1/review";

/* 리뷰 선택 조회 */
export async function getReview(reviewId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/${reviewId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 리뷰 수정 */
export async function updateReview(reviewId, object) {
  try {
    const res = await axiosAPI.put(commonUrl + `/${reviewId}`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 리뷰 삭제 */
export async function deleteReview(reviewId) {
  try {
    const res = await axiosAPI.delete(commonUrl + `/${reviewId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 리뷰 생성 */
export async function createReview(object) {
  try {
    const res = await axiosAPI.post(commonUrl, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 리뷰 목록 조회 */
export async function getReviewList() {
  try {
    const res = await axiosAPI.get(commonUrl + `/list`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
