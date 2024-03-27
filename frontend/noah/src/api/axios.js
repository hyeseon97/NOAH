import axios from "axios";

const axiosAPI = axios.create({ baseURL: process.env.REACT_APP_URL });

export default axiosAPI;
