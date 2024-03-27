import Logo from "../components/common/Logo";
import styles from "./WelcomePage.module.css";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function WelcomePage() {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      /* 로그인 상태를 판별하고 로그인 상태라면 /home 으로, 아니라면 /login 으로 리다이렉트 */
      navigate("/login");
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
