export default function Input({
  inputType,
  placeholderText,
  value,
  onChange,
  name,
  disabled,
}) {
  const inputStyle = {
    width: "77.78vw",
    height: "10vw",
    /* Label/Medium */
    fontFamily: "Pretendard",
    fontWeight: 600,
    fontSize: "4.44vw",
    lineHeight: "150%",
    color: "#000000",
    marginBottom: "8vw",
    padding: "1vw",
  };

  return (
    <>
      <input
        type={inputType}
        placeholder={placeholderText}
        name={name}
        value={value}
        onChange={onChange}
        style={inputStyle}
        disabled={disabled}
      />
    </>
  );
}
