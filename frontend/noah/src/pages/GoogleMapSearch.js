import React, { useState, useRef, useEffect } from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Header from "../components/common/Header";
import { ReactComponent as Mark } from "./../assets/Icon/Mark.svg";
import styles from "./GoogleMapSearch.module.css";

import Rating from "react-rating";
import { FaStar } from "react-icons/fa";
import { useParams } from "react-router-dom";
import { useLocation } from "react-router-dom";

import {
  getDetailPlan,
  getDetailPlanList,
  updateDetailPlan,
  deleteDetailPlan,
  createDetailPlan,
} from "../api/detailplan/DetailPlan";

// const containerStyle = {
//   width: "100vw",
//   height: "65vh",
//   backgroundColor: "transparent",
// };

// const listSearch = {
//   // backgroundColor: "coral",
//   // margin: "10px",
//   display: "flex",
//   // justifyContent: "center",
//   // alignItems: "center",
//   // textAlign: "center",
//   width: "90vw",
//   marginBottom: "20px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
//   margin: "17px",
//   border: "1px solid grey",
// };

// const buttonStyle = {
//   width: "60vw",
//   height: "8vh",
//   border: "none",
//   marginLeft: "20px",
// };

// const listResult = {
//   // backgroundColor: "pink",
//   display: "flex",
//   flexDirection: "column",
//   height: "80vh",
//   margin: "10px",
// };

// const placeOrTicket = {
//   height: "8vh",
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "space-evenly",
//   marginLeft: "20px",
//   marginRight: "20px",
//   alignItems: "center",
// };

// const searchPlace = {
//   height: "8vh",
//   display: "flex",
//   flexDirection: "row",
//   justifyContent: "center",
//   alignItems: "center",
//   border: "1px solid gray",
//   borderRadius: "5px",
//   margin: "10px",
// };

// const placeNameStyle = {
//   display: "flex",
//   margin: "0px",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "4.4vw",
//   lineHeight: "200%",
// };

// const ratingStyle = {
//   display: "flex",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const placeAddressStyle = {
//   display: "flex",
//   textAlign: "left",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const placeNumberStyle = {
//   display: "flex",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };
// const placeOpenSytle = {
//   display: "flex",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const placeWeekTimeToggleStyle = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const placeWeekTimeDetailInfoSytle = {
//   display: "flex",
//   flexDirection: "column",
//   textAlign: "left",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const webSiteStyle = {
//   display: "flex",
//   textAlign: "left",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
//   wordWrap: "break-word",
//   overflowWrap: "break-word",
//   whiteSpace: "normal",
// };

// const moreInfoStyle = {
//   display: "flex",
//   margin: "10px",
//   justifyContent: "center",
//   textAlign: "center",
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.4vw",
//   lineHeight: "200%",
// };

// const moreInfoDetailStyle = {
//   display: "flex",
//   flexDirection: "column",
//   // justifyContent: "center",
//   // alignContent : "center",
//   // alignItems: "center",
//   textAlign: "left",
//   height: "50vh",
//   overflow: "auto", // 내용이 넘치면 스크롤 허용
// };

// const imgStyle = {
//   display: "flex",
//   justifyContent: "center",
//   alignItems: "center",
//   padding: "10px",
//   marginTop: "10px",
// };

// const smallTextStyle = {
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "3.6vw",
//   lineHeight: "200%",
//   color: "gray",
// };

// const reviewStyle = {
//   display: "flex",
//   flexDirection: "column",
//   width: "90vw",
//   margin: "10px",
//   alignItems: "flex-start", // 왼쪽 정렬을 위해 변경
//   fontFamily: "Pretendard",
//   fontStyle: "normal",
//   fontWeight: "700",
//   fontSize: "2.7vw",
//   lineHeight: "200%",
// };

// // 더보기 버튼과 리뷰 텍스트를 같은 줄에 위치시키기 위한 스타일
// const reviewContentStyle = {
//   display: "flex",
//   width: "100%",
//   justifyContent: "space-between",
//   alignItems: "center",
//   marginBottom: "10px", // 리뷰간의 간격을 조정하기 위해 추가
//   overflow: "scroll",
// };

