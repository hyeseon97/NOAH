import Button from "../components/common/Button";
import Input from "../components/common/Input";
import Logo from "../components/common/Logo";
import styles from "./LoginPage.module.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function SignUpPage() {
  const navigate = useNavigate();
  const [signUpFailedMessage, setSignUpFailedMessage] = useState(""); // 로그인 실패시 메시지 변경 "아이디와 비밀번호를 다시 확인해주세요."
  const [isEmailVerifying, setIsEmailVerifying] = useState(false); // 이메일 인증 중인 상태

  const handleButtonClick = () => {
    if (!isEmailVerifying) {
      setIsEmailVerifying(true);
      return;
    }
    /* 회원가입 API 작성 */
    /* 회원가입 실패 시 */
    setSignUpFailedMessage("입력 정보를 다시 확인해주세요.");

    /* 회원가입 성공 시 */
    // 전역 상태 지정 코드
    //navigate("/login");
  };

  const handleLoginPageRedirect = () => {
    navigate("/login");
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
          <Input inputType={"text"} placeholderText={"이메일"}></Input>
          {isEmailVerifying && (
            <>
              <Input inputType={"text"} placeholderText={"인증번호"}></Input>
              <Input
                inputType={"password"}
                placeholderText={"비밀번호"}
              ></Input>
              <Input
                inputType={"text"}
                placeholderText={"닉네임 (한글 2~8자)"}
              ></Input>
            </>
          )}
        </div>
        <div onClick={() => handleButtonClick()}>
          <Button
            buttonText={isEmailVerifying ? "회원가입" : "이메일 인증"}
            warningText={signUpFailedMessage}
          />
        </div>

        <div className={styles.loginFooter}>
          <div
            style={{ cursor: "pointer", width: "15.5vw" }}
            onClick={() => handleLoginPageRedirect()}
          >
            로그인
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
