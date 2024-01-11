import { useEffect, useState } from "react";
import axios from "../../axios/axios";
import GameListCard from "./GameListCard"
import type { Game } from "../../interfaces/Game";

const GameListPage = () => {
  const [games, setGames] = useState<Game[]>([]);
  const [randomGame, setRandomGame] = useState<Game | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  const fetchGames = async () =>{
    const response = await axios.get('/steam/get-owned-games',
    {
      params: 
      {
        steamId: "76561199089642482" 
      }
    });

    const data = response.data;

    setGames(data);
    setIsLoading(false);
    setRandomGame(data[Math.floor(Math.random()*data.length)]);
  }

  useEffect(() =>{
    fetchGames();
  }, [])

  
  return (
    <div className="w-full text-white">
      <main id="main-content">
        <div className="gamelist-navbar">
          <h1 className="gamelist-title">
            Game List
          </h1>
        </div>
        <section id="section-already-played">
          <div className="already-played-opening">
            {randomGame?.trailerUrl ? 
            <video
              className="already-played-img" 
              src={randomGame?.trailerUrl}
              autoPlay muted>
            </video>
            :
            randomGame?.bannerUrl 
            ? 
            <img src={randomGame?.bannerUrl} alt="GameBanner" />
            :
            <img src={randomGame?.headerUrl} alt="GameHeader" />
            }
            <div className="opening-overlay"></div>
            <div className="section-opening-container">
              <div className="section-title">Recently Popular</div>
              <div className="section-update">
                <span className="section-date text-[2rem]">{randomGame?.name}</span>
              </div>
            </div>
          </div>
          <div className="already-played-filter">
            <div className="already-icon-container">
              <img
                className="filter-icon"
                src="src/assets/images/gamelist/icon-filter.png"
                alt="Filter Button"
              />
            </div>
          </div>
          <div className="already-played-cards">
            {isLoading ? 
            <span className="loader"></span> 
            :
            games.length > 0 ?
            games.map((game, index) => 
            <GameListCard key={index} game={game}/>
            )
            : 
            <div>No games in list.</div>
            }
          </div>
        </section>
      </main>
    </div>
  )
}

export default GameListPage
