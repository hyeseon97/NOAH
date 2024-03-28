import axiosAPI from "../axios";
const commonUrl = "/api/v1/detailPlan";

/* 상세계획 조회 */
export async function getDetailPlan(detailPlanId) {
  try {
    const res = axiosAPI.get(commonUrl + `/${detailPlanId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 상세계획 목록 조회 */
export async function getDetailPlanList(object) {
  try {
    const res = axiosAPI.get(commonUrl + `/list`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 상세 계획 수정 */
export async function updateDetailPlan(detailPlanId, object) {
  try {
    const res = axiosAPI.put(commonUrl + `/${detailPlanId}`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 상세 계획 삭제 */
export async function deleteDetailPlan(detailPlanId) {
  try {
    const res = axiosAPI.delete(commonUrl + `/${detailPlanId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
