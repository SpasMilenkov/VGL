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
  const { register, handleSubmit, formState: { errors } } = useForm<Inputs>({
    resolver: zodResolver(loginSchema)
  });

  const onSubmit = async (data: Inputs) => {
    const response = await axios.post('auth/login',
      JSON.stringify(data),
      {
        headers: {'Content-Type': 'application/json'}
      }
    );
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
    </form>
  )
}

export default Login
