import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Main from "./Main";
import * as serviceWorkerRegistration from "./serviceWorkerRegistration";
import reportWebVitals from "./reportWebVitals";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Main></Main>
  </React.StrictMode>
);

serviceWorkerRegistration.register();
reportWebVitals();
