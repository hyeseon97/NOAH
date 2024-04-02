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

/* QR 결제 승인 */
export async function withdrawByQR(object) {
  try {
    const response = await axiosAPI.post("/api/v1/bank/qr/withdraw", object);
    return response.data;
  } catch (error) {
    throw error;
  }
}
