import AccessForm from "./AccessForm";
import BecomePart from "./BecomePart";
import Header from "./Header";
import Hero from "./Hero";
import LandingReviews from "./LandingReviews";
import LearnMore from "./LearnMore";
import { useState, type SetStateAction } from "react";

const Landing = () => {
  const [selectedForm, setSelectedForm] = useState(true);
  const [isFormActive, setIsFormActive] = useState(false);

  return (
    <div id="landing">
      <Header setState={setSelectedForm} setFormIsActive={setIsFormActive} />
      <main className="w-full">
        {isFormActive && (
          <AccessForm
            isLoginOn={selectedForm}
            setState={setSelectedForm}
            isActive={isFormActive}
            setIsActive={setIsFormActive}
          />
        )}
        <Hero setState={setSelectedForm} setFormIsActive={setIsFormActive} />
        <LearnMore
          setState={setSelectedForm}
          setFormIsActive={setIsFormActive}
        />
        <LandingReviews />
        <BecomePart
          setState={setSelectedForm}
          setFormIsActive={setIsFormActive}
        />
      </main>
    </div>
  );
};

export default Landing;
