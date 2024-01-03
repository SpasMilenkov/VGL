import { useState } from "react";

interface Props {
  setState: React.Dispatch<React.SetStateAction<boolean>>;
}
const Header: React.FC<Props> = ({ setState }: Props) => {
  return (
    <div id="landing-header">
      <button onClick={() => setState(true)}>Login</button>
      <button onClick={() => setState(false)}>Sign Up</button>
    </div>
  );
};

export default Header;
