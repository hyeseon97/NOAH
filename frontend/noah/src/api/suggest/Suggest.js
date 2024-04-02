import axiosAPI from "../axios";
const commonUrl = "/api/v1/Suggest";

export async function getRecommendReviewInfo() {
  try {
    const response = await axiosAPI.get(commonUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function getRecommendReviewInfoNonLogin() {
  try {
    const response = await axiosAPI.get(`${commonUrl}/nonlogin`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function getRecommendReviewList(travelId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/${travelId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
