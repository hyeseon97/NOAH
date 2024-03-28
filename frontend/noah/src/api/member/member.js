import axiosAPI from "../axios";
const commonUrl = "/api/v1/member/";

export async function login(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "login", object);
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function signup(object) {
  try {
    const response = await axiosAPI.post(commonUrl, object);
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function checkNickname(object) {
  try {
    const response = await axiosAPI.post(commonUrl, object);
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}
