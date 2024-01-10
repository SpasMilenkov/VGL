import { useEffect, useState } from 'react'
import type { Game } from '../../interfaces/Game';
import axios from '../../axios/axios';

interface GameProps {
  game: Game
  played: boolean
}

const GameListCard:React.FC<GameProps> = ({game, played}) => {
  const [imageUrl, setImageUrl] = useState(game.bannerUrl);

  useEffect(() =>{
    setImageUrl(game.bannerUrl);
  }, [game.bannerUrl])

  const handleDelete = async () =>{
    //await axios.delete('/games/delete');
  }

  const handleImageError = () => {
    setImageUrl(game.headerUrl);
  };

  return (
    <div className="card">
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
      <div className="card-game-settings">
        {played &&
        <img
          className="settings-icon"
          onClick={handleDelete}
          src="src/assets/images/landing/close-icon.png"
          alt="Settings Button"
        />
        }
      </div>
    </div>
  )
}

export default GameListCard
