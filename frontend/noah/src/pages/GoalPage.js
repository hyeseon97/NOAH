import Header from "./../components/common/Header";
import styles from "./GoalPage.module.css";
import { ReactComponent as Edit } from "./../assets/Icon/Edit.svg";
import { ReactComponent as GreyPeople } from "./../assets/Icon/GreyPeople.svg";
import { ReactComponent as BluePeople } from "./../assets/Icon/BluePeople.svg";

export default function GoalPage() {
  return (
    <>
      <Header LeftIcon="Arrow" Title="여행 이름" />
      <div className={styles.goalPageContainer}>
        <div className={styles.labelMedium}>목표설정</div>
        <div className={styles.goalContainer}>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>목표금액</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>9,000,000</div>
            </div>
            <div className={styles.line}></div>
          </div>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>목표기간</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>2024-03-22</div>
            </div>
            <div className={styles.line}></div>
          </div>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>월별 납입금액</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>300,000</div>
            </div>
            <div className={styles.line}></div>
          </div>
          <div className={styles.goalColumn}>
            <div className={styles.labelSmall}>납입날짜</div>
            <div className={styles.amount}>
              <Edit className={styles.icon} />
              <div className={styles.labelMedium}>15일</div>
            </div>
          </div>
        </div>
        <div className={styles.labelMedium}>달성인원</div>
        <div className={styles.memberContainer}>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <GreyPeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>핸섬건영</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <GreyPeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>핸섬건영</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
          <div className={styles.item}>
            <BluePeople className={styles.iconPeople} />
            <div className={styles.paragraphSmall}>큐티준규95</div>
          </div>
        </div>
      </div>
    </>
  );
}
