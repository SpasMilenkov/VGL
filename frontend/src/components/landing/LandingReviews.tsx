import LandingReviewCard from "./LandingReviewCard";

const LandingReviews = () => {
  const data = [
    {
      imgUrl: "src/assets/images/landing/game1.jpg",
      reviewerName: "Olympic57",
    },
    {
      imgUrl: "src/assets/images/landing/game2.jpg",
      reviewerName: "Beastry",
    },
    {
      imgUrl: "src/assets/images/landing/game3.jpg",
      reviewerName: "Stef",
    },
    {
      imgUrl: "src/assets/images/landing/game4.webp",
      reviewerName: "Gali",
    },
    {
      imgUrl: "src/assets/images/landing/game7.webp",
      reviewerName: "Hyport77",
    },
    {
      imgUrl: "src/assets/images/landing/game6.jpg",
      reviewerName: "Featurelynk",
    },
    {
      imgUrl: "src/assets/images/landing/game8.jpg",
      reviewerName: "Anderha38",
    },
    {
      imgUrl: "src/assets/images/landing/game9.jpg",
      reviewerName: "iiChelagii",
    },
  ];
  return (
    <section id="section-reviews">
      <div className="review-container">
        <div className="reviews-text">20 000+ reviews</div>
        {data.slice(0, 4).map((card) => (
          <LandingReviewCard
            imgUrl={card.imgUrl}
            reviewerName={card.reviewerName}
          ></LandingReviewCard>
        ))}
      </div>
      <div className="review-container">
        {data.slice(4, 8).map((card) => (
          <LandingReviewCard
            imgUrl={card.imgUrl}
            reviewerName={card.reviewerName}
          ></LandingReviewCard>
        ))}
        <div className="reviews-text">From trusted users</div>
      </div>
    </section>
  );
};

export default LandingReviews;
