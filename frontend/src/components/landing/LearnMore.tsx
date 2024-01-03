const LearnMore = () => {
  return (
    <section id="section-learn-more">
      <img
        className="learn-more-background"
        src="src/assets/images/landing/background2.png"
        alt=""
      />

      <div className="test">
        <div className="learn-more-title width-max">Collect Discover Connect</div>
      </div>

      <div className="learn-more-info">
        <div className="learn-more-title">Keep track of your games</div>
        <div className="learn-more-details">
          From nostalgic classics to the latest blockbusters, we've curated a
          treasure trove of gaming experiences for every taste and preference.
        </div>
        <div className="learn-more-start sign-up">
          Start your journey &nbsp;<span className="learn-more-arrow"
            >&#8594;</span>
        </div>
      </div>
    </section>
  );
};

export default LearnMore
