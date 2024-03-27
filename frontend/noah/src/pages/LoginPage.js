import { login } from "../api/member/member";
import Button from "../components/common/Button";
import Input from "../components/common/Input";
import Logo from "../components/common/Logo";
import styles from "./LoginPage.module.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
  const navigate = useNavigate();
  const [loginFailedMessage, setLoginFailedMessage] = useState(""); // 로그인 실패시 메시지 변경 "아이디와 비밀번호를 다시 확인해주세요."
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  /* 값을 입력함과 동시에 form 데이터 동시에 갱신 */
  function handleChange(e) {
    setFormData((prevFormData) => ({
      ...prevFormData,
      [e.target.name]: e.target.value,
    }));
  }

  //formData 전송
  const handleLoginClick = () => {
    /* 로그인 API 작성 + 유효성 검사 */
    login(formData);
    /* 로그인 실패 시 */
    setLoginFailedMessage("아이디와 비밀번호를 다시 확인해주세요.");

    /* 로그인 성공 시 */
    // 전역 상태 지정 코드

    //navigate("/home");
  };

  const handleSignupPageRedirect = () => {
    navigate("/signup");
  };

  const handleHomePageRedirect = () => {
    navigate("/home");
  };

  return (
    <>
      <div className={styles.Container}>
        <div className={styles.logoContainer}>
          <Logo />
        </div>
        <div className={styles.inputContainer}>
          <Input
            inputType={"text"}
            placeholderText={"이메일"}
            onChange={handleChange}
            value={formData.email}
            name="email"
          ></Input>
          <Input
            inputType={"password"}
            placeholderText={"비밀번호"}
            onChange={handleChange}
            value={formData.password}
            name="password"
          ></Input>
        </div>
        <div onClick={() => handleLoginClick()}>
          <Button buttonText="로그인" warningText={loginFailedMessage} />
        </div>

        <div className={styles.loginFooter}>
          <div
            style={{ cursor: "pointer", width: "15.5vw" }}
            onClick={() => handleSignupPageRedirect()}
          >
            회원가입
          </div>
          <div>|</div>
          <div
            style={{ cursor: "pointer" }}
            onClick={() => handleHomePageRedirect()}
          >
            둘러보기
          </div>
        </div>
      </div>
    </>
  );
}
