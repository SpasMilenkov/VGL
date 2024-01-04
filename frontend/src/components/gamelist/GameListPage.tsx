import GameListCard from "./GameListCard"

const GameListPage = () => {
  const data = [
    {
      "gameId": 220,
      "name": "Half-Life 2",
      "playtimeForever": 0,
      "studio": "ayo",
      "releaseDate": "12.12.1212",
      "bannerUrl": "https://cdn.akamai.steamstatic.com/steam/apps/220/page.bg.jpg",
      "headerUrl": "https://cdn.akamai.steamstatic.com/steam/apps/220/header.jpg"
  },
  {
      "gameId": 20900,
      "name": "The Witcher: Enhanced Edition",
      "playtimeForever": 0,
      "studio": "ayo",
      "releaseDate": "12.12.1212",
      "bannerUrl": "https://cdn.akamai.steamstatic.com/steam/apps/20900/page.bg.jpg",
      "headerUrl": "https://cdn.akamai.steamstatic.com/steam/apps/20900/header.jpg"
  },
  {
      "gameId": 17460,
      "name": "Mass Effect (2007)",
      "playtimeForever": 0,
      "studio": "ayo",
      "releaseDate": "12.12.1212",
      "bannerUrl": "https://cdn.akamai.steamstatic.com/steam/apps/17460/page.bg.jpg",
      "headerUrl": "https://cdn.akamai.steamstatic.com/steam/apps/17460/header.jpg"
  },
  ]

  return (
    <div className="w-full text-white">
      <main id="main-content">
        <section id="section-already-played">
          <div className="already-played-opening">
            <img
              className="already-played-img"
              src="https://cdn.akamai.steamstatic.com/steam/apps/17460/page.bg.jpg"
              alt="Opening Image"
            />
            <div className="opening-overlay"></div>
            <div className="section-opening-container">
              <div className="section-title">Most Played</div>
              <div className="section-update">
                Last updated: <span className="section-date">7 May 2023</span>
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
            {data.map((card, index) => 
              <GameListCard key={index} {...card}/>
            )}
          </div>
        </section>
      </main>
    </div>
  )
}

export default GameListPage
