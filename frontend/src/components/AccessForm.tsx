import React, { useState } from "react";

interface Props {
  isLoginOn: boolean;
  setState: React.Dispatch<React.SetStateAction<boolean>>;
}

const AccessForm: React.FC<Props> = ({ isLoginOn, setState }: Props) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [steamId, setSteamId] = useState("");

  const handleSubmit = () => {};
  return (
    <div className="login-form-wrapper">
      <div
        className={`flex justify-center items-center bg-gray-15 white-98 w-[60%] rounded-2xl h-[70%] ${
          !isLoginOn ? "flex-row-reverse" : ""
        }`}
      >
        <form
          className="
      flex flex-col justify-center 
      items-center bg-gray-15 
      gap-4 p-4 white-98 w-1/2
      rounded-2xl"
        >
          <h2 className="form-h2 white-98">
            {isLoginOn ? "Login" : "Register"}
          </h2>
          <input
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="form-input"
            type="email"
            placeholder="Email"
          />
          <input
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="form-input"
            type="password"
            placeholder="Password"
          />
          {!isLoginOn && (
            <>
              <input
                value={name}
                onChange={(e) => setName(e.target.value)}
                className="form-input"
                type="text"
                placeholder="Name"
              />
              <input
                value={steamId}
                onChange={(e) => setSteamId(e.target.value)}
                className="form-input"
                type="text"
                placeholder="SteamID"
              />
            </>
          )}

          <button onClick={handleSubmit} className="form-button">
            {isLoginOn ? "Login" : "Sign Up"}
          </button>
        </form>
        {/* Unselected Container */}
        <div
          className={`flex justify-center items-center flex-col gap-[15%] bg-gradient-to-tr from-[#FF5C00] to-[#FF9838] w-1/2 h-full  ${
            isLoginOn ? "rounded-r-2xl" : "rounded-l-2xl"
          }`}
        >
          <div className="flex flex-col justify-center items-center w-[65%] gap-4">
            <h2 className="font-medium text-5xl text-center">
              {isLoginOn ? "Hello!" : "Welcome back!"}
            </h2>
            <h3 className="font-normal text-xl text-center">
              {isLoginOn
                ? "Are you here for the first time? Start your journey with us."
                : "To keep connected with us please login with your pesonal info."}
            </h3>
          </div>
          <button
            className="unselected-button"
            onClick={() => setState(!isLoginOn)}
          >
            {isLoginOn ? "Sign Up" : "Login"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default AccessForm;
