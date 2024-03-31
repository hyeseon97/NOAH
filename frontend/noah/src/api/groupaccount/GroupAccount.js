import axiosAPI from "../axios";

const commonUrl = "api/v1/groupaccount";

/* 사용자가 속해있는 모임통장 전체 조회 */
export async function getAllGroupAccount() {
  try {
    const response = await axiosAPI.get(commonUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 모임통장 내용 수정 */
export async function updateGroupAccountInfo(object) {
  try {
    const response = await axiosAPI.put(commonUrl, object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 모임통장 생성 */
export async function createGroupAccount(object) {
  try {
    const response = await axiosAPI.post(commonUrl, object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 모임통장에 입금 */
export async function depositGroupAccount(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/deposit", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 모임 통장 단건 조회 */
export async function getGroupAccount(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 모임통장 멤버별 필수 납입금 조회 */
export async function getGroupAccountTotalDue(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/totalDue/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 모임통장에 속해있는 멤버, 납입금 조회 */
export async function getGroupAccountMemberAndTotalDue(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/member/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}
