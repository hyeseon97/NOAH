import Logo from "../components/common/Logo";
import styles from "./WelcomePage.module.css";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function WelcomePage() {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/join");
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
