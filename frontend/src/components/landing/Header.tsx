interface Props {
  setState: React.Dispatch<React.SetStateAction<boolean>>;
  setFormIsActive: React.Dispatch<React.SetStateAction<boolean>>;
}

const Header: React.FC<Props> = ({ setState, setFormIsActive }: Props) => {
  return (
    <div className="header-container">
      <div className="header">
        <div className="left-section">
          <img
            src="src/assets/images/landing/logo.png"
            alt="Website Logo"
            className="header-logo"
          />

          <div className="title-text">The Game List</div>
        </div>
        <div className="right-section">
          <button
            onClick={() => {
              setState(true);
              setFormIsActive(true);
            }}
            className="button-login-signup login"
          >
            Login
          </button>
          <button
            onClick={() => {
              setState(false);
              setFormIsActive(true);
            }}
            className="button-login-signup sign-up"
          >
            Sign Up
          </button>
        </div>
      </div>
    </div>
  );
};

export default Header;
