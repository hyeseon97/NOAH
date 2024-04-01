import axiosAPI from "../axios";
const commonUrl = "/api/v1/ticket";

/* 티켓 상세 조회 */
export async function getTicketDetail(ticketId) {
  try {
    const res = await axiosAPI.get(commonUrl + `/${ticketId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 티켓 수정 */
export async function updateTicket(ticketId, object) {
  try {
    const res = await axiosAPI.put(commonUrl + `/${ticketId}`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 티켓 삭제 */
export async function deleteTicket(ticketId) {
  try {
    const res = await axiosAPI.delete(commonUrl + `/${ticketId}`);
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 티켓 생성 */
export async function createTicket(object, travelId) {
  const urlWithQueryParam = `${commonUrl}?travelId=${travelId}`;
  try {
    const res = await axiosAPI.post(urlWithQueryParam, object);
    console.log(res)
    return res.data;
  } catch (error) {
    throw error;
  }
}
/* 티켓 목록 조회 */
export async function getTicketList(object) {
  try {
    const res = await axiosAPI.put(commonUrl + `/list`, object);
    return res.data;
  } catch (error) {
    throw error;
  }
}
