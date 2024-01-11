import { useEffect, useState } from 'react'
import type { Game } from '../../interfaces/Game';

interface GameProps {
  game: Game
  setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
  setGameId: React.Dispatch<React.SetStateAction<number>>;
  setGameName: React.Dispatch<React.SetStateAction<string>>;
}

const GameListCard:React.FC<GameProps> = ({game, setGameId, setGameName, setIsModalOpen}) => {
  const [imageUrl, setImageUrl] = useState(game.bannerUrl);

  useEffect(() =>{
    setImageUrl(game.bannerUrl);
  }, [game.bannerUrl])

  const handleImageError = () => {
    setImageUrl(game.headerUrl);
  };

  const handleModalOpen = () =>{
    setIsModalOpen(true);
    setGameId(game.gameId);
    setGameName(game.name);
    document.body.classList.add("overflow-y-hidden");
  }

  return (
    <div className="card relative">
      <div className="card-game-container">
        <img
          className="card-game-cover"
          src={imageUrl}
          alt="Game Cover"
          onError={handleImageError}
        />
      </div>
      <div className="card-game-info-container">
        <div className="card-game-main-info-container">
          <div className="card-game-title">{game.name}</div>
          <div className="card-game-studio">{game.studio}</div>
        </div>
      </div>
      <img 
        title='Add Review'
        onClick={handleModalOpen}
        className='w-[2rem] h-[2rem] cursor-pointer absolute right-6 top-6' 
        src="src/assets/icons/review-icon.svg" 
        alt="ReviewIcon" />
    </div>
  )
}

export default GameListCard