interface Props {
  newsAuthor: string;
  newsTitle: string;
  newsDesc: string;
  newsTime: string;
  imgUrl: string;
}
const NewsArticleCard: React.FC<Props> = ({
  newsAuthor,
  newsTitle,
  newsDesc,
  newsTime,
  imgUrl,
}: Props) => {
  return (
    <div className="article-card">
      {/* <!-- Article Image --> */}
      <img className="article-card-img" src={imgUrl} alt="Article Image" />
      {/* <!-- Article Info --> */}
      <div className="article-info">
        <div className="article-row1">
          <div className="article-title">{newsTitle}</div>
        </div>
        <div className="article-desc">{newsDesc}</div>
        <div className="article-details">
          <div className="article-author">{newsAuthor}</div>
          <div className="article-time">{newsTime}</div>
        </div>
      </div>
    </div>
  );
};

export default NewsArticleCard;
