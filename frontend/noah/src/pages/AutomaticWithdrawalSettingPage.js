import Header from "../components/common/Header";
import { useRef, useEffect, useState } from "react";
import { getAllGroupAccount } from "../api/groupaccount/GroupAccount";
import Trip from "../components/trip/Trip";
import styles from "./AutomaticWithdrawalSettingPage.module.css";
import MyAccount from "./../components/common/MyAccount";
import ClipLoader from "react-spinners/ClipLoader";
import {
  deleteAutoTransfer,
  getAccount,
  getAutoTransfer,
  setAutoTransfer,
} from "../api/account/Account";
import showToast from "../components/common/Toast";

export default function AutomaticWithdrawalSettingPage() {
  /* Trip 컴포넌트 스와이프 */
  const containerRef = useRef(null);
  const [startX, setStartX] = useState(0);
  const [startTime, setStartTime] = useState(0); // 스와이프 시작 시간을 저장하기 위한 상태

  const handleSwipeStart = (position) => {
    setStartX(position);
    setStartTime(Date.now()); // 스와이프 시작 시간 저장
  };

  const handleSwipeEnd = (endPosition) => {
    const endTime = Date.now(); // 스와이프가 끝난 시간
    const moveDistance = startX - endPosition;
    const moveTime = endTime - startTime; // 총 이동 시간 계산

    if (Math.abs(moveDistance) >= window.innerWidth * 0.05 || moveTime > 150) {
      // 이동 거리가 충분히 길거나 이동 시간이 150ms 이상인 경우 스와이프로 판단
      if (containerRef.current) {
        const direction = moveDistance > 0 ? 1 : -1;
        containerRef.current.scrollTo({
          left:
            containerRef.current.scrollLeft +
            direction * window.innerWidth * 0.8744,
          behavior: "smooth",
        });
      }
    } else if (
      Math.abs(moveDistance) < window.innerWidth * 0.05 &&
      moveTime < 150
    ) {
      // 이동 거리가 짧고 이동 시간이 150ms 미만인 경우 클릭으로 판단
      // 클릭 이벤트 처리
      // 여기서는 별도의 클릭 이벤트 처리 로직을 실행하지 않음
    }
  };

  // 마우스 이벤트 핸들러
  const handleMouseDown = (e) => handleSwipeStart(e.clientX);
  const handleMouseUp = (e) => handleSwipeEnd(e.clientX);

  // 터치 이벤트 핸들러
  const handleTouchStart = (e) => handleSwipeStart(e.touches[0].clientX);
  const handleTouchEnd = (e) => handleSwipeEnd(e.changedTouches[0].clientX);

  const [seq, setSeq] = useState(0);
  const [selectedTravelId, setSelectedTravelId] = useState(null);
  const [trips, setTrips] = useState([]); // 여행 데이터 저장
  const [isLoading, setIsLoading] = useState(false);
  const [accounts, setAccounts] = useState([]);

  const handleLeftIconClick = () => {
    setSeq(0);
  };

  useEffect(() => {
    setIsLoading(true);
    const fetchGroupAccounts = async () => {
      try {
        const response = await getAllGroupAccount();
        if (response.data === null) setTrips([]);
        else setTrips([]);
        //else setTrips(response.data); // API로부터 받아온 여행 데이터를 상태에 저장
      } catch (error) {
        setTrips([]);
      } finally {
        setIsLoading(false);
      }
    };

    fetchGroupAccounts();
  }, []);

  useEffect(() => {
    if (seq === 0) return;

    (async () => {
      try {
        const res = await getAutoTransfer(selectedTravelId);
        setAccounts(res.data);
      } catch (e) {
      } finally {
        setIsLoading(false);
      }
    })();
  }, [seq]);

  const handleTripClick = (travelId) => {
    setSelectedTravelId(travelId);
    setSeq(1);
  };

  const handleAutomaticClick = async (autoTransfer, accountId) => {
    const res = await deleteAutoTransfer(selectedTravelId);

    if (autoTransfer) {
      showToast("자동이체 계좌 해제 완료");
    }

    if (!autoTransfer) {
      const res = await setAutoTransfer({
        travelId: selectedTravelId,
        accountId: accountId,
        autoActivate: true,
      });

      if (res.status === "SUCCESS") {
        showToast("자동이체 계좌 등록 완료");
      }
    }

    (async () => {
      try {
        const res = await getAutoTransfer(selectedTravelId);
        setAccounts(res.data);
      } catch (e) {
      } finally {
        setIsLoading(false);
      }
    })();
  };

  return (
    <>
      {seq === 0 && (
        <>
          <Header LeftIcon="Arrow" Title="여행 선택" />
          <div
            className={styles.tripContainer}
            ref={containerRef}
            onMouseDown={handleMouseDown}
            onMouseUp={handleMouseUp}
            onMouseLeave={handleMouseUp} // 컨테이너 밖으로 마우스가 나갔을 때
            onTouchStart={handleTouchStart}
            onTouchEnd={handleTouchEnd}
          >
            <div style={{ marginLeft: "5vw" }}></div>
            {isLoading && (
              <>
                <Trip isLoading={true} />
              </>
            )}

            {!isLoading &&
              trips.length !== 0 &&
              trips.map((trip, index) => (
                <Trip
                  key={index}
                  onClick={() => handleTripClick(trip.travelId)}
                  title={trip.title}
                  bankName={trip.bankName}
                  accountNumber={trip.accountNumber}
                  amount={trip.amount}
                  targetAmount={trip.targetAmount}
                  fromHome={false}
                  travelId={trip.travelId}
                />
              ))}
            {!isLoading && trips.length === 0 && (
              <div className={styles.headingLarge}>
                참여 중인 여행이 없습니다.
              </div>
            )}
            <div style={{ marginRight: "5vw" }}></div>
          </div>
        </>
      )}
      {seq === 1 && (
        <>
          <Header
            LeftIcon="Arrow"
            Title="내 계좌"
            onClick={handleLeftIconClick}
          />
          {!isLoading && (
            <>
              {accounts
                .filter((account) => account.type !== "공동계좌") // 공동계좌만 불러옴
                .map((account, index) => (
                  <div
                    onClick={() =>
                      handleAutomaticClick(
                        account.autoTransfer,
                        account.accountId
                      )
                    }
                  >
                    <MyAccount
                      key={account.accountId} // 고유 key 값으로 accountId 사용
                      type={account.bankName} // 조건에 따른 type 결정
                      accountNumber={account.accountNumber}
                      sum={account.amount}
                      //onClick={() => handleAccountClick(account)}
                      from="automatic"
                      autoTransfer={account.autoTransfer}
                    />
                  </div>
                ))}
            </>
          )}
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
        </>
      )}
    </>
  );
}
