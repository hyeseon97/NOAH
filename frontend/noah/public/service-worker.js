// service-worker.js

self.addEventListener("install", (event) => {
  console.log("Service Worker installing.");
});

self.addEventListener("activate", (event) => {
  console.log("Service Worker activated.");
});

self.addEventListener('fetch', (event) => {
    // Google Maps API 스크립트 요청을 캐싱에서 제외
    if (event.request.url.indexOf('https://maps.googleapis.com/maps/api/js') === 0) {
      return fetch(event.request);
    }
  
    // 여기에 다른 캐싱 로직을 추가
  });
  
