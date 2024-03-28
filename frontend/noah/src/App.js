import { Outlet } from "react-router-dom";
import "./App.css";
import "./firebase-config";
import axios from "axios";
import axiosAPI from "./api/axios";

axiosAPI.defaults.withCredentials = true;

axiosAPI.interceptors.request.use(
  async (config) => {
    let accessToken = localStorage.getItem("accessToken");
    console.log(accessToken);

    if (!accessToken) {
      return config;
    }

    config.headers["Authorization"] = `Bearer ${accessToken}`;
    console.log(config.headers);
    return config;
  },
  (error) => {
    localStorage.clear();
    window.location.href = "/join";
    //return Promise.reject(error);
  }
);

export default function App() {
  return (
    <div className="App">
      <Outlet />
    </div>
  );
}
