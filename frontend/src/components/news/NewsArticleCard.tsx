import type { News } from "../../interfaces/News";

interface Props {
  news: News;
  imgUrl: string;
}

const NewsArticleCard: React.FC<Props> = ({news, imgUrl}) => {
  return (
    <div className="article-card">
      {/* <!-- Article Image --> */}
      <a className="article-card-link" href={news.url}>
        <img className="article-card-img" src={imgUrl} alt="Article Image" />
      </a>
      
      {/* <!-- Article Info --> */}
      <div className="article-info">
        <div className="article-row1">
          <div className="article-title">{news.title}</div>
        </div>
        <div className="article-desc">{news.description}</div>
        <div className="article-details">
          <div className="article-author">{news.author}</div>
        </div>
      </div>
    </div>
  );
};

export default NewsArticleCard;
