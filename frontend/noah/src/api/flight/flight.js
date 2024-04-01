import axiosAPI from "../axios";
const commonUrl = "/api/v2";

/* 계획 상세 조회 */
export async function getFlightInfo(planId,
  originLocationCode,
  destinationLocationCode,
  departureDate) {
  try {
    const params = {
      planId: planId,
      originLocationCode: originLocationCode,
      destinationLocationCode: destinationLocationCode,
      departureDate: departureDate,
    };
    const res = await axiosAPI.get(commonUrl + "/flight-offers", { params });
    return res.data;
  } catch (error) {
    throw error;
  }
}
