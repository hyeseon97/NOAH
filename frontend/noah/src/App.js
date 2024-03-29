import { Outlet } from "react-router-dom";
import "./App.css";
import "./firebase-config";
import { Toaster } from "react-hot-toast";

export default function App() {
  return (
    <>
      <div className="App">
        <Outlet />
      </div>
      <Toaster position="top" />
    </>
  );
}