const Review = ({ review }) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const showToggle = review.text.length > 20;

  return (
    <div className={styles.reviewContentStyle}>
      <div style={{ flex: 1 }}>
        {review.author_name.length < 10
          ? review.author_name
          : `${review.author_name.substring(0, 10)}...`}
        / 평점 : {review.rating}
      </div>
      <div style={{ marginRight: "20px" }}>
        {isExpanded
          ? review.text
          : review.text.length > 15
          ? `${review.text.substring(0, 15)}...`
          : review.text}
      </div>

      {showToggle && review.text.length > 15 && (
        <div
          onClick={() => setIsExpanded(!isExpanded)}
          style={{ cursor: "pointer" }}
        >
          <span>{isExpanded ? "접기" : "더 보기"}</span>
        </div>
      )}
    </div>
  );
};

// PhotoSlider 컴포넌트 정의
const PhotoSlider = ({ photos }) => {
  // Slider 설정
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 2,
    slidesToScroll: 1,
  };

  return (
    <Slider {...settings}>
      {photos.map((photo, index) => (
        <div key={index}>
          <img
            src={photo.getUrl()} // 이미지 URL
            style={{
              width: "40vw",
              height: "20vh",
              objectFit: "cover",
              marginLeft: "10px",
            }}
            alt="장소 사진"
          />
        </div>
      ))}
    </Slider>
  );
};

