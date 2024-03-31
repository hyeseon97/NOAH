import Logo from "../components/common/Logo";
import useUserStore from "../store/useUserStore";
import styles from "./WelcomePage.module.css";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function WelcomePage() {
  const navigate = useNavigate();
  const user = useUserStore((state) => state.user);

  useEffect(() => {
    const timer = setTimeout(() => {
      /* 로그인 상태를 판별하고 로그인 상태라면 /home 으로, 아니라면 /login 으로 리다이렉트 */
      if (user.memberId === "") {
        navigate("/login");
      } else {
        navigate("/home");
      }
    }, 1000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <>
      <div className={styles.logoContainer}>
        <Logo />
      </div>
    </>
  );
}
