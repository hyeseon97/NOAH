import axiosAPI from "../axios";
const commonUrl = "/api/v1/trade";

/* 해당 여행의 모든 거래 내역 조회 */
export async function getAllTrade(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 거래의 포함 여부를 토글 */
export async function toggleTrade(tradeId) {
  try {
    const response = await axiosAPI.put(commonUrl + `/remove/${tradeId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 숨긴 거래 내역만 조회 */
export async function getHiddenTrade(travelId) {
  try {
    const response = await axiosAPI.post(commonUrl + `/hide/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}
