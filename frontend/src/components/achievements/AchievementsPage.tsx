import { useEffect, useState } from "react"
import type { Achievement } from "../../interfaces/Achievement"
import AchivementCard from "./AchivementCard";
import axios from "../../axios/axios";

const AchievementsPage = () => {
  const [achievements, setAchievements] = useState<Achivement[]>([]);

  const data = [
    {
      username: "neshto",
      title: "title",
      description: "descr",
      difficulty: "diff",
      gameTitle: "game",
      image: undefined
    }
  ]

  const fetchAchievements = async () =>{
    //const response = (await axios.get('/achievements')).data;
    setAchievements(data);
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
      achievements.map(achievement =>
      <AchivementCard achievement={achievement}/>
      )
      :
      <div className="text-white">No achievements added.</div>
      }
    </div>
  )
}

export default AchievementsPage
