import React, { useState } from "react";
import GoogleMapSearch from "./GoogleMapSearch"; // GoogleMapSearch 컴포넌트를 임포트
import PlaneSearchPage from "./PlaneSearchPage"; // PlaneSearchPage 컴포넌트를 임포트
import Header from "../components/common/Header";

const PlanningTestPage = () => {
  const [activeTab, setActiveTab] = useState("place");

  const placeOrTicketStyle = {
    height: "8vh",
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-evenly",
    marginLeft: "20px",
    marginRight: "20px",
    alignItems: "center",
  };

  return (
    <div>
      <Header LeftIcon="Arrow" Title="검색" />
      <div style={placeOrTicketStyle}>
        <div
          style={{ fontWeight: activeTab === "place" ? "bold" : "normal" }}
          onClick={() => setActiveTab("place")}
        >
          장소
        </div>
        <div
          style={{ fontWeight: activeTab === "ticket" ? "bold" : "normal" }}
          onClick={() => setActiveTab("ticket")}
        >
          항공권
        </div>
      </div>
      {activeTab === "place" && <GoogleMapSearch />}
      {activeTab === "ticket" && <PlaneSearchPage />}
    </div>
  );
};

export default PlanningTestPage;
