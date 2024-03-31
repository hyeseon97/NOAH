import { useNavigate } from "react-router-dom";
import { createGroupAccount } from "../api/groupaccount/GroupAccount";
import { createTravel } from "../api/travel/Travel";
import Header from "../components/common/Header";
import { ReactComponent as Airplane } from "./../assets/Icon/Airplane.svg";
import { ReactComponent as Bank } from "./../assets/Icon/Bank.svg";
import styles from "./TripCreatePage.module.css";
import { useState } from "react";
import showToast from "./../components/common/Toast";

export default function TripCreatePage() {
  const navigate = useNavigate();
  const [seq, setSeq] = useState(0);
  const [tripName, setTripName] = useState("");
  const [selectedBank, setSelectedBank] = useState("한국은행");

  const handleBankChange = (e) => {
    setSelectedBank(e.target.value);
  };

  const handleTripNameChange = (e) => {
    const value = e.target.value;
    if (value.length > 20) {
      showToast("최대 20자 까지 가능합니다.");
      return;
    }

    setTripName(value);
  };
  const handleLeftIconClick = () => {
    setSeq(0);
  };

  const handleCreate = async () => {
    //여행 생성 후 모임 통장 생성
    try {
      const res = await createTravel({ title: tripName });
      const travelID = res.data;

      const bankRes = await createGroupAccount({
        type: selectedBank,
        travelId: travelID,
      });
      console.log(bankRes);
      showToast("여행이 생성되었습니다.");
      navigate(`/trip/${travelID}`);
    } catch (e) {
      console.log(e);
    }
    //createGroupAccount;
  };

  return (
    <>
      {seq === 0 && (
        <>
          <Header LeftIcon="Cancel" Title="여행 생성" />
          <Airplane className={styles.iconStyle} />

          <div className={styles.inputBorder}>
            {tripName !== "" && <div className={styles.label}>여행 이름</div>}
            {tripName === "" && <div className={styles.label}></div>}
            <div style={{ display: "flex" }}>
              <input
                placeholder="여행 이름을 입력하세요"
                className={styles.input}
                value={tripName}
                onChange={handleTripNameChange}
              ></input>
              <div style={{ height: "100%", width: "4.44vw" }}></div>
            </div>
          </div>

          {tripName !== "" && (
            <>
              <div className={styles.button} onClick={() => setSeq(1)}>
                다음
              </div>
            </>
          )}
        </>
      )}

      {seq === 1 && (
        <>
          <Header
            onClick={handleLeftIconClick}
            LeftIcon="Arrow"
            Title="모임통장 생성"
          />
          <Bank className={styles.iconStyle} />
          <div className={styles.inputBorder}>
            <div className={styles.label}>은행 선택</div>

            <div style={{ display: "flex" }}>
              <div>
                <select
                  className={styles.dropdownStyle}
                  value={selectedBank}
                  onChange={handleBankChange}
                >
                  <option value="한국은행">한국은행</option>
                  <option value="기업은행">기업은행</option>
                  <option value="산업은행">산업은행</option>
                  <option value="국민은행">국민은행</option>
                </select>
              </div>
              <div style={{ height: "100%", width: "4.44vw" }}></div>
            </div>
          </div>
          <div className={styles.button} onClick={handleCreate}>
            생성
          </div>
        </>
      )}
    </>
  );
}
