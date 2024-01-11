import { useEffect, useState } from 'react'
import type { Game } from '../../interfaces/Game';

interface GameProps {
  game: Game
}

const GameListCard:React.FC<GameProps> = ({game}) => {
  const [imageUrl, setImageUrl] = useState(game.bannerUrl);

  useEffect(() =>{
    setImageUrl(game.bannerUrl);
  }, [game.bannerUrl])

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
    </div>
  )
}

export default GameListCard
