import { getAllTravel, getTravelInfo } from "../api/travel/Travel";
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import ClipLoader from "react-spinners/ClipLoader";
import styles from "./TravelHistoryPage.module.css";
import TravelHistory from "../components/trip/TravelHistory";

export default function TravelHistoryPage() {
  const [isLoading, setIsLoading] = useState(true);
  const [travels, setTravels] = useState([]);

  useEffect(() => {
    (async () => {
      try {
        const res = await getAllTravel();
        if (res.status === "SUCCESS") {
          console.log(res.data);
          setTravels(res.data);
        }
      } catch (e) {
      } finally {
        setTimeout(() => setIsLoading(false), 100);
      }
    })();
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="내 여행 기록" />
      {isLoading && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "80vh",
            }}
          >
            <ClipLoader />
          </div>
        </>
      )}
      {!isLoading && travels.length === 0 && (
        <>
          <div className={styles.headingLarge}>조회된 여행이 없습니다.</div>
        </>
      )}
      {!isLoading && travels.length > 0 && <></>}
      {travels.map((travel) => (
        <TravelHistory key={travel.travelId} travel={travel} />
      ))}
    </>
  );
}
