import { useEffect } from "react";
import Register from "./Register";
import Login from "./Login";

interface Props {
  isLoginOn: boolean;
  setState: React.Dispatch<React.SetStateAction<boolean>>;
  setIsActive: React.Dispatch<React.SetStateAction<boolean>>;
};

const AccessForm: React.FC<Props> = ({isLoginOn, setState, setIsActive}) => {

  useEffect(() => {
    document.body.classList.add("overflow-y-hidden");

    return () => {
      document.body.classList.remove("overflow-y-hidden");
    };
  }, []);

  return (
    <div className={`login-form-wrapper`}>
      <div
        className={`relative flex justify-center items-center bg-gray-15 white-98 w-[60%] rounded-2xl h-[70%] ${
          !isLoginOn ? "flex-row-reverse" : ""
        }`}
      >
        <img 
          onClick={() => setIsActive(false)} 
          className="login-close" 
          src="src/assets/images/landing/close-icon.png" alt="Close">
        </img>

        {isLoginOn ? <Login/> : <Register/>}

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
