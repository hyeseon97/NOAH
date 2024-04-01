import {
  deleteNotification,
  getNotification,
} from "../api/notification/Notification";
import Notification from "../components/notification/Notification";
import Header from "./../components/common/Header";
import { useEffect, useState } from "react";
import styles from "./NotificationPage.module.css";
export default function NotificationPage() {
  const [notifications, setNotifications] = useState([]);

  const handleDelete = (index, notificationId) => {
    const fetchDeleteNotification = async () => {
      try {
        const res = await deleteNotification(notificationId);
        if (res.status === "SUCCESS") {
          console.log("삭제성공");
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchDeleteNotification();
    setNotifications((currentNotifications) =>
      currentNotifications.filter((_, i) => i !== index)
    );
  };

  useEffect(() => {
    const fetchNotification = async () => {
      try {
        const res = await getNotification();
        if (res.status === "SUCCESS") {
          if (res.data === null) {
          } else {
            console.log(res.data);
            setNotifications(res.data);
          }
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
      {notifications.length > 0 ? (
        notifications.map((notification, index) => (
          <Notification
            key={index}
            object={notification}
            onDelete={() => handleDelete(index, notification.notificationId)}
          />
        ))
      ) : (
        <div className={styles.headingLarge}>조회된 알림이 없습니다.</div>
      )}
    </>
  );
}
