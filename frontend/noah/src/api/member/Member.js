import axiosAPI from "../axios";
const commonUrl = "/api/v1/member";

/* 로그인 */
export async function login(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/login", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function signup(object) {
  try {
    const response = await axiosAPI.post(commonUrl, object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

// 닉네임 중복검사
export async function checkNickname(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/nickname", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function logout() {
  try {
    const response = await axiosAPI.post(commonUrl + "/logout");
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function emailVerify(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/email", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function checkEmailCode(object) {
  try {
    const response = await axiosAPI.post(
      commonUrl + "/email/verification",
      object
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}
