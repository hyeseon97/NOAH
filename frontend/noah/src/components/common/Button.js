export default function Button({ buttonText, warningText }) {
  const buttonStyle = {
    /* Lable/Large */
    fontFamily: "Pretendard",
    fontSize: "5.9vw",
    fontWeight: "normal",
    lineHeight: "150%",
    textAlign: "center",
    color: "white",
    backgroundColor: "black",
    width: "88vw",
    height: "10vw",
    margin: "0 auto",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    cursor: "pointer",
  };

  const warningStyle = {
    fontFamily: "Pretendard",
    fontStyle: "normal",
    lineHeight: "160%",
    color: "#E11900",
    fontSize: "3.3vw",
    marginLeft: "7vw",
  };

  return (
    <>
      {warningText && <div style={warningStyle}>{warningText}</div>}
      <div style={buttonStyle}>{buttonText}</div>
    </>
  );
}
