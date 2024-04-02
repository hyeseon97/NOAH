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
export async function getDetailPlanList(planId) {
  try {
    console.log(commonUrl + `/${planId}`);
    const res = await axiosAPI.get(commonUrl + `/${planId}`); // await 키워드 추가
    console.log(res.data);
    return res.data;
  } catch (error) {
    console.error(error); // 오류 출력 변경
    throw error;
  }
}

/* 상세계획 추가 */
export async function createDetailPlan(planId, object) {
  const urlWithQueryParam = `${commonUrl}?planId=${planId}`;
  try {
    console.log(object);
    console.log(planId + "test");
    const res = await axiosAPI.post(urlWithQueryParam, object, {
      // headers: {
      //   'Content-Type': 'application/json',
      // }
    });
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
