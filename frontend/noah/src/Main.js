import { BrowserRouter, Routes, Route } from "react-router-dom";
import ScrollToTop from "./components/common/ScrollToTop";
import App from "./App";
import WelcomePage from "./pages/WelcomgPage";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage";
import HomePage from "./pages/HomePage";
import TransferPage from "./pages/TransferPage";
import TripCreatePage from "./pages/TripCreatePage";
import TripPage from "./pages/TripPage";
import GoalPage from "./pages/GoalPage";
import NotificationPage from "./pages/NotificationPage";
import ParticipantManagementPage from "./pages/ParticipantManagementPage";
import ExchangePage from "./pages/ExchangePage";
import SpendingManagemnetPage from "./pages/SpendingManagementPage";
import PlanningCreatePage from "./pages/PlanningCreatePage";
import PlanningPage from "./pages/PlanningPage";
import MyPage from "./pages/MyPage";
import TravelHistoryPage from "./pages/TravelHistoryPage";
import AutomaticWithdrawalSettingPage from "./pages/AutomaticWithdrawalSettingPage";
import ErrorPage from "./pages/ErrorPage";
import GoogleMapSearch from "./pages/GoogleMapSearch";
import TestGoogleMap from "./pages/TestGoogleMap";
import PaymentPage from "./pages/PaymentPage";
import PlaneSearchPage from "./pages/PlaneSearchPage";
import PlanningTestPage from "./pages/PlanningTestPage";
import MyAccount from "./components/common/MyAccount";
import MyAccountPage from "./pages/MyAccountPage";

export default function Main() {
  return (
    <BrowserRouter>
      <ScrollToTop />
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<WelcomePage />} />
          <Route path="login" element={<LoginPage />} />
          <Route path="signup" element={<SignUpPage />} />
          <Route path="home" element={<HomePage />} />
          <Route path="transfer/:travelId" element={<TransferPage />} />
          <Route path="tripcreate" element={<TripCreatePage />} />
          <Route path="trip/:travelId">
            <Route index element={<TripPage />} />
            <Route path="goal" element={<GoalPage />} />
            <Route
              path="participantmanagement"
              element={<ParticipantManagementPage />}
            />
            <Route path="exchange" element={<ExchangePage />} />
            <Route
              path="spendingmanagement"
              element={<SpendingManagemnetPage />}
            />
            <Route path="payment" element={<PaymentPage />} />
            <Route path="planningcreate" element={<PlanningCreatePage />} />
            <Route path="planning" element={<PlanningPage />} />
          </Route>
          <Route path="googlemap" element={<GoogleMapSearch />} />
          <Route path="planeSearch" element={<PlaneSearchPage />} />
          <Route path="planningTest" element={<PlanningTestPage />}/>
          <Route path="testgooglemap" element={<TestGoogleMap />} />
          <Route path="notification" element={<NotificationPage />} />
          <Route path="mypage" element={<MyPage />} />
          <Route path="travelhistory" element={<TravelHistoryPage />} />
          <Route path="myaccount" element={<MyAccountPage />} />
          <Route
            path="automaticwithdrawalsetting"
            element={<AutomaticWithdrawalSettingPage />}
          />
        </Route>
        <Route path="*" element={<ErrorPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
