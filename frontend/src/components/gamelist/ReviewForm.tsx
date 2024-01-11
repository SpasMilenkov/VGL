import { useState } from "react";
import axios from "../../axios/axios";

interface Props {
  setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
  gameId: number;
  gameName: string;
}

const ReviewForm:React.FC<Props> = ({gameId, gameName, setIsModalOpen}) => {
  const [text, setText] = useState<string>('');
  const [rating, setRating] = useState<number>(0);

  const handelModalOpen = () =>{
    setIsModalOpen(false);
    document.body.classList.remove("overflow-y-hidden");
  }

  const handleStarClick = (num: number) =>{
    setRating(num);
  }

  const onSubmit = async () =>{
    const userId = localStorage.getItem('userId');

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
      <h2 className="text-4xl w-full text-center pt-6">Add Review</h2>
      <textarea 
        value={text} 
        onChange={(e) => setText(e.target.value)} 
        className="form-textarea" 
        placeholder="Text"
      />
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
      <button 
        onClick={onSubmit} 
        className="form-button-review ">
          Submit
      </button>
    </div>
  )
}

export default ReviewForm