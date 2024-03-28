import axiosAPI from "../axios";
const commonUrl = "/api/v1/member";

/* 로그인 */
export async function login(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/login", object);
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

// 닉네임 중복검사
export async function checkNickname(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/nickname", object);
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function logout() {
  try {
    const response = await axiosAPI.post(commonUrl + "/logout");
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function emailVerify(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/email", object);
    console.log(response.data);
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
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}
