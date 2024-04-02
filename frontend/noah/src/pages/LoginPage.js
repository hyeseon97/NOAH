import { login } from "../api/member/Member";
import Button from "../components/common/Button";
import Input from "../components/common/Input";
import Logo from "../components/common/Logo";
import styles from "./LoginPage.module.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import useUserStore from "../store/useUserStore";

export default function LoginPage() {
  const navigate = useNavigate();
  const { setUser } = useUserStore();
  const [loginFailedMessage, setLoginFailedMessage] = useState(""); // 로그인 실패시 메시지 변경 "아이디와 비밀번호를 다시 확인해주세요."
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const regex = {
    email: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
    password: /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d!@$%^&]{3,20}$/,
  };

  /* 값을 입력함과 동시에 form 데이터 동시에 갱신 */
  function handleChange(e) {
    setFormData((prevFormData) => ({
      ...prevFormData,
      [e.target.name]: e.target.value,
    }));
  }

  //formData 전송
  const handleLoginClick = async () => {
    if (!regex.password.test(formData.password)) {
      setLoginFailedMessage(
        "비밀번호는 최소 하나의 영소문자, 숫자를 포함한 3~20자 입니다."
      );
      return;
    }

    const firebaseToken = localStorage.getItem("firebaseToken");
    console.log(firebaseToken);

    /* 로그인 API 작성 + 유효성 검사 */
    try {
      const res = await login({ ...formData, firebaseToken: firebaseToken });
      if (res.status === "SUCCESS") {
        localStorage.setItem("accessToken", res.data.token);
        await setUser({
          email: res.data.memberInfo.email,
          memberId: res.data.memberInfo.memberId,
          nickname: res.data.memberInfo.nickname,
          name: res.data.memberInfo.name,
        });
        navigate("/home");
      } else {
        setLoginFailedMessage("입력 정보를 확인해주세요.");
      }
    } catch (e) {
      navigate("/error");
    }
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
