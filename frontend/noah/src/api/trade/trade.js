import axiosAPI from "../axios";
const commonUrl = "/api/v1/trade";

/* 거래내역 수정 분류 */
export async function classifyTrade(tradeId, object) {
  try {
    const response = await axiosAPI.put(commonUrl + `/${tradeId}`, object);
    console.log("성공");
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 거래내역 숨기기 */
export async function removeTrade(tradeId) {
  try {
    const response = await axiosAPI.put(commonUrl + `/remove/${tradeId}`);
    console.log("성공");
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 거래내역 전체조회*/
export async function getTradeList(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/${travelId}`);
    console.log("성공");
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 숨김된 거래내역 전체조회*/
export async function getHideTradeList(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/hide/${travelId}`);
    console.log("성공");
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 거래내역 분류 전체조회*/
export async function getTradeListByClassifying(
  travelId,
  memberIds,
  consumeTypes
) {
  try {
    const response = await axiosAPI.get(
      commonUrl + `/classify/${travelId}`,
      memberIds,
      consumeTypes
    );
    console.log("성공");
    return response.data;
  } catch (error) {
    throw error;
  }
}
