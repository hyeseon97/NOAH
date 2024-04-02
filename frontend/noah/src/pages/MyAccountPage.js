import Header from "../components/common/Header";
import ClipLoader from "react-spinners/ClipLoader";
import { useState, useEffect } from "react";
import { getAccount } from "../api/account/Account";
import MyAccount from "./../components/common/MyAccount";

export default function MyAccountPage() {
  const [isLoading, setIsLoading] = useState(false);
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    (async () => {
      try {
        const res = await getAccount();
        setAccounts(res.data);
      } catch (e) {
      } finally {
        setIsLoading(false);
      }
    })();
  }, []);

  return (
    <>
      <Header LeftIcon="Arrow" Title="내 계좌" />
      {!isLoading && (
        <>
          {accounts
            .filter((account) => account.type !== "공동계좌") // 공동계좌만 불러옴
            .map((account) => (
              <MyAccount
                key={account.accountId} // 고유 key 값으로 accountId 사용
                type={account.bankName} // 조건에 따른 type 결정
                accountNumber={account.accountNumber}
                sum={account.amount}
                from="myaccount"
              />
            ))}
        </>
      )}
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
    </>
  );
}
