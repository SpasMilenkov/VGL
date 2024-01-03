import AccessForm from "./AccessForm";
import BecomePart from "./BecomePart";
import Header from "./Header";
import Hero from "./Hero";
import LandingReviews from "./LandingReviews";
import LearnMore from "./LearnMore";
import { useState } from "react";

const Landing = () => {
  const [selectedForm, setSelectedForm] = useState(true);

  return (
    <div id="landing">
      <Header setState={setSelectedForm}/>
      <main className="w-full">
        <AccessForm isLoginOn={selectedForm} setState={setSelectedForm}/>
        <Hero/>
        <LearnMore/>
        <LandingReviews/>
        <BecomePart/>
      </main>
    </div>
  );
};

export default Landing
