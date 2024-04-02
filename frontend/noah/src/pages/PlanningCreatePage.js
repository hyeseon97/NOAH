import Header from "../components/common/Header";
import styles from "./PlanningCreatePage.module.css";
import { ReactComponent as Airplane } from "./../assets/Icon/Airplane.svg";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import { ReactComponent as Calender } from "./../assets/Icon/Calender.svg";
import RoundButton from "../components/common/RoundButton";
import { useParams } from "react-router-dom";
import * as React from "react";
import dayjs from "dayjs";
import { DemoContainer, DemoItem } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";

export default function PlanningCreatePage() {
  const { travelId } = useParams();
  return (
    <>
      <Header LeftIcon="Arrow" Title="계획 생성" />
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <DemoContainer components={["MobileDatePicker"]}>
          <DemoItem label="Mobile variant">
            <MobileDatePicker defaultValue={dayjs("2022-04-17")} />
          </DemoItem>
        </DemoContainer>
      </LocalizationProvider>
      <div className={styles.planningCreateContainer}>
        <div className={styles.iconBox}>
          <Airplane className={styles.icon} />
        </div>
        <div className={styles.labelMedium}>여행 국가</div>
        <div className={styles.setBox}>
          <Mark className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            placeholder="장소를 입력하세요"
          ></input>
        </div>
        <div className={styles.labelMedium}>여행 날짜</div>
        <div className={styles.setBox}>
          <Calender className={styles.smallIcon} />
          <input
            className={styles.inputBox}
            type="text"
            placeholder="날짜를 설정하세요"
          ></input>
        </div>
        <div className={styles.buttonBox}>
          <RoundButton className={styles.button} buttonText="새 계획 생성" />
        </div>
      </div>
    </>
  );
}
