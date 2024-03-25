import { Outlet } from "react-router-dom";
import "./App.css";
import "./firebase-config";

export default function App() {
  return (
    <div className="App">
      <Outlet />
    </div>
  );
}
