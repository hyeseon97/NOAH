import React, { useState, useRef, useEffect } from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Header from "./../components/common/Header";

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

const reviewStyle = {
  width: "400px",
  maxWidth: "100%",
  margin: "0 auto",
  alignItems: "center",
  justifyContent: "center",
};

const listSearch = {
  backgroundColor: "coral",
  // margin: "10px",
  display: "flex",
  flexDirection: "row",
  justifyContent: "center",
  alignItems: "center",
};

const buttonStyle = {
  width: "90vw",
  height: "10vh",
};

const listResult = {
  backgroundColor: "pink",
  // height: "100px"
  // margin: "10px",
};

const center = {
  lat: 37.5665,
  lng: 126.978,
};

const placeOrTicket = {
  height: "8vh",
  backgroundColor: "red",
};

const searchPlace = {
  height: "8vh",
  display: "flex",
  flexDirection: "row",
  justifyContent: "center",
  alignItems: "center",
  backgroundColor: "green",
};

const Review = ({ review }) => {
  // 리뷰 텍스트를 전체 보여줄지 여부를 결정하는 상태
  const [isExpanded, setIsExpanded] = useState(false);

  // "더 보기" / "접기" 버튼 클릭 이벤트 핸들러
  const toggleExpand = () => {
    setIsExpanded(!isExpanded);
  };

  // 리뷰 텍스트가 20글자를 초과하는 경우에만 "더 보기" / "접기" 기능 활성화
  const showToggle = review.text.length > 20;

  const [selectedReview, setSelectedReview] = useState(null);

  const handleSelectReview = (review) => {
    setSelectedReview(review);
  };

  // 뒤로 가기 버튼 핸들러
  const handleBack = () => {
    setSelectedReview(null); // 선택된 리뷰를 null로 설정하여 리스트로 돌아감
  };

  return (
    <>
      <div
        key={review.id}
        onClick={toggleExpand}
        style={{ cursor: "pointer", backgroundColor: "blue" }}
      >
        {review.author_name} / {review.rating} /{review.text.substring(0, 20)}
        {/* {showToggle && <span>{isExpanded ? " 접기" : " 더 보기"}</span>} */}
      </div>

      {/* <div>
        {selectedReview ? (
          // 선택된 리뷰의 상세 정보와 뒤로 가기 버튼을 렌더링
          <>
            <Review review={selectedReview} />
            <button onClick={handleBack}>뒤로 가기</button>
          </>
        ) : (
          // 리뷰 리스트 렌더링
          reviewsData.map((review) => (
            <div key={review.id} onClick={() => handleSelectReview(review)}>
              {review.author_name}
            </div>
          ))
        )}
      </div> */}
    </>
  );
};

const PhotoSlider = ({ photos }) => {
  const settings = {
    dots: false, // 슬라이더 하단에 점 표시 여부
    infinite: true, // 무한 슬라이드 여부
    speed: 500, // 슬라이드 전환 속도
    slidesToShow: 1, // 한 번에 보여줄 슬라이드 개수
    slidesToScroll: 1, // 스크롤할 때 넘어가는 슬라이드 개수
  };

  return (
    <Slider {...settings}>
      {photos.map((photo, index) => (
        <div
          key={index}
          style={{ width: "100%", height: "100%", objectFit: "cover" }}
        >
          <img
            src={photo.getUrl({
              maxWidth: "400",
              maxHeight: "400",
              height: "400",
              width: "400",
            })}
            alt="장소 사진"
          />
          <div className="googlemaptest" />
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

  // useEffect(() => {
  //   if (outPlace && outPlace.geometry && outPlace.geometry.location) {
  //     setCenter({
  //       lat: outPlace.geometry.location.lat(),
  //       lng: outPlace.geometry.location.lng(),
  //     });
  //     setMarkers([
  //       {
  //         // 새 마커 위치를 설정
  //         lat: outPlace.geometry.location.lat(),
  //         lng: outPlace.geometry.location.lng(),
  //       },
  //     ]);
  //   }
  // }, [outPlace]);

  // useEffect(() => {
  //   if (outPlace && outPlace.geometry && outPlace.geometry.location) {
  //     const lat = outPlace.geometry.location.lat();
  //     const lng = outPlace.geometry.location.lng();
  //     setCenter({ lat, lng });
  //     setMarkers([{ lat, lng }]);
  //     const newMapUrl = `https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lng}&zoom=${zoom}&size=${size}&maptype=${mapType}&markers=color:blue%7Clabel:S%7C${lat},${lng}&key=${apiKey}`;
  //     setMapUrl(newMapUrl);
  //   }
  // }, [outPlace]);

  useEffect(() => {
    if (outPlace?.geometry?.location) {
      const lat = outPlace.geometry.location.lat();
      const lng = outPlace.geometry.location.lng();
      const newMapUrl = `https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lng}&zoom=${zoom}&size=${widthVW}x${heightVH}&maptype=${mapType}&markers=color:blue%7Clabel:S%7C${lat},${lng}&key=${apiKey}`;
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

  // 검색어 입력 시 호출되는 함수
  // useEffect(() => {
  //   const handleSearchChange = (e) => {
  //     const value = e.target.value;
  //     setSearch(value);
  //     if (value.length > 0) {
  //       setShowList(true); // 검색어가 있을 때 검색 결과창을 표시
  //     }
  //     setSuggestions([]);
  //     setOutPlace([]);
  //   };
  // }, [search])

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
  const searchResultsStyle = {
    position: "absolute", // 절대 위치 지정
    bottom: 0, // 하단에 위치
    width: "100%", // 너비는 전체 차지
    height: "71vh", // 높이는 70vh
    backgroundColor: "#fff", // 배경색 지정
    overflowY: "scroll", // 내용이 많을 경우 스크롤
    display: showList ? "block" : "none", // showList 상태에 따라 보여주기/숨기기
  };

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

  const myStyle = {
    display: "none",
    flexDirection: "column",
    alignItems: "center",
  };

  const searchList = {
    backgroundColor: "orange",

    height: "5vh",
  };

  const handleMapLoad = (map) => {
    mapRef.current = map;
    // getCurrentLocation();
  };

  return (
    <>
      <Header LeftIcon="Arrow" Title="장소 검색" />
      <div style={placeOrTicket} onClick={() => setShowList(false)}></div>
      <div style={searchPlace} onClick={() => setShowList(false)}>
        <input
          style={buttonStyle}
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

      <div
        style={{
          cursor: "pointer",
          backgroundColor: detailToggle ? "yellow" : "orange", // 토글 버튼 색상 변경
        }}
        onClick={changeDetailToggle}
      >
        {detailToggle ? "상세 정보 숨기기" : "상세 정보 보기"}
      </div>
      {showList && search.length != 0 && (
        <div style={searchResultsStyle}>
          <div style={listSearch}>
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
          {outPlace && (
            <div style={listResult} onClick={changeDetailToggle}>
              {outPlace.formatted_address}
              <br />
              {outPlace.geometry?.location.lat()}
              <br />
              {outPlace.geometry?.location.lng()}
              <br />
              {outPlace.name}
              <br />
              {outPlace.rating}
              <br />
              {detailToggle && (
                <div>
                  {reviews &&
                    reviews.length > 0 &&
                    reviews.map((review, index) => (
                      <Review key={index} review={review} />
                    ))}
                  <div style={reviewStyle}>
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
