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
import { useNavigate } from "react-router-dom";

import {
  getDetailPlan,
  getDetailPlanList,
  updateDetailPlan,
  deleteDetailPlan,
  createDetailPlan,
} from "../api/detailplan/DetailPlan";

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
  const navigate = useNavigate();

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

  const handleCreateDetail = async (name, vicinity, lat, lng, rating) => {
    try {
      await createdetail(name, vicinity, lat, lng, rating);
      navigate(-1);
    } catch (error) {
      // 에러 처리
      console.error("계획 추가 중 오류 발생", error);
    }
  };

  function getSize() {
    const widthVW = window.innerWidth * 0.8; // 뷰포트의 80%
    const heightVH = window.innerHeight * 0.4; // 뷰포트의 40%
    return `${Math.round(widthVW)}x${Math.round(heightVH)}`;
  }

  const apiKey = "AIzaSyDQuG0EPBRz632DtyOLTtopsQ97Uun8ybM";

  const zoom = 14;
  const widthVW = window.innerWidth * 1; // 뷰포트의 80%
  const heightVH = window.innerHeight * 0.5; // 뷰포트의 40%
  const mapType = "roadMap";
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
    const handleClickOutside = (event) => {
      if (
        searchResultsRef.current &&
        !searchResultsRef.current.contains(event.target) &&
        toggleButtonRef.current &&
        !toggleButtonRef.current.contains(event.target)
      ) {
        setShowList(false);
        setSuggestions([]);
        setOutPlace([]);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [searchResultsRef, toggleButtonRef]);

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
    return () => clearTimeout(timeoutId);
  }, []);

  useEffect(() => {
    console.log(planId, day);
  }, []);

  const handleSelect = (placeId, event) => {
    event.preventDefault();
    const placesService = new window.google.maps.places.PlacesService(
      mapRef.current
    );
    placesService.getDetails({ placeId }, (place, status) => {
      if (status === window.google.maps.places.PlacesServiceStatus.OK) {
        const { lat, lng } = place.geometry.location;
        setMarkers([{ lat: lat(), lng: lng() }]);
        mapRef.current.panTo({ lat: lat(), lng: lng() });
        mapRef.current.setZoom(5);
        console.log(place);
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
          setMarkers([currentPosition]);
          mapRef.current.panTo(currentPosition);
          console.log(currentPosition);
          mapRef.current.setZoom(5);
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
  };

  const createdetail = async (name, formatted_address, lat, lng, rating) => {
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
      <div className={styles.searchPlace} onClick={() => setShowList(false)}>
        <Mark />
        <input
          className={styles.buttonStyle}
          value={search}
          onChange={handleSearchChange}
          placeholder="장소 검색..."
        />
      </div>
      <LoadScript googleMapsApiKey={googleMapApiKey} libraries={["places"]}>
        <GoogleMap
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
                onClick={() =>
                  handleCreateDetail(
                    outPlace.name,
                    outPlace.vicinity,
                    outPlace.lat,
                    outPlace.lng,
                    outPlace.rating
                  )
                }
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
