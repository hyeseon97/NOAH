import axiosAPI from "../axios";
const commonUrl = "/api/v1/travel";

export async function getMyTravel() {
  try {
    const response = await axiosAPI.get(commonUrl + "/list");
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
