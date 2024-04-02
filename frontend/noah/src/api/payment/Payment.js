import axiosAPI from "../axios";
const commonUrl = "/api/v1/qrcode";

/* QR 이미지 요청 */
export async function getQRImage(travelId) {
  try {
    const response = await axiosAPI.get(commonUrl + `/image/${travelId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}
