self.addEventListener('install', (event) => {
  console.log('Service Worker installing.');
});

self.addEventListener('activate', (event) => {
  console.log('Service Worker activated.');
  // 캐시 버전 관리 및 이전 캐시 삭제
  event.waitUntil(
    caches.keys().then((cacheNames) => {
      return Promise.all(
        cacheNames.map((cacheName) => {
          // 여기서 'my-site-cache-v1'은 사용 중인 캐시의 이름입니다.
          // 사용 중인 캐시 이름과 다른 모든 캐시를 삭제합니다.
          if (cacheName !== 'my-site-cache-v1') {
            console.log('Service Worker: Deleting old cache:', cacheName);
            return caches.delete(cacheName);
          }
        })
      );
    })
  );
});

self.addEventListener('fetch', (event) => {
  // Google Maps API 스크립트 요청 및 기타 Google Maps 관련 요청을 캐싱에서 제외
  if (event.request.url.startsWith('https://maps.googleapis.com/')) {
    console.log('Service Worker: Skipping cache for Google Maps API request');
    return fetch(event.request);
  }

  // 캐싱 전략: 네트워크 우선, 실패 시 캐시 사용
  event.respondWith(
    fetch(event.request).catch(() => caches.match(event.request))
  );
});
