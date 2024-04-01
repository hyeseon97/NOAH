import { getNotification } from "../api/notification/Notification";
import Notification from "../components/notification/Notification";
import Header from "./../components/common/Header";
import { useEffect, useState } from "react";
export default function NotificationPage() {
  const [notifications, setNotifications] = useState([]);

  const handleDelete = (index) => {
    setNotifications((currentNotifications) =>
      currentNotifications.filter((_, i) => i !== index)
    );
  };

  useEffect(() => {
    const fetchNotification = async () => {
      try {
        const res = await getNotification();
        if (res.status === "SUCCESS") {
          console.log(res.data);
          setNotifications(res.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchNotification();
  }, []);

  return (
    <>
      <Header LeftIcon="Cancel" Title="알림" />
      {notifications.map((notification, index) => (
        <Notification
          key={index}
          object={notification}
          onDelete={() => handleDelete(index)}
        />
      ))}
    </>
  );
}
