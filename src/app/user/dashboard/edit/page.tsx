"use client";

import Image from "next/image";
import avaBlank from "@/public/avaBlank.png";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import axiosInstance from "@/axios";

interface Role {
  id: number;
  roleName: string;
  createdDate: string;
  updatedTime: string;
}

interface User {
  userId: number;
  username: string;
  password: string;
  fullName: string;
  dateOfBirth: string;
  gender: string;
  email: string;
  address: string;
  phone: string;
  identityCard: string;
  imageURL: string;
  role: Role;
}

export default function Home() {
  const [user, setUser] = useState<User>();
  const router = useRouter();
  const [userId, setUserId] = useState(0);
  const [fileName, setFileName] = useState<File>();
  const [imageName, setImageName] = useState("")
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [fullName, setFullName] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [gender, setGender] = useState("MALE");
  const [identityCard, setIdentityCard] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [notReload, setNotReload] = useState(true);
  const [updated, setUpdated] = useState(false);
  const [invalidData, setInvalidData] = useState(false);

  useEffect(() => {
    const userName = localStorage.getItem("account");
    async function getData() {
      await axiosInstance.get(`/api/user/${userName}`).then((response) => {
        setUser(response.data);
      });
    }
    if (notReload) {
      if (userName !== null) {
        getData();
      }
      setNotReload(false);
    }
  }, [notReload]);

  useEffect(() => {
    setUserId(user?.userId || 0);
    setAccount(user?.username || "");
    setPassword(user?.password || "");
    setConfirmPassword("");
    setFullName(user?.fullName || "");
    setDateOfBirth(user?.dateOfBirth || "");
    setGender(user?.gender || "");
    setIdentityCard(user?.identityCard || "");
    setEmail(user?.email || "");
    setAddress(user?.address || "");
    setPhoneNumber(user?.phone || "");
    setImageName(user?.imageURL || "")
  }, [user]);

  const handleFileChange = (e: any) => {
    setFileName(e.target.files[0]);
    setImageName(e.target.files[0].name)
    if (e.target.files[0]) {
      const form = new FormData();
      form.append("imageFile", e.target.files[0]);
      axiosInstance.post("/images", form);
    }
  };

  function checkFormFilled() {
    const allStringsFilled =
      account &&
      password &&
      fullName &&
      dateOfBirth &&
      gender &&
      identityCard &&
      email &&
      address &&
      phoneNumber;
    const fileSelected = fileName !== undefined;
    return allStringsFilled && fileSelected;
  }

  function redirectHome() {
    router.push("/user/dashboard/home");
  }

  function handleUpdate(e: any) {
    e.preventDefault();
    if (checkFormFilled()) {
      axiosInstance
        .put("/api/profile", {
          userId: userId,
          username: account,
          password: password,
          fullName: fullName,
          dateOfBirth: dateOfBirth,
          gender: gender,
          email: email,
          address: address,
          phone: phoneNumber,
          identityCard: identityCard,
          imageURL: fileName?.name,
        })
        .then((response) => {
          setUpdated(true);
        });
    } else {
      setInvalidData(true);
    }
  }

  return (
    <div className="bg-[#EFF0F3] p-10 text-black h-full overflow-auto">
      <div className="bg-white flex flex-col items-center gap-3 pb-10">
        <span className="font-[700] border-b-[1px] w-full py-5 text-center text-[1.2rem]">
          Account Information
        </span>
        <div className="w-[20rem] h-[20rem] flex items center justify-center pt-5 rounded-full">
          <img src={process.env.NEXT_PUBLIC_API_BASE_URL + "/images/" + user?.imageURL} alt="" className="w-full h-full object-cover rounded-full"/>
        </div>
        <form
          className="w-[95%] bg-white px-[10px] flex flex-col gap-3"
          onSubmit={(e) => handleUpdate(e)}
        >
          <label
            htmlFor="account"
            className="block text-sm font-medium text-gray-700"
          >
            Account:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="account"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            disabled
            value={account}
          />
          <label
            htmlFor="password"
            className="block text-sm font-medium text-gray-700"
          >
            Password:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="password"
            type="password"
            value={password}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setPassword(e.target.value)}
          />
          <label
            htmlFor="full_name"
            className="block text-sm font-medium text-gray-700"
          >
            Full name:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="full_name"
            type="text"
            value={fullName}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setFullName(e.target.value)}
          />
          <label
            htmlFor="dob"
            className="block text-sm font-medium text-gray-700"
          >
            Date of birth:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="dob"
            type="date"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            value={dateOfBirth}
            onChange={(e) => setDateOfBirth(e.target.value)}
          />
          <label
            htmlFor="gender"
            className="block text-sm font-medium text-gray-700 mt-2"
          >
            Gender:
            <span className="text-red-500">*</span>
          </label>
          <div className="text-black flex flex-row gap-2">
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                value="MALE"
                id="male"
                name="gender"
                className="mt-[3px]"
                checked={gender === "MALE"}
                onChange={(e) => setGender(e.target.value)}
              />
              <label htmlFor="male">Nam</label>
            </div>
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                value="FEMALE"
                id="female"
                name="gender"
                className="mt-[3px]"
                checked={gender === "FEMALE"}
                onChange={(e) => setGender(e.target.value)}
              />
              <label htmlFor="female">Nữ</label>
            </div>
          </div>
          <label
            htmlFor="identity"
            className="block text-sm font-medium text-gray-700 mt-2"
          >
            Identity card:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="identity"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            disabled
            value={identityCard}
          />
          <label
            htmlFor="email"
            className="block text-sm font-medium text-gray-700"
          >
            Email:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="email"
            type="email"
            value={email}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] h-10 p-2"
            onChange={(e) => setEmail(e.target.value)}
          />
          <label
            htmlFor="address"
            className="block text-sm font-medium text-gray-700"
          >
            Address:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="address"
            type="text"
            value={address}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setAddress(e.target.value)}
          />
          <label
            htmlFor="phone"
            className="block text-sm font-medium text-gray-700"
          >
            Phone:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="phone"
            type="text"
            value={phoneNumber}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setPhoneNumber(e.target.value)}
          />
          <div>
            <label
              htmlFor="file-upload"
              className="block text-sm font-medium text-gray-700 mr-2 mb-3"
            >
              Image:
              <span className="text-red-500">*</span>
            </label>
            <div className="border-[1px] border-solid border-black rounded-[5px] p-1">
              <button
                type="button"
                onClick={() => document.getElementById("file-upload")?.click()}
                className="bg-gray-200 rounded p-1 text-sm mr-4 text-black" // Added mr-4 for margin to the right of the button
              >
                Choose File
              </button>
              <input
                id="file-upload"
                type="file"
                className="hidden"
                onChange={handleFileChange}
              />
              {imageName && (
                <span className="text-sm text-black">{imageName}</span> 
              )}
            </div>
          </div>
          <div className="flex flex-row items-center gap-4 mt-5">
            <button
              type="submit"
              disabled={updated}
              className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white"
            >
              Save
            </button>
            <button
              type="reset"
              className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white"
              onClick={redirectHome}
            >
              Close
            </button>
          </div>
        </form>
        {invalidData && (
          <span className="text-red-500 block self-center font-bold">
            You have to fill in all of the required fields above!
          </span>
        )}
        {updated && (
          <span className="text-green-500 block self-center font-bold">
            Information is successfully updated
          </span>
        )}
      </div>
    </div>
  );
}
