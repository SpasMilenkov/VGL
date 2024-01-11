import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import axios from "../../axios/axios";

const loginSchema = z.object({
  email: z.string().email().trim(),
  password: z.string().min(1, "Password is required")
})
  
type Inputs = z.infer<typeof loginSchema>

const Login = () => {
  const [error, setError] = useState('');

  const { register, handleSubmit, formState: { errors }, watch } = useForm<Inputs>({
    resolver: zodResolver(loginSchema)
  });

  const watchEmail = watch("email");
  const watchPassword = watch("password");

  useEffect(() =>{
    setError('');
  },[watchEmail, watchPassword])

  const onSubmit = async (data: Inputs) => {
    try {
      const response = await axios.post('auth/login',
        JSON.stringify(data),
        {
          headers: {'Content-Type': 'application/json'}
        }
      );

      localStorage.setItem('userId', response.data);
      window.location.href="/home";

    } catch (error : any) {
      if(!error?.response){
        setError('No response');
      }else if(error.response?.status === 400){
        setError('An error occurred');
      }else if(error.response?.status === 401){
        setError('Incorrect email or password');
      }else{
        setError('Error');
      }
    }
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      noValidate
      className="flex flex-col justify-center items-center bg-gray-15 
      gap-4 p-4 white-98 w-1/2 rounded-2xl"
      >
      <h2 className="form-h2 white-98">
        Login
      </h2>
      <input className="form-input" type="email" placeholder="Email" {...register("email")}/>
      <p className="text-orange-600">{errors.email?.message}</p>
      <input className="form-input" type="password" placeholder="Password" {...register("password")}/>
      <p className="text-orange-600">{errors.password?.message}</p>
      
      <button className="form-button">
        Login
      </button>
      <p className="text-orange-600">{error}</p>
    </form>
  )
}

export default Login