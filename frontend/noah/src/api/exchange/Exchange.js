import axiosAPI from "../axios";
const commonUrl = "/api/v1/exchange";

/* 환전 */
export async function exchange(object) {
  try {
    const res = await axiosAPI.post(commonUrl, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 환전금액 조회 */
export async function getExchangeAmount(travelId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/${travelId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 환율 조회 */
export async function getExchangeRate() {
  try {
    const res = await axiosAPI.get(commonUrl + `/rateinfo`);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 환율 알림 설정 */
export async function setNotification(object) {
  try {
    const res = await axiosAPI.put(commonUrl + `/rate`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
