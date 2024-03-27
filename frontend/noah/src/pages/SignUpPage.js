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
  const [isEmailVerified, setIsEmailVerified] = useState(false); // 이메일 인증 완료한 상태
  const [isAgreed, setIsAgreed] = useState(true);
  const [buttonText, setButtonText] = useState("이메일 인증");
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    code: "",
    nickname: "",
  });

  /* 값을 입력함과 동시에 form 데이터 동시에 갱신 */
  function handleChange(e) {
    setFormData((prevFormData) => ({
      ...prevFormData,
      [e.target.name]: e.target.value,
    }));
  }

  const handleButtonClick = () => {
    if (!isEmailVerifying && !isEmailVerified) {
      setIsEmailVerifying(true);
      setButtonText("인증번호 확인");
      return;
    }

    if (!isEmailVerified) {
      // 인증 확인 시도
      // 실패시
      //setSignUpFailedMessage("인증 번호가 올바르지 않습니다.");

      // 성공시
      setIsEmailVerified(true);
      setIsEmailVerifying(false);
      setSignUpFailedMessage("");
      setButtonText("회원가입");

      return;
    }
    if (!isAgreed) {
      setSignUpFailedMessage("약관 동의가 필요합니다.");
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
          <Input
            inputType={"text"}
            placeholderText={"이메일"}
            onChange={handleChange}
            value={formData.email}
            name="email"
          ></Input>
          {isEmailVerifying && (
            <>
              <Input
                inputType={"text"}
                placeholderText={"인증번호"}
                onChange={handleChange}
                value={formData.code}
                name="code"
              ></Input>
            </>
          )}
          {isEmailVerified && (
            <>
              <Input
                inputType={"password"}
                placeholderText={"비밀번호"}
                onChange={handleChange}
                value={formData.password}
                name="password"
              ></Input>
              <Input
                inputType={"text"}
                placeholderText={"닉네임 (한글 2~8자)"}
                onChange={handleChange}
                value={formData.nickname}
                name="nickname"
              ></Input>
            </>
          )}
        </div>

        <div onClick={() => handleButtonClick()}>
          <Button buttonText={buttonText} warningText={signUpFailedMessage} />
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
