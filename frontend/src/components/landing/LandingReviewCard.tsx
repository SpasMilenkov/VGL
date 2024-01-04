interface Props {
  imgUrl: string;
  reviewerName: string;
}
const LandingReviewCard: React.FC<Props> = ({
  imgUrl,
  reviewerName,
}: Props) => {
  return (
    <div className="review-card">
      <img src={imgUrl} alt="" className="review-img" />
      <div className="review-card-text glass">{reviewerName}</div>
    </div>
  );
};

export default LandingReviewCard;
