import { useEffect, useState } from "react";
import axios from "../../axios/axios";
import GameListCard from "./GameListCard"
import type { Game } from "../../interfaces/Game";

const GameListPage = () => {
  const [games, setGames] = useState<Game[]>([]);
  const [randomGame, setRandomGame] = useState<Game | null>(null);
  const [activeButton, setActiveButton] = useState(1);
  const [isPlayedGames, setIsPlayedGames] = useState(true);

  const fetchAlreadyPlayedGames = async () =>{
    //const response = (await axios.get('/steam/get-owned-games')).data;
    //setGames(response);
    //setRandomGame(response[Math.floor(Math.random()*response.length)]);
  }

  const fetchToBePlayedGames = async () =>{
    //const response = await axios.get('/steam/get-owned-games').data;
    //setGames(response);
    //setRandomGame(response[Math.floor(Math.random()*response.length)]);
  }

  useEffect(() =>{
    if (activeButton === 2) {
      fetchToBePlayedGames();
      setIsPlayedGames(true);
    } else if(activeButton === 1) {
      fetchAlreadyPlayedGames();
      setIsPlayedGames(false);
    }
  }, [activeButton])

  const handleClick = (btn: number) =>{
    setActiveButton(btn);
  }
  
  return (
    <div className="w-full text-white">
      <main id="main-content">
        <div className="gamelist-navbar">
          <ul className="gamelist-nav">
            <li 
              onClick={() => handleClick(1)} 
              className={`gamelist-item ${activeButton === 1 ? "active" : ""}`}>
                Already Played
            </li>
            <li 
              onClick={() => handleClick(2)} 
              className={`gamelist-item ${activeButton === 2 ? "active" : ""}`}>
                To be played
            </li>
          </ul>
        </div>
        <section id="section-already-played">
          {activeButton === 1 &&
          <div className="already-played-opening">
            <video
              className="already-played-img" 
              src={randomGame?.trailerUrl}
              autoPlay muted>
            </video>
            <div className="opening-overlay"></div>
            <div className="section-opening-container">
              <div className="section-title">Recently Popular</div>
              <div className="section-update">
                <span className="section-date text-[2rem]">{randomGame?.name}</span>
              </div>
            </div>
          </div>
          }
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
            {games.length > 0 ?
            games.map((game, index) => 
            <GameListCard key={index} game={game} played={isPlayedGames}/>
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
