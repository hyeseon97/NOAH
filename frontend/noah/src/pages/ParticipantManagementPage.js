import Header from "./../components/common/Header";
import styles from "./ParticipantManagementPage.module.css";
import { ReactComponent as Plus } from "./../assets/Icon/Plus.svg";
import { useEffect, useState } from "react";
import InviteModal from "../components/common/InviteModal";
export default function ParticipantManagementPage() {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const openModal = () => setIsModalVisible(true);
  const closeModal = () => setIsModalVisible(false);

  return (
    <>
      <Header LeftIcon="Arrow" Title="인원관리" />
      <div>
        <div className={styles.memberBox}>
          <div className={styles.memberBoxLeft}>
            <div className={styles.labelLarge}>준규</div>
            <div className={styles.paragraphSmallGrey}>wnsrb123@naver.com</div>
            <div className={styles.paragraphSmallGrey}>강준규</div>
          </div>
          <div className={styles.memberBoxRight}>
            <div className={styles.paragraphSmallGrey}>
              가입날짜: 2024-03-19
            </div>
          </div>
        </div>
        <div className={styles.line}></div>
      </div>
      <div className={styles.inviteBox} onClick={openModal}>
        <Plus className={styles.icon} />
        <div className={styles.paragraphSmall}>새로운 멤버를 추가</div>
      </div>
      <InviteModal
        onDelete={() => {}}
        isVisible={isModalVisible}
        closeModal={closeModal}
      />
      <div className={styles.line}></div>
    </>
  );
}
