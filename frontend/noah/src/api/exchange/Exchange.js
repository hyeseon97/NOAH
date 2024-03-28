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

/* 환전 */
export async function getExchangeAmount(travelId) {
  try {
    const res = await axiosAPI.put(commonUrl + `/${travelId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
