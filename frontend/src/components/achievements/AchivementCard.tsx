import { useEffect, useState } from "react"
import type { Achievement } from "../../interfaces/Achievement"

interface AchievementProps{
  achievement: Achievement
}

const AchivementCard:React.FC<AchievementProps> = ({achievement}) => {
  const [icon, setIcon] = useState<Blob | undefined>(undefined);

  const fetchIcon = async () =>{
    //const response = (await axios.get('/achievements')).data;
    //setAchievements(response.data);
  }

  useEffect(() =>{
    //fetchIcon();
  }, [])

  return (
    <div className="latest-card">
      <div className="card-left-section">
        <div className="latest-img-container">
          <img
            className="latest-image text-white"
            // src={URL.createObjectURL(icon)}
            alt="Achievement Icon"
          />
          <div className="latest-overlay"></div>
        </div>
      </div>
      <div className="card-right-section">
        <div className="latest-review-container">
          <div className="latest-review">
            {achievement.title}
          </div>
        </div>
        <div className="review-info">
          <div className="review-name">{achievement.difficulty}</div>
          <div className="review-name">{achievement.gameTitle}</div>
          <div className="review-name">{achievement.username}</div>
        </div>
      </div>
    </div>
  )
}

export default AchivementCard
