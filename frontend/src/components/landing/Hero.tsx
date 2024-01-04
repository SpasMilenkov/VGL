interface Props {
  setState: React.Dispatch<React.SetStateAction<boolean>>;
  setFormIsActive: React.Dispatch<React.SetStateAction<boolean>>;
}
const Hero: React.FC<Props> = ({ setState, setFormIsActive }: Props) => {
  return (
    <div id="section-hero">
      <img
        className="heropage-background"
        src="src/assets/images/landing/gifche.gif"
        alt=""
      />
      <div className="heropage-background-overlay"></div>
      <div className="section-hero-title">
        Discover your next gaming obsession with us
      </div>
      <div className="section-hero-buttons">
        <button
          onClick={() => {
            setState(false);
            setFormIsActive(true);
          }}
          className="button-join-us sign-up"
        >
          Join us &nbsp; &#8599;
        </button>
        <a href="#section-learn-more">
          <button className="button-learn-more">Learn More</button>
        </a>
      </div>
    </div>
  );
};

export default Hero;
