import { Outlet } from "react-router-dom";
import React from "react";
import "./App.css";

export default function App() {
  return (
    <div className="App">
      <Outlet />
    </div>
  );
}
