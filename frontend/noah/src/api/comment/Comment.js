import axiosAPI from "../axios";
const commonUrl = "/api/v1/comment";

/* 코멘트 선택 조회 */
export async function getComment(commentId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/${commentId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 코멘트 목록 조회 */
export async function getCommentList(reviewId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/list/${reviewId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 코멘트 수정 */
export async function updateComment(commentId) {
  try {
    const res = await axiosAPI.put(commonUrl + `/${commentId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 코멘트 삭제 */
export async function deleteComment(commentId) {
  try {
    const res = await axiosAPI.delete(commonUrl + `/${commentId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
