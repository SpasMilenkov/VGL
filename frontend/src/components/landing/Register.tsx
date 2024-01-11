import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import axios from "../../axios/axios";
import { useEffect, useState } from "react";

const registerSchema = z.object({
  nickName: z.string().trim().min(2, "Username must be at least 2 characters"),
  email: z.string().email().trim(),
  steamId: z.string().min(1, "SteamID is required"),
  password: z.string().min(4, "Password must be at least 4 characters"),
  confirmPassword: z.string(),
}).refine((data) => data.password === data.confirmPassword,{
  message: "Passwords do not match",
  path: ["confirmPassword"],
})

type Inputs = z.infer<typeof registerSchema>

const Register = () => {
  const [error, setError] = useState('');

  const { register, handleSubmit, formState: { errors }, watch } = useForm<Inputs>({
    resolver: zodResolver(registerSchema)
  });

  const watchName = watch("nickName");
  const watchEmail = watch("email");
  const watchPassword = watch("password");
  const watchConfirmPassword = watch("confirmPassword");
  const watchSteamId = watch("steamId");

  useEffect(() =>{
    setError('');
  },[watchName, watchEmail, watchPassword, watchConfirmPassword, watchSteamId])

  const onSubmit = async (data: Inputs) => {
    const { confirmPassword, ...request} = data;

    try {
      const response = await axios.post('auth/register',
      JSON.stringify(request),
      {
        headers: {'Content-Type': 'application/json'}
      }
    );
    } catch (error : any) {
      if(!error?.response){
        setError('No response');
      }else if(error.response?.status === 400){
        setError('An error occurred');
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
        Register
      </h2>
      <input className="form-input" type="text" placeholder="Name" {...register("nickName")}/>
      <p className="text-orange-600">{errors.nickName?.message}</p>
      <input className="form-input" type="email" placeholder="Email" {...register("email")}/>
      <p className="text-orange-600">{errors.email?.message}</p>
      <input className="form-input" type="password" placeholder="Password" {...register("password")}/>
      <p className="text-orange-600">{errors.password?.message}</p>
      <input className="form-input" type="password" placeholder="Confirm Password" {...register("confirmPassword")}/>
      <p className="text-orange-600">{errors.confirmPassword?.message}</p>
      <input className="form-input"  type="text" placeholder="SteamID" {...register("steamId")}/>
      <p className="text-orange-600">{errors.steamId?.message}</p>

      <button className="form-button">
        Sign Up
      </button>
      <p className="text-orange-600">{error}</p>
    </form>
  )
}

export default Register
