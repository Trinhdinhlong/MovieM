"use client";
import axiosInstance from "@/axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

interface TypeMovie {
  id: number;
  typeName: string;
  createdDate: string | null; // `null` is explicitly allowed as per the JSON structure
  updatedTime: string | null;
}

interface ShowTime {
  id: number;
  startTime: string;
  endTime: string;
}

interface RoomDetails {
  id: number;
  nameRoom: string;
  seatQuantity: number;
}

interface MovieDetails {
  id: number;
  content: string;
  movieNameEnglish: string;
  movieNameVN: string;
  actor: string;
  director: string;
  duration: number;
  movieProductionCompany: string;
  startedDate: string;
  endDate: string;
  imageURL: string;
  createdDate: string;
  updatedTime: string;
  version: string;
  typeMovies: TypeMovie[];
  showTimes: ShowTime[];
}

export default function Home() {
  const router = useRouter();
  const [movNameEn, setMovNameEn] = useState("");
  const [movNameVie, setMovNameVie] = useState("");
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [actor, setActor] = useState("");
  const [movProductionComp, setMovProductionComp] = useState("");
  const [director, setDirector] = useState("");
  const [duration, setDuration] = useState("");
  const [version, setVersion] = useState("");
  const [movType, setMovType] = useState<string[]>([]);
  const [room, setRoom] = useState("1");
  const [schedule, setSchedule] = useState<string[]>([]);
  const [content, setContent] = useState("");
  const [rooms, setRooms] = useState<RoomDetails[]>([]);
  const [fileName, setFileName] = useState<File>();
  const generateTimeSlots = (
    duration: any,
    interval: any,
    startHour: any,
    endHour: any
  ) => {
    const slots = [];
    let currentTime = new Date().setHours(startHour, 0, 0, 0);
    const endTime = new Date().setHours(endHour, 0, 0, 0);
    while (currentTime + Number(duration) * 60000 < endTime) {
      const slotTime = new Date(currentTime);
      slots.push(
        slotTime.toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
          hour12: false,
        })
      );
      currentTime += (Number(duration) + Number(interval)) * 60000;
    }
    return slots;
  };

  useEffect(() => {
    setSchedule([]);
  }, [duration]);

  const handleCheckBoxSchedule = (e: any) => {
    const { value, checked } = e.target;
    setSchedule((prev) => {
      if (checked) {
        return [...prev, value];
      } else {
        return prev.filter((time) => time !== value);
      }
    });
  };

  useEffect(() => {
    axiosInstance.get(`/api/room`).then((response) => {
      setRooms(response.data);
    });
  }, []);

  const slots = generateTimeSlots(Number(duration), 30, 8, 24);

  function handleCreate(e: any) {
    e.preventDefault();
    axiosInstance
      .post("/api/movie", {
        content: content,
        movieNameEnglish: movNameEn,
        movieNameVN: movNameVie,
        actor: actor,
        director: director,
        duration: duration,
        movieProductionCompany: movProductionComp,
        startedDate: fromDate,
        endDate: toDate,
        version: version,
        imageURL: fileName?.name,
        typeMovieId: movType,
        startTime: schedule,
        roomId: room,
      })
      .then((response) => {
        router.push("/admin/dashboard/movies");
      });
  }

  function checkFormFilled() {
    const allStringsFilled =
      movNameEn &&
      movNameVie &&
      fromDate &&
      toDate &&
      actor &&
      movProductionComp &&
      director &&
      duration &&
      room &&
      content;
    const allArraysFilled = movType.length > 0 && schedule.length > 0;
    const fileSelected = fileName !== undefined;
    return allStringsFilled && allArraysFilled && fileSelected;
  }

  const handleFileChange = (e: any) => {
    setFileName(e.target.files[0]);
    if (e.target.files[0]) {
      const form = new FormData();
      form.append("imageFile", e.target.files[0]);
      axiosInstance.post("/images", form);
    }
  };

  function handleCheckBoxType(e: any) {
    const value = e.target.value;
    if (e.target.checked) {
      setMovType((prev) => [...prev, value]);
    } else {
      setMovType((prev) => prev.filter((type) => type !== value));
    }
  }

  return (
    <div className="bg-[#EFF0F3] w-full h-full overflow-auto flex flex-col items-center text-black overflow-auto">
      <form
        className="w-[95%] bg-white m-10 p-10 flex flex-col gap-3"
        onSubmit={(e) => handleCreate(e)}
      >
        <span className="block self-center font-bold text-[1.5rem]">
          ADD MOVIE
        </span>
        <label
          htmlFor="movie_name"
          className="block text-sm font-medium text-gray-700"
        >
          Movie name (ENG):
          <span className="text-red-500">*</span>
        </label>
        <input
          id="movie_name"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setMovNameEn(e.target.value)}
        />
        <label
          htmlFor="movie_name_VN"
          className="block text-sm font-medium text-gray-700"
        >
          Movie name (VN):
          <span className="text-red-500">*</span>
        </label>
        <input
          id="movie_name_VN"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setMovNameVie(e.target.value)}
        />
        <label
          htmlFor="from_date"
          className="block text-sm font-medium text-gray-700"
        >
          From date:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="from_date"
          type="date"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setFromDate(e.target.value)}
        />
        <label
          htmlFor="to_date"
          className="block text-sm font-medium text-gray-700"
        >
          To date:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="to_date"
          type="date"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setToDate(e.target.value)}
        />
        <label
          htmlFor="actor"
          className="block text-sm font-medium text-gray-700"
        >
          Actor:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="actor"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setActor(e.target.value)}
        />
        <label
          htmlFor="movie_production_company"
          className="block text-sm font-medium text-gray-700"
        >
          Movie Production Company:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="movie_production_company"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setMovProductionComp(e.target.value)}
        />
        <label
          htmlFor="director"
          className="block text-sm font-medium text-gray-700"
        >
          Director:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="director"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setDirector(e.target.value)}
        />
        <label
          htmlFor="duration"
          className="block text-sm font-medium text-gray-700"
        >
          Duration:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="duration"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setDuration(e.target.value)}
        />
        <label
          htmlFor="duration"
          className="block text-sm font-medium text-gray-700"
        >
          Version:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="duration"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setVersion(e.target.value)}
        />
        <label
          htmlFor="version"
          className="block text-sm font-medium text-gray-700 mt-2"
        >
          Type:
          <span className="text-red-500">*</span>
        </label>
        <div className="text-black w-[60%] gap-7 flex flex-row flex-wrap mt-6">
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hd"
              type="checkbox"
              value="1"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="hd">Hành động</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh"
              type="checkbox"
              value="5"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="hh">Hài hước</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="lm"
              type="checkbox"
              value="9"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="lm">Lãng mạn</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="tc"
              type="checkbox"
              value="2"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="tc">Tình cảm</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="ct"
              type="checkbox"
              value="6"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="ct">Chiến tranh</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="kh"
              type="checkbox"
              value="10"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="kh">Kiếm hiệp</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="an"
              type="checkbox"
              value="3"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="an">Âm nhạc</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="kd"
              type="checkbox"
              value="7"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="kd">Kinh dị</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="tl"
              type="checkbox"
              value="4"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="tl">Tâm lý 18+</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh2"
              type="checkbox"
              value="8"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="hh2">Hoạt hình</label>
          </div>
        </div>
        <label
          htmlFor="sched"
          className="block text-sm font-medium text-gray-700"
        >
          Cinema room:
          <span className="text-red-500">*</span>
        </label>
        <select
          id="sched"
          value={room}
          className="text-black h-10 rounded-[5px] border-[1px] border-black border-solid p-2"
          onChange={(e) => setRoom(e.target.value)}
        >
          {rooms.map((el) => (
            <option value={el.id}>{el.nameRoom}</option>
          ))}
        </select>
        <label
          htmlFor="version"
          className="block text-sm font-medium text-gray-700 mt-2"
        >
          Schedule:
          <span className="text-red-500">*</span>
        </label>
        <div className="text-black w-[60%] gap-7 flex flex-row flex-wrap mt-5">
          {slots?.map((time, index) => (
            <div
              key={index}
              className="w-[calc(30%)] flex flex-row items-center gap-2"
            >
              <input
                id={`time_${index}`}
                type="checkbox"
                value={time}
                onChange={handleCheckBoxSchedule}
              />
              <label htmlFor={`time_${index}`}>{time}</label>
            </div>
          ))}
        </div>
        <label
          htmlFor="content"
          className="block text-sm font-medium text-gray-700"
        >
          Content:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="content"
          type="textarea"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] h-10 p-2"
          onChange={(e) => setContent(e.target.value)}
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
              onChange={(e) => handleFileChange(e)}
            />
            {fileName && (
              <span className="text-sm text-black">{fileName.name}</span>
            )}
          </div>
        </div>
        <div className="flex flex-row items-center gap-4 mt-5">
          <button
            type="submit"
            className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white"
          >
            Save
          </button>
          <button
            type="reset"
            className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white"
          >
            Close
          </button>
        </div>
      </form>
    </div>
  );
}
