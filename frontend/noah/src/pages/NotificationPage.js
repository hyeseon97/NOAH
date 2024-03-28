import Notification from "../components/notification/Notification";
import Header from "./../components/common/Header";
export default function NotificationPage() {
  return (
    <>
      <Header LeftIcon="Cancel" Title="알림" />

      <Notification />
      <Notification />
      <Notification />
      <Notification />
    </>
  );
}
