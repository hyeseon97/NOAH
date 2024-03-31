import Notification from "../components/notification/Notification";
import Header from "./../components/common/Header";
import { useState, useEffect } from "react";
export default function NotificationPage() {
  const [notifications, setNotifications] = useState([
    { isInvitation: true },
    { isInvitation: true },
    { isInvitation: true },
    { isInvitation: false },
  ]);

  const handleDelete = (index) => {
    setNotifications((currentNotifications) =>
      currentNotifications.filter((_, i) => i !== index)
    );
  };

  return (
    <>
      <Header LeftIcon="Cancel" Title="알림" />
      {notifications.map((notification, index) => (
        <Notification
          key={index}
          isInvitation={notification.isInvitation}
          onDelete={() => handleDelete(index)}
        />
      ))}
    </>
  );
}
