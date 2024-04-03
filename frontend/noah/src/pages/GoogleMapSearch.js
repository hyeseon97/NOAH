import React, { useState, useRef, useEffect } from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Header from "../components/common/Header";
import { ReactComponent as Search } from "./../assets/Icon/Search.svg";
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
  const showToggle = review.text?.length > 20;

  return (
    <div className={styles.reviewContentStyle}>
      <div className={styles.reviewAuthor}>
        <div className={styles.labelMedium}>
          {review.author_name?.length < 10
            ? review.author_name
            : `${review.author_name.substring(0, 10)}...`}
        </div>
        <Rating
          emptySymbol={<FaStar color="gray" />}
          fullSymbol={<FaStar color="gold" />}
          initialRating={review.rating}
          readonly
        />
        <div className={styles.labelSmallGrey}>({review.rating})</div>
      </div>
      <div className={styles.labelSmall}>
        {isExpanded
          ? review.text
          : review.text?.length > 15
          ? `${review.text.substring(0, 15)}...`
          : review.text}
      </div>

      {showToggle && review.text?.length > 15 && (
        <div
          onClick={() => setIsExpanded(!isExpanded)}
          className={styles.labelSmallOpen}
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
  const [outPlace, setOutPlace] = useState({ website: "" });
  const [reviews, setReviews] = useState([]);
  const [photos, setPhotos] = useState([]);
  const [showList, setShowList] = useState(false);
  const searchResultsRef = useRef(null); // 상세 정보 창에 대한 ref
  const toggleButtonRef = useRef(null); // 토글 버튼에 대한 ref
  const [center, setCenter] = useState({ lat: 37.5665, lng: 126.978 });
  const [infoToggle, setInpoToggle] = useState(false);
  const [detailToggle, setDetailToggle] = useState(false);
  const [isWeekTime, setIsWeekTime] = useState(false);
  const location = useLocation();
  const { planId, day, date } = location.state;
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

  const DEFAULT_IMAGE_URL =
    "https://us.123rf.com/450wm/robuart/robuart1808/robuart180801020/111971665-%ED%99%94%EC%B0%BD%ED%95%9C-%EB%82%A0-%EC%A0%95%EC%82%AC%EA%B0%81%ED%98%95-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EB%A7%8C%ED%99%94-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%A0%88%EC%9D%B4-%EC%85%98%EC%97%90-%ED%94%84%EB%9E%91%EC%8A%A4-%EC%82%AC%EC%A7%84-%EC%9C%A0%EB%9F%BD-%EB%AA%85%EC%86%8C%EB%A5%BC-%EC%97%AC%ED%96%89%ED%95%98%EB%8A%94-%EC%97%90%ED%8E%A0%ED%83%91%EA%B3%BC-%ED%95%A8%EA%BB%98-%ED%8C%8C%EB%A6%AC%EB%A1%9C%EC%9D%98-%EC%97%AC%ED%96%89.jpg";

  const handleCreateDetail = async (name, vicinity, lat, lng, rating, url) => {
    try {
      await createdetail(
        name,
        vicinity,
        lat,
        lng,
        rating,
        url || DEFAULT_IMAGE_URL
      );
      navigate(-1);
    } catch (error) {
      console.error("계획 추가 중 오류 발생: ", error);
    }
  };

  function getSize() {
    const widthVW = window.innerWidth * 0.8; // 뷰포트의 80%
    const heightVH = window.innerHeight * 0.4; // 뷰포트의 40%
    return `${Math.round(widthVW)}x${Math.round(heightVH)}`;
  }

  const apiKey = process.env.REACT_APP_GOOGLE_API_KEY;

  const zoom = 14;
  // const widthVW = Math.round(window.innerWidth * 1); // 뷰포트의 100%를 사용하고 반올림
  // const heightVH = Math.round(window.innerHeight * 0.5); // 뷰포트의 50%를 사용하고 반올림
  // 너비와 높이를 계산하는 예제 코드
const widthMap = window.innerWidth * 1.0; // 뷰포트의 80%를 사용하는 예시
const heightMap = window.innerHeight * 0.5; // 뷰포트의 40%를 사용하는 예시

// 소수점을 처리하여 정수로 만들기
const width = Math.round(widthMap);
const height = Math.round(heightMap);

// `size` 파라미터에 적용
const sizeToMap = `${width}x${height}`;

  
  const mapType = "roadMap";
  const [mapUrl, setMapUrl] = useState("");

  useEffect(() => {
    if (outPlace?.geometry?.location) {
      const lat = outPlace.geometry.location.lat();

      const lng = outPlace.geometry.location.lng();
      const newMapUrl = `https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lng}&zoom=${zoom}&size=${sizeToMap}&maptype=${mapType}&markers=color:blue%7Clabel:S%7C${lat},${lng}&key=${apiKey}`;
      setMapUrl(newMapUrl);
    }
  }, [outPlace, widthMap, heightMap, zoom, mapType, apiKey]);

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
  };

  const handleSearchChange = (e) => {
    const value = e.target.value;
    setSearch(value);
    if (value?.length > 0) {
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
    if (search?.length > 0) {
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

  useEffect(() => {}, []);

  const handleSelect = (placeId, event) => {
    event.preventDefault();
    const placesService = new window.google.maps.places.PlacesService(
      mapRef.current
    );
    placesService.getDetails({ placeId }, (place, status) => {
      if (status === window.google.maps.places.PlacesServiceStatus.OK) {
        const { lat, lng } = place.geometry.location;
        setMarkers([{ lat: lat(), lng: lng() }]);
        mapRef.current?.panTo({ lat: lat(), lng: lng() });
        mapRef.current?.setZoom(5);
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
          mapRef.current?.panTo(currentPosition);
          mapRef.current?.setZoom(5);
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

  const createdetail = async (
    name,
    formatted_address,
    lat,
    lng,
    rating,
    url
  ) => {
    const object = {
      day: day,
      sequence: 1,
      place: name,
      pinX: lat,
      pinY: lng,
      memo: formatted_address,
      time: rating,
      imageUrl: url,
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
        <Search className={styles.searchIcon} />
        <input
          className={styles.buttonStyle}
          value={search}
          onChange={handleSearchChange}
          placeholder="장소 검색..."
        />
      </div>
      <LoadScript googleMapsApiKey={apiKey} libraries={["places"]}>
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
      {outPlace && showList && search?.length !== 0 && (
        <div
          className={styles.searchResultsStyle}
          style={{ display: showList ? "block" : "none" }}
        >
          {showList && suggestions?.length > 0 && (
            <div className={styles.listSearch}>
              {suggestions &&
                suggestions.map((suggestion) => (
                  <div
                    key={suggestion.place_id}
                    onClick={(event) =>
                      handleSelect(suggestion.place_id, event)
                    }
                    className={styles.contentsBox}
                  >
                    <Mark className={styles.markIcon} />
                    <div className={styles.contents}>
                      <div className={styles.placeTitle}>
                        {suggestion.structured_formatting.main_text}
                      </div>
                      <div className={styles.labelSmallGrey}>
                        {suggestion.structured_formatting.secondary_text
                          ?.length > 20
                          ? `${suggestion.structured_formatting.secondary_text.substring(
                              0,
                              20
                            )}...`
                          : suggestion.structured_formatting.secondary_text}
                      </div>
                    </div>
                  </div>
                ))}
            </div>
          )}
          {mapUrl !== "" && (
            <img
              key={mapUrl}
              src={mapUrl}
              alt="Static Map"
              onClick={changeInfoToggle}
            />
          )}
          {outPlace && outPlace.name && (
            <div className={styles.listResult}>
              <div className={styles.resultHead}>
                <div className={styles.labelLarge}>
                  {outPlace.name} {outPlace.types[0]}
                </div>
                <div
                  onClick={() =>
                    handleCreateDetail(
                      outPlace.name,
                      outPlace.vicinity,
                      outPlace.lat,
                      outPlace.lng,
                      outPlace.rating,
                      outPlace.photos && outPlace.photos?.length > 0
                        ? outPlace.photos[0].getUrl()
                        : DEFAULT_IMAGE_URL
                    )
                  }
                  className={styles.addButton}
                >
                  계획 추가
                </div>
              </div>

              <div className={styles.ratingStyle}>
                {outPlace.rating && (
                  <>
                    <Rating
                      className={styles.rating}
                      emptySymbol={
                        <FaStar className={styles.star} color="gray" />
                      }
                      fullSymbol={
                        <FaStar className={styles.star} color="gold" />
                      }
                      initialRating={outPlace.rating}
                      readonly
                    />
                    <div className={styles.labelSmallGrey}>
                      ({outPlace.rating})
                    </div>
                  </>
                )}
              </div>
              <div className={styles.labelMedium}>
                {outPlace.formatted_address}
              </div>
              <div className={styles.labelSmall}>
                {outPlace.international_phone_number}
              </div>

              <div
                className={styles.placeWeekTimeToggleStyle}
                onClick={() => setIsWeekTime(!isWeekTime)}
              >
                <div className={styles.placeOpenSytle}>
                  <div className={styles.open}>
                    <div
                      className={styles.openingTime}
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
                    <div className={styles.labelSmallOpen}>
                      {outPlace.current_opening_hours?.weekday_text
                        ? isWeekTime
                          ? "접기"
                          : "보기"
                        : ""}
                    </div>
                  </div>
                </div>
              </div>
              <div className={styles.labelMedium}>
                {isWeekTime &&
                  outPlace.current_opening_hours?.weekday_text &&
                  outPlace.current_opening_hours.weekday_text.map(
                    (day, index) => <div key={index}>{day}</div>
                  )}
              </div>

              {outPlace?.website && (
                <div
                  href={outPlace.website}
                  target="_blank"
                  rel="noopener noreferrer"
                  className={styles.website}
                >
                  <div className={styles.labelSmall}>웹사이트 : </div>
                  <div className={styles.labelSmallGrey}>
                    {outPlace.website?.length > 30
                      ? `${outPlace.website?.substring(0, 35)}...`
                      : outPlace.website}
                  </div>
                </div>
              )}

              <div
                className={styles.moreInfoStyle}
                onClick={changeDetailToggle}
              >
                {detailToggle ? "줄이기" : "리뷰 더보기"}
              </div>
              {detailToggle && (
                <div className={styles.moreInfoDetailStyle}>
                  <div className={styles.reviewStyle}>
                    {reviews &&
                      reviews?.length > 0 &&
                      reviews.map((review, index) => (
                        <Review key={index} review={review} />
                      ))}
                  </div>
                  <div className={styles.imgStyle}>
                    {photos && photos?.length > 0 && (
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
