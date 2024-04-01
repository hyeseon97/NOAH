import Header from "./../components/common/Header";
import styles from "./ParticipantManagementPage.module.css";
import { ReactComponent as Plus } from "./../assets/Icon/Plus.svg";
import { useEffect, useState } from "react";
import InviteModal from "../components/common/InviteModal";
import { useParams } from "react-router-dom";
import { getGroupAccountMemberAndTotalDue } from "../api/groupaccount/GroupAccount";
export default function ParticipantManagementPage() {
  const { travelId } = useParams();
  const [memberInfo, setMemberInfo] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const openModal = () => setIsModalVisible(true);
  const closeModal = () => setIsModalVisible(false);

  useEffect(() => {
    const fetchMemberInfo = async () => {
      try {
        const res = await getGroupAccountMemberAndTotalDue(travelId);
        if (res.status === "SUCCESS") {
          setMemberInfo(res.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchMemberInfo();
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="인원관리" />
      <div>
        {memberInfo.map((member, index) => (
          <div key={index}>
            <div className={styles.memberBox}>
              <div className={styles.memberBoxLeft}>
                <div className={styles.labelLarge}>{member.memberNickname}</div>
                <div className={styles.paragraphSmallGrey}>
                  {member.memberEmail}
                </div>
              </div>
              <div className={styles.memberBoxRight}>
                <div className={styles.paragraphSSmallGrey}>납입금</div>
                <div className={styles.labelLarge}>
                  {new Intl.NumberFormat("ko-KR").format(member.payment_amount)}{" "}
                  원
                </div>
              </div>
            </div>
            <div className={styles.line} />
          </div>
        ))}
      </div>
      <div className={styles.inviteBox} onClick={openModal}>
        <Plus className={styles.icon} />
        <div className={styles.paragraphSmall}>새로운 멤버를 추가</div>
      </div>
      <InviteModal
        onDelete={() => {}}
        isVisible={isModalVisible}
        closeModal={closeModal}
        travelId={travelId}
      />
      <div className={styles.line}></div>
    </>
  );
}
