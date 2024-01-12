import { useState } from "react";
import axios from "../../axios/axios";

interface Props {
  setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
  gameId: number;
  gameName: string;
  modalType: string;
}

const Modal:React.FC<Props> = ({gameId, gameName, setIsModalOpen, modalType}) => {
  const [title, setTitle] = useState<string>('');
  const [text, setText] = useState<string>('');
  const [rating, setRating] = useState<number>(0);
  const [selectedDifficulty, setSelectedDifficulty] = useState<string>('');
  const [image, setImage] = useState<File | null>(null);
  const [imageName, setImageName] = useState('');

  const handelModalOpen = () =>{
    setIsModalOpen(false);
    document.body.classList.remove("overflow-y-hidden");
  }

  const handleStarClick = (num: number) =>{
    setRating(num);
  }

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files[0];
    setImage(file);
    setImageName(file.name);
  };

  const onSubmit = async () =>{
    const userId = localStorage.getItem('userId');

    if(modalType === 'review'){
      const data = {
        id: 1,
        userId,
        gameId,
        steamId: gameId.toString(),
        title: gameName,
        text, 
        rating,
      }

      await axios.post('/reviews', data);
    }

    if(modalType === 'achievement'){
      const requestData = new FormData();
      requestData.append('gameId', gameId);
      requestData.append('userId', userId);
      requestData.append('Description', text);  
      requestData.append('difficulty', selectedDifficulty);
      requestData.append('title', title);

      if(image !== null){
        requestData.append('image', image);
      }

      await axios.post('/achievements/create', requestData);
    }

    handelModalOpen();
  }

  return (
    <div className="bg-[#262626] 
      flex flex-col items-center gap-4 
      w-1/2 h-[45rem] fixed top-24 
      left-1/4 p-8 rounded-3xl z-50">
      <img 
        onClick={handelModalOpen} 
        className="w-12 absolute top-2 right-2 z-50 cursor-pointer" 
        src="src/assets/icons/close-button-icon.svg" 
        alt="Exit"
      />
      <h2 className="text-4xl w-full text-center pt-6">{modalType === 'review' ? 'Add Review' : "Add Achievement"}</h2>
      {modalType === 'achievement' && 
        <input 
          value={title} 
          onChange={(e) => setTitle(e.target.value)} 
          className="form-input" 
          type="text" 
          placeholder="Title"
        /> 
      }
      <textarea 
        value={text} 
        onChange={(e) => setText(e.target.value)} 
        className="form-textarea" 
        placeholder="Text"
      />
      {modalType === 'review' ?
      <div className="flex items-center gap-4 text-2xl">
        Rating: 
        <div className="flex">
        {Array.from({ length: 5 }, (_, index) => 
          <img 
            key={index} 
            onClick={() => handleStarClick(index)}
            className="w-[2rem] cursor-pointer" 
            src={rating < index ? 'src/assets/icons/star.svg' : 'src/assets/icons/solid-star.svg'}/>
        )}
        </div>
      </div>
      :
      <>
        <div className="flex gap-8">
          <button 
            onClick={() => setSelectedDifficulty('Easy')} 
            className={`form-button p-4 ${selectedDifficulty === 'Easy' ? "border-solid border-4" : ""}`}>
              Easy
          </button>
          <button 
            onClick={() => setSelectedDifficulty('Medium')} 
            className={`form-button p-4 ${selectedDifficulty === 'Medium' ? "border-solid border-4" : ""}`}>
              Medium
          </button>
          <button 
            onClick={() => setSelectedDifficulty('Hard')} 
            className={`form-button p-4 ${selectedDifficulty === 'Hard' ? "border-solid border-4" : ""}`}>
              Hard
          </button>
        </div>
        <div className="p-4">
          <input
            id="file"
            type="file"
            onChange={handleImageChange}
            className="hidden"
          />
          <label
            htmlFor="file"
            className="bg-[#ff5c00] 
            hover:bg-white hover:text-[#ff5c00] 
            transition text-white font-bold py-2 px-4 rounded cursor-pointer"
            >
              Upload Icon
          </label>
        </div>
        {image && <label>{imageName}</label>}
      </>
      }
      <button 
        onClick={onSubmit} 
        className="form-button-review ">
          Submit
      </button>
    </div>
  )
}

export default Modal
