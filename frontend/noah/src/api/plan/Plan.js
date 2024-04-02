import axiosAPI from "../axios";
const commonUrl = "/api/v1/plan";

/* 계획 수정 */
export async function updatePlan(planId, object) {
  try {
    const res = await axiosAPI.put(commonUrl + `/update/${planId}`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 계획 시작 변경 */
export async function updatePlanStart(planId, object) {
  try {
    const res = await axiosAPI.put(commonUrl + `/change/${planId}`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 계획 작성 */
export async function cretePlan(object) {
  try {
    const res = await axiosAPI.post(commonUrl, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 계획 상세 조회 */
export async function getPlanDetail(planId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/${planId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 계획 삭제 */
export async function deletePlan(planId) {
  try {
    const res = await axiosAPI.delete(commonUrl + `/${planId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 계획 목록 조회 */
export async function getPlanList(object) {
  try {
    const res = await axiosAPI.get(commonUrl + `/list`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
