import { useEffect, useState } from "react";
import NewsArticleCard from "./NewsArticleCard";
import type { News } from "../../interfaces/News";
import axios from "../../axios/axios";

const NewsPage = () => {
  const [news, setNews] = useState<News[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  const fetchNews = async () =>{
    const response = await axios.get('/steam/game-news',
      {
        params: 
        {
          steamId: "76561199089642482" 
        }
      }
    );

    setIsLoading(false);
    setNews(response.data);
  }

  useEffect(() =>{
    fetchNews();
  }, [])

  return (
    <div>
      <main id="main-content">
        <section id="latest-section">
          <div className="section-title">Latest</div>
          {isLoading ? 
          <span className="loader"></span> 
          :
          news.length > 0 ?
          news?.map((news, index) => (
          <NewsArticleCard
            key={index}
            news={news}
            imgUrl={`https://cdn.cloudflare.steamstatic.com/steam/apps/${news.appid}/header.jpg`}
          ></NewsArticleCard>
          ))
          :
          <div className="text-white">No news.</div>
          }
        </section>
      </main>
    </div>
  );
};

export default NewsPage;
