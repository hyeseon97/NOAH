import axiosAPI from "../axios";
const commonUrl = "/api/v1/notification";

/* 파이어베이스 토큰 저장 */
export async function setFirebaseToken(object) {
  try {
    const response = await axiosAPI.post(commonUrl + "/token", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}

/* 알림 조회 */
export async function getNotification() {
  try {
    const res = await axiosAPI.get(commonUrl);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 여행 초대 알림 거절 */
export async function refuseNotification(notificationId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/refuse/${notificationId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 여행 초대 알림 수락 */
export async function acceptNotification(notificationId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/accept/${notificationId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}

/* 알림 삭제 */
export async function deleteNotification(notificationId) {
  try {
    const res = await axiosAPI.delete(commonUrl + `/${notificationId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
