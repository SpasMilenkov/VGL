import { useState } from 'react'

interface GameProps {
  gameId: number,
  name: string,
  studio: string,
  playtimeForever: number,
  releaseDate: string,
  bannerUrl: string,
  headerUrl: string
}

const GameListCard:React.FC<GameProps> = ({
  gameId, 
  name, 
  studio,
  playtimeForever, 
  releaseDate, 
  bannerUrl, 
  headerUrl
}) => {
  const [imageUrl, setImageUrl] = useState(bannerUrl);

  const handleImageError = () => {
    setImageUrl(headerUrl);
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
          <div className="card-game-title">{name}</div>
          <div className="card-game-studio">{studio}</div>
          <div className="card-game-studio">{playtimeForever}</div>
        </div>
        <div className="card-game-release">{releaseDate}</div>
      </div>
      <div className="card-game-settings">
        <img
          className="settings-icon"
          src="src/assets/images/gamelist/icon-settings.png"
          alt="Settings Button"
        />
      </div>
    </div>
  )
}

export default GameListCard
