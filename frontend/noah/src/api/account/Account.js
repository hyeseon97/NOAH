import axiosAPI from "../axios";
const commonUrl = "/api/v1/account";

/* 나의 계좌 조회 */
export async function getAccount() {
  try {
    const response = await axiosAPI.get(commonUrl + "/my");
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 여행에 자동이체 계좌 설정 */
export async function setAutoTransfer(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/auto", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 여행에 자동이체 계좌 해제 */
export async function deleteAutoTransfer(travelId) {
  try {
    const response = await axiosAPI.delete(commonUrl + `/auto/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 내 계좌 조회 + 여행에 해당하는 자동이체 계좌 조회 */
export async function getAutoTransfer(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/auto/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}
