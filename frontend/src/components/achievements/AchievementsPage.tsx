import { useEffect, useState } from "react"
import type { Achievement } from "../../interfaces/Achievement"
import AchivementCard from "./AchivementCard";
import axios from "../../axios/axios";

const AchievementsPage = () => {
  const [achievements, setAchievements] = useState<Achievement[]>([]);

  const fetchAchievements = async () =>{
    const response = (await axios.get('/achievements/all')).data;
    setAchievements(response);
  }

  useEffect(() =>{
    fetchAchievements();
  }, [])

  return (
    <div className="p-16">
      <h1 className="gamelist-title">
        Achievements
      </h1>
      {achievements.length > 0 ?
      achievements.map((achievement, index) =>
      <AchivementCard key={index} achievement={achievement}/>
      )
      :
      <div className="text-white">No achievements added.</div>
      }
    </div>
  )
}

export default AchievementsPage
