import { ReactComponent as Arrow } from "../../assets/Icon/Arrow.svg";
import { ReactComponent as Cancel } from "../../assets/Icon/Cancel.svg";
import { ReactComponent as Filter } from "../../assets/Icon/Filter.svg";

export default function Header({
  LeftIcon,
  Title,
  RightIcon,
  onClick,
  onRightIconClick,
}) {
  const headerBorderStyle = {
    width: "100vw",
    height: "16.67vw",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  };

  const line = {
    width: "100vw",
    height: "0px",
    border: "0.277vw solid rgba(137, 137, 137, 0.3)",
  };

  const iconStyle = {
    width: "6.67vw",
    height: "6.67vw",
    cursor: "pointer",
  };

  const headingStyle = {
    /* Heading/Large */
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: "700",
    fontSize: "5.92vw",
    lineHeight: "140%",

    color: "#000000",
  };

  const headingSmallStyle = {
    /* Heading/Large */
    fontFamily: "Pretendard",
    fontStyle: "normal",
    fontWeight: "700",
    fontSize: "3.35vw",
    lineHeight: "140%",

    color: "#000000",
  };

  const headerContainer = {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    width: "83.3vw",
  };

  const handleDefaultClick = () => {
    window.history.back();
  };

  const handleLeftIconClick = onClick || handleDefaultClick;

  return (
    <>
      <div style={headerBorderStyle}>
        <div style={headerContainer}>
          <div onClick={handleLeftIconClick}>
            {LeftIcon === "Arrow" && <Arrow style={iconStyle} />}
            {LeftIcon === "Cancel" && <Cancel style={iconStyle} />}
            {LeftIcon === "none" && <div style={iconStyle} />}
          </div>
          <div style={Title.length <= 12 ? headingStyle : headingSmallStyle}>
            {Title}
          </div>
          <div>
            {RightIcon === "Filter" ? (
              <Filter style={iconStyle} onClick={onRightIconClick} />
            ) : (
              <div style={iconStyle} /> // RightIcon이 없을 때 공간 차지
            )}
          </div>
        </div>
      </div>
      <div style={line}></div>
    </>
  );
}
