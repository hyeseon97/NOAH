import axiosAPI from "../axios";
const commonUrl = "/api/v1/travel";

export async function getTravelInfo(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function createTravel(object) {
  try {
    const response = await axiosAPI.post(commonUrl, object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function travelMemberInvite(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/invite", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function getAllTravel() {
  try {
    const response = await axiosAPI.get(commonUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
}
