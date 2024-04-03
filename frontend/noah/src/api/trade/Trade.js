import axiosAPI from "../axios";
import qs from "qs";

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
    const response = await axiosAPI.get(commonUrl + `/hide/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function changeTrade(object) {
  try {
    const response = await axiosAPI.put(commonUrl, object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function getTradeListByMemberAndConsumeType(
  travelId,
  memberIds,
  consumeTypes
) {
  const response = await axiosAPI.get(commonUrl + `/classify/${travelId}`, {
    params: {
      memberIds,
      consumeTypes,
    },
    paramsSerializer: (params) => {
      return qs.stringify(params, { arrayFormat: "repeat" });
    },
  });

  return response.data;
}