export default function GoogleMapSearch() {
  const [markers, setMarkers] = useState([]);
  const [search, setSearch] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const mapRef = useRef(null);
  const googleMapApiKey = "AIzaSyDQuG0EPBRz632DtyOLTtopsQ97Uun8ybM";
  const [outPlace, setOutPlace] = useState([]);
  const [reviews, setReviews] = useState([]);
  const [photos, setPhotos] = useState([]);
  const [showList, setShowList] = useState(false);
  const searchResultsRef = useRef(null); // 상세 정보 창에 대한 ref
  const toggleButtonRef = useRef(null); // 토글 버튼에 대한 ref
  const libraries = ["places"];
  const [center, setCenter] = useState({ lat: 37.5665, lng: 126.978 });
  const [infoToggle, setInpoToggle] = useState(false);
  const [detailToggle, setDetailToggle] = useState(false);
  const [isWeekTime, setIsWeekTime] = useState(false);
  // const { planId } = useParams();
  const location = useLocation();
  const { planId, day } = location.state;

  const onLoad = (map) => {
    mapRef.current = map;
    getCurrentLocation();
  };

  const changeInfoToggle = () => {
    setInpoToggle(!infoToggle);
  };

  const changeDetailToggle = () => {
    setDetailToggle(!detailToggle);
  };

  const [size, setSize] = useState(getSize());

  function getSize() {
    const widthVW = window.innerWidth * 0.8; // 뷰포트의 80%
    const heightVH = window.innerHeight * 0.4; // 뷰포트의 40%
    return `${Math.round(widthVW)}x${Math.round(heightVH)}`;
  }

  const apiKey = "AIzaSyDQuG0EPBRz632DtyOLTtopsQ97Uun8ybM"; // 여기에 실제 API 키를 입력해주세요.
  // const center = `${outPlace.geometry.location.lat()},${outPlace.geometry.location.lng()}`;

  const zoom = 14;
  const widthVW = window.innerWidth * 1; // 뷰포트의 80%
  const heightVH = window.innerHeight * 0.5; // 뷰포트의 40%
  // const size = `${Math.round(widthVW)}x${Math.round(heightVH)}`;
  const mapType = "roadMap";
  const markersw = "color:blue%7Clabel:S%7C37.5665,126.9780";
  const [mapUrl, setMapUrl] = useState("");

  useEffect(() => {
    if (outPlace?.geometry?.location) {
      const lat = outPlace.geometry.location.lat();
      const lng = outPlace.geometry.location.lng();
      const newMapUrl = `https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lng}&zoom=${zoom}&size=${widthVW}x${heightVH}&maptype=${mapType}&markers=color:blue%7Clabel:S%7C${lat},${lng}&key=${apiKey}`;
      console.log(newMapUrl);
      setMapUrl(newMapUrl);
    }
  }, [outPlace, widthVW, heightVH, zoom, mapType, apiKey]);

  useEffect(() => {
    function handleResize() {
      setSize(getSize());
    }

    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  const toggleShowList = () => {
    setShowList(!showList);
    console.log(showList);
  };

  const handleSearchChange = (e) => {
    const value = e.target.value;
    setSearch(value);
    if (value.length > 0) {
      setShowList(true); // 검색어가 있을 때 검색 결과창을 표시
    }
    setSuggestions([]);
    setOutPlace([]);
    setReviews([]);
    setPhotos([]);
    setMapUrl("");
    setDetailToggle(false);
  };

  useEffect(() => {
    // 클릭 이벤트 리스너를 document에 추가
    const handleClickOutside = (event) => {
      if (
        searchResultsRef.current &&
        !searchResultsRef.current.contains(event.target) &&
        toggleButtonRef.current &&
        !toggleButtonRef.current.contains(event.target)
      ) {
        setShowList(false); // 상세 정보 창 외부 클릭 시 숨김
        setSuggestions([]);
        setOutPlace([]);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      // 컴포넌트 언마운트 시 이벤트 리스너 제거
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [searchResultsRef, toggleButtonRef]);

  // 검색 결과 및 상세 정보를 보여주는 영역을 제어하는 스타일 추가
  // const searchResultsStyle = {
  //   position: "absolute", // 절대 위치 지정
  //   // display: "flex",
  //   bottom: 0, // 하단에 위치
  //   width: "100%", // 너비는 전체 차지
  //   height: "71vh", // 높이는 70vh
  //   backgroundColor: "#fff", // 배경색 지정
  //   overflowY: "scroll", // 내용이 많을 경우 스크롤
  //   display: showList ? "block" : "none", // showList 상태에 따라 보여주기/숨기기
  //   alignItems: "center",
  //   textAlign: "center",
  // };

  useEffect(() => {
    if (search.length > 0) {
      const autocompleteService =
        new window.google.maps.places.AutocompleteService();
      autocompleteService.getPlacePredictions(
        { input: search },
        (predictions, status) => {
          if (
            status === window.google.maps.places.PlacesServiceStatus.OK &&
            predictions
          ) {
            setSuggestions(predictions);
          } else {
            setSuggestions([]);
          }
        }
      );
      window.google.maps.event.trigger(mapRef.current, "resize");
    } else {
      setSuggestions([]);
    }
  }, [search]);

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      if (mapRef.current) {
        getCurrentLocation(); // 컴포넌트가 마운트될 때 사용자의 현재 위치를 가져옵니다.
      }
    }, 1000);
    // console.log(googleMapApiKey);
    return () => clearTimeout(timeoutId);
  }, []);

  useEffect(() => {
    console.log(planId, day);
  }, []);

  const handleSelect = (placeId, event) => {
    event.preventDefault(); // 추가: 클릭 이벤트의 기본 동작 방지
    const placesService = new window.google.maps.places.PlacesService(
      mapRef.current
    );
    placesService.getDetails({ placeId }, (place, status) => {
      if (status === window.google.maps.places.PlacesServiceStatus.OK) {
        const { lat, lng } = place.geometry.location;
        setMarkers([{ lat: lat(), lng: lng() }]);
        mapRef.current.panTo({ lat: lat(), lng: lng() }); // 지도 중심 이동
        mapRef.current.setZoom(5);
        console.log(place); // 선택한 장소의 상세 정보 출력
        setOutPlace(place);
        setReviews(place.reviews);
        setPhotos(place.photos || []);
        setSuggestions([]);
      }
    });
  };

  const getCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const currentPosition = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          setMarkers([currentPosition]); // 현재 위치에 마커 설정
          mapRef.current.panTo(currentPosition); // 지도 중심을 현재 위치로 이동
          console.log(currentPosition);
          mapRef.current.setZoom(5); // 줌 레벨 조정
        },
        () => {
          alert("위치 정보를 가져올 수 없습니다.");
        }
      );
    } else {
      alert("브라우저가 위치 정보를 지원하지 않습니다.");
    }
  };

  useEffect(() => {
    if (mapRef.current) {
      window.google.maps.event.trigger(mapRef.current, "resize");
      getCurrentLocation();
    }
  }, [mapRef.current]);

  const handleMapLoad = (map) => {
    mapRef.current = map;
    // getCurrentLocation();
  };

  const createdetail = async (name, formatted_address, lat, lng, rating) => {
    // const intday = parseInt(day, 10); 
    // console.log("Day value:", day);
    const object = {
      day: day, 
      sequence: 1,
      place: name,
      pinX: lat,
      pinY: lng,
      memo: formatted_address,
      time: rating,
    };
    try {
      const response = await createDetailPlan(planId, object);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      {/* <Header LeftIcon="Arrow" Title="장소 검색" />
      <div style={placeOrTicket} onClick={() => setShowList(false)}>
        <div>장소</div>
        <div>항공권</div>
      </div> */}
      <div className={styles.searchPlace} onClick={() => setShowList(false)}>
        <Mark />
        <input
          className={styles.buttonStyle}
          value={search}
          onChange={handleSearchChange}
          placeholder="장소 검색..."
        />
        {/* <button onClick={getCurrentLocation}>현재 위치 가져오기</button> */}
      </div>
      <LoadScript googleMapsApiKey={googleMapApiKey} libraries={["places"]}>
        <GoogleMap
          // mapContainerStyle={containerStyle}
          center={center}
          zoom={10}
          onLoad={onLoad}
          onUnmount={() => (mapRef.current = null)}
        >
          {markers.map((marker, i) => (
            <Marker key={i} position={marker} />
          ))}
        </GoogleMap>
      </LoadScript>
      {outPlace && showList && search.length != 0 && (
        <div
          className={styles.searchResultsStyle}
          style={{ display: showList ? "block" : "none" }}
        >
          <div className={styles.listSearch}>
            {suggestions && suggestions.length > 0 && (
              <ul>
                {suggestions.map((suggestion) => (
                  <li
                    key={suggestion.place_id}
                    onClick={(event) =>
                      handleSelect(suggestion.place_id, event)
                    }
                  >
                    {suggestion.description}
                  </li>
                ))}
              </ul>
            )}
          </div>
          {mapUrl != "" && (
            <img
              key={mapUrl}
              src={mapUrl}
              alt="Static Map"
              onClick={changeInfoToggle}
            />
          )}
          {outPlace && outPlace.name && (
            <div className={styles.listResult}>
              <div className={styles.placeNameStyle}>
                {outPlace.name} {outPlace.types[0]}
              </div>
              <div
                onClick={() => {
                  createdetail(
                    outPlace.name,
                    outPlace.vicinity,
                    outPlace.lat,
                    outPlace.lng,
                    outPlace.rating
                  );
                }}
              >
                계획 추가
              </div>
              <div className={styles.ratingStyle}>
                {outPlace.rating && (
                  <>
                    <Rating
                      emptySymbol={<FaStar color="gray" />}
                      fullSymbol={<FaStar color="gold" />}
                      initialRating={outPlace.rating}
                      readonly
                    />
                    <div>{outPlace.rating}</div>
                  </>
                )}
              </div>
              <div className={styles.placeAddressStyle}>
                {outPlace.formatted_address}
              </div>
              <div className={styles.placeNumberStyle}>
                {outPlace.international_phone_number}
              </div>

              <div
                className={styles.placeWeekTimeToggleStyle}
                onClick={() => setIsWeekTime(!isWeekTime)}
              >
                <div className={styles.placeOpenSytle}>
                  <div
                    style={{
                      color: outPlace.current_opening_hours ? "green" : "red",
                    }}
                  >
                    {outPlace.current_opening_hours?.open_now !== undefined
                      ? outPlace.current_opening_hours.open_now
                        ? "영업 중"
                        : "영업 종료"
                      : "영업 시간 정보 없음"}
                  </div>
                  <div style={{ marginLeft: "10px" }}>
                    {outPlace.current_opening_hours?.weekday_text
                      ? isWeekTime
                        ? "접기"
                        : "펼치기"
                      : ""}
                  </div>
                </div>
              </div>
              <div className={styles.placeWeekTimeDetailInfoSytle}>
                {isWeekTime &&
                  outPlace.current_opening_hours?.weekday_text &&
                  outPlace.current_opening_hours.weekday_text.map(
                    (day, index) => <div key={index}>{day}</div>
                  )}
              </div>

              {outPlace.website && (
                <div href={outPlace.website} className={styles.webSiteStyle}>
                  웹사이트 : {outPlace.website}
                </div>
              )}

              <div
                className={styles.moreInfoStyle}
                onClick={changeDetailToggle}
              >
                {detailToggle ? "줄이기" : "더보기"}
              </div>
              {detailToggle && (
                <div className={styles.moreInfoDetailStyle}>
                  <div className={styles.smallTextStyle}>Review</div>
                  <div className={styles.reviewStyle}>
                    {reviews &&
                      reviews.length > 0 &&
                      reviews.map((review, index) => (
                        <Review key={index} review={review} />
                      ))}
                  </div>
                  <div className={styles.smallTextStyle}>Photo</div>
                  <div className={styles.imgStyle}>
                    {photos && photos.length > 0 && (
                      <PhotoSlider photos={photos} />
                    )}
                  </div>
                </div>
              )}
            </div>
          )}
        </div>
      )}
    </>
  );
}
