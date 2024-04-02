import {
  deleteNotification,
  getNotification,
} from "../api/notification/Notification";
import Notification from "../components/notification/Notification";
import Header from "./../components/common/Header";
import { useEffect, useState } from "react";
import styles from "./NotificationPage.module.css";
import ClipLoader from "react-spinners/ClipLoader";
export default function NotificationPage() {
  const [notifications, setNotifications] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleDelete = (index, notificationId) => {
    const fetchDeleteNotification = async () => {
      try {
        const res = await deleteNotification(notificationId);
        if (res.status === "SUCCESS") {
        }
      } catch (error) {}
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
            setNotifications(res.data);
          }
        }
      } catch (error) {
      } finally {
        setTimeout(() => setIsLoading(false), 200);
      }
    };
    fetchNotification();
  }, []);

  return (
    <>
      <Header LeftIcon="Cancel" Title="알림" />
      {isLoading && (
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "80vh",
            }}
          >
            <ClipLoader />
          </div>
        </>
      )}
      {!isLoading && (
        <>
          {notifications.length > 0 ? (
            notifications.map((notification, index) => (
              <Notification
                key={index}
                object={notification}
                onDelete={() =>
                  handleDelete(index, notification.notificationId)
                }
              />
            ))
          ) : (
            <div className={styles.headingLarge}>조회된 알림이 없습니다.</div>
          )}
        </>
      )}
    </>
  );
}
