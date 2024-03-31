import { useState } from "react";
import { getImageList, uplodaImage } from "../api/image/Image";

export default function ErrorPage() {
  const [files, setFiles] = useState(null);

  const handleFileChange = (event) => {
    // 선택된 파일들을 files 상태에 저장
    setFiles(event.target.files);
  };

  const upload = async () => {
    const formData = new FormData();
    formData.append("images", files[0]);
    const res = await uplodaImage(formData);
    console.log(res);
    const urlRes = await getImageList([1, 2]);
    console.log(urlRes);
  };
  return (
    <>
      <h1>
        이미지 테스트
        <input type="file" onChange={handleFileChange} multiple></input>
        <div onClick={upload}>업로드</div>
      </h1>
    </>
  );
}
