import Header from "./../components/common/Header";
import styles from "./MyPage.module.css";
import { ReactComponent as History } from "./../assets/Icon/History.svg";
import { ReactComponent as Auto } from "./../assets/Icon/Auto.svg";
import { ReactComponent as Logout } from "./../assets/Icon/Logout.svg";
import { ReactComponent as Account } from "./../assets/Icon/Account.svg";
import { ReactComponent as Noah } from "./../assets/Icon/Noah.svg";
import { logout } from "../api/member/Member";
import { useNavigate } from "react-router-dom";
import useUserStore from "../store/useUserStore";

export default function MyPage() {
  const user = useUserStore((state) => state.user);

  const navigate = useNavigate();

  const handleTripHistoryClick = () => {
    navigate("/travelhistory");
  };

  const handleMyAccountClick = () => {
    navigate("/myaccount");
  };

  const handleAutomaticSettingClick = () => {
    navigate("/automaticwithdrawalsetting");
  };

  const handleLogoutClick = async () => {
    try {
      const res = await logout();
      if (res.status === "SUCCESS") {
        localStorage.clear();
        navigate("/login");
      } else {
        throw new Error();
      }
    } catch (e) {
      navigate("/error");
    }
  };

  return (
    <>
      <Header
        LeftIcon="Cancel"
        Title="마이페이지"
        onClick={navigate("/home")}
      />
      <div
        style={{
          display: "flex",
          alignItems: "center",
          marginTop: "3vw",
          marginBottom: "2vw",
        }}
      >
        <div className={styles.infoContainer}>
          <div className={styles.labelLarge}>{user.nickname}</div>
          <div className={styles.labelSmall}>{user.email}</div>
          <div className={styles.labelSmall}>{user.name}</div>
        </div>
        <Noah style={{ width: "40vw", height: "44vw", marginLeft: "10vw" }} />
      </div>
      <div className={styles.menuContainer} onClick={handleTripHistoryClick}>
        <History className={styles.icon} />
        <div className={styles.labelLarge}>내 여행 기록</div>
      </div>
      <div className={styles.menuContainer} onClick={handleMyAccountClick}>
        <Account className={styles.icon} />
        <div className={styles.labelLarge}>내 계좌</div>
      </div>
      <div
        className={styles.menuContainer}
        onClick={handleAutomaticSettingClick}
      >
        <Auto className={styles.icon} />
        <div className={styles.labelLarge}>자동이체 설정</div>
      </div>
      <div className={styles.menuContainer} onClick={handleLogoutClick}>
        <Logout className={styles.icon} />
        <div className={styles.labelLarge}>로그아웃</div>
      </div>
      <div className={styles.bottom}></div>
    </>
  );
}
