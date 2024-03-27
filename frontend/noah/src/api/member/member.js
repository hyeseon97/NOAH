import axiosAPI from "../axios";
const commonUrl = "/api/v1/member/";

export async function login(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "login", object);
    console.log("서ㅇ공");
    return response.data;
  } catch (error) {
    throw error;
  }
}
