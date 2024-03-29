interface Props {
  setState: React.Dispatch<React.SetStateAction<boolean>>;
  setFormIsActive: React.Dispatch<React.SetStateAction<boolean>>;
}
const BecomePart: React.FC<Props> = ({ setState, setFormIsActive }: Props) => {
  return (
    <section id="section-become-part">
      <img
        className="heropage-background"
        src="src/assets/images/landing/gifche2.gif"
        alt=""
      />
      <div className="becomepart-background-overlay"></div>
      <div className="section-hero-title">Become a part of our community</div>
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
      </div>
    </section>
  );
};

export default BecomePart;
