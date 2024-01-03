import { useState } from "react"
import "../styles/global.css"

const AccessForm = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [steamId, setSteamId] = useState('');

  const handleSubmit = () =>{
    
  }

  return (
    <div className="
      flex justify-center 
      items-center bg-gray-15 
      gap-4 p-4 white-98 w-1/2 rounded-2xl h-4/6">
      <form className="
        flex flex-col justify-center 
        items-center bg-gray-15 
        gap-4 p-4 white-98 w-1/2">
        <h2 className="form-h2 white-98">Login</h2>
        <input 
          value={email} 
          className="form-input"
          onChange={(e) => setEmail(e.target.value)} 
          type="email" 
          placeholder="Email" />

        <input 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
          className="form-input"
          type="password" 
          placeholder="Password" />

        <button 
          onClick={handleSubmit}
          className="form-button">
            Login
        </button>
      </form>
      <form className="
      flex flex-col justify-center 
      items-center bg-gray-15 
      gap-4 p-4 white-98 w-1/2">
        <h2 className="form-h2 white-98">Register</h2>
      
        <input 
          value={name} 
          onChange={(e) => setName(e.target.value)} 
          className="form-input"
          type="text" 
          placeholder="Name" />

        <input 
          value={email} 
          onChange={(e) => setEmail(e.target.value)} 
          className="form-input"
          type="email" 
          placeholder="Email" />

        <input 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
          className="form-input"
          type="password" 
          placeholder="Password" />

        <input 
          value={steamId} 
          onChange={(e) => setSteamId(e.target.value)} 
          className="form-input"
          type="text" 
          placeholder="SteamID" />     

        <button 
          onClick={handleSubmit}
          className="form-button">
            Sign Up
        </button>
      </form>
    </div>
  )
}

export default AccessForm
