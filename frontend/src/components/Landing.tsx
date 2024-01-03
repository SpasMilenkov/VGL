import AccessForm from "../components/AccessForm";
import Header from "../components/Header";
import { useState } from "react";

const Landing = () => {
  const [selectedForm, setSelectedForm] = useState(true);

  return (
    <main id="landing-main">
      <Header setState={setSelectedForm}></Header>
      <AccessForm
        isLoginOn={selectedForm}
        setState={setSelectedForm}
      ></AccessForm>
    </main>
  );
};

export default Landing;
