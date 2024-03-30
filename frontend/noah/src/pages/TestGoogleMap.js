import React, { useState, useRef, useEffect } from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Header from "./../components/common/Header";



const containerStyle = {
  width: "400px",
  height: "400px",
  // width: "100vh",
  // height: "50vh",
  margin: "10px",
  backgroundColor: "transparent",
};

const reviewStyle = {
  width: "400px",
  maxWidth: "100%",
  margin: "0 auto",
  alignItems: "center",
  justifyContent: "center",
};

const listSearch = {
  backgroundColor: "coral",
  margin: "10px",
};

const listResult = {
  backgroundColor: "pink",
  margin: "10px",
};

const center = {
  lat: 37.5665,
  lng: 126.978,
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

  return (
    <div
      key={review.id}
      onClick={toggleExpand}
      style={{ cursor: "pointer", backgroundColor: "blue" }}
    >
      {review.author_name} / {review.rating} /
      {isExpanded ? review.text : `${review.text.substring(0, 20)}...`}
      {showToggle && <span>{isExpanded ? " 접기" : " 더 보기"}</span>}
    </div>
  );
};

// const Photo = ({ photo }) => {
//   const imageUrl = photo.getUrl({ maxWidth: 200 }); // maxWidth를 지정하여 이미지 크기 조절

//   return <img src={imageUrl} alt="장소 사진" style={{ margin: "10px" }} />;
// };

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

  const onLoad = (map) => {
    mapRef.current = map;
  };

  const onUnmount = () => {
    mapRef.current = null;
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
    // getCurrentLocation(); // 컴포넌트가 마운트될 때 사용자의 현재 위치를 가져옵니다.
    console.log(googleMapApiKey);
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
      // window.google.maps.event.trigger(mapRef.current, "resize");
      // getCurrentLocation();
    }
  }, [mapRef.current]);

  const myStyle = {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  };

  const handleMapLoad = (map) => {
    mapRef.current = map;
    // getCurrentLocation();
  };

  return (
    <div className="test" style={myStyle}>
      <Header LeftIcon="Arrow" Title="장소 검색" />
      <LoadScript
        googleMapsApiKey={googleMapApiKey}
        // callback={console.debug}
        libraries={["places"]}
        onLoad={() => console.log("Google Maps API loaded successfully.")}
      >
        <GoogleMap
          mapContainerStyle={containerStyle}
          center={center}
          zoom={10}
          onLoad={handleMapLoad}
          onUnmount={onUnmount}
        >
          {markers.map((marker, i) => (
            <Marker key={i} position={marker} />
          ))}
        </GoogleMap>
      </LoadScript>
      <div style={listSearch}>
        <button onClick={getCurrentLocation}>현재 위치 가져오기</button>
        <input
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          placeholder="장소 검색..."
        />
        <ul>
          {suggestions.map((suggestion) => (
            <li
              key={suggestion.place_id}
              onClick={(event) => handleSelect(suggestion.place_id, event)}
            >
              {suggestion.description}
            </li>
          ))}
        </ul>
      </div>
      <div style={listResult}>
        {outPlace.formatted_address}
        <br />
        <br />
        {outPlace.geometry?.location.lat()}
        <br />
        {outPlace.geometry?.location.lng()}
        <br />
        {outPlace.name}
        <br />
        {outPlace.rating}
        <br />
        <div>
          {reviews &&
            reviews.map((review, index) => (
              <Review key={index} review={review} />
            ))}
        </div>

        {/* <div>
          {photos &&
            photos.map((photo, index) => <Photo key={index} photo={photo} />)}
        </div> */}
        {/* {outPlace.photos[1].getUrl()} */}
        <div style={reviewStyle}>
          {photos.length > 0 && <PhotoSlider photos={photos} />}
        </div>
      </div>
    </div>
  );
}
