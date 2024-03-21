import React, { useState } from "react";
import { SignInFormState } from "../interfaces/SignInFormState";
import { apiLoginRequest } from "../api/apiAuthActions"

const Login = () => {
    
  const [formData, setFormData] = useState<SignInFormState> ({
    email: '',
    password: ''
  })

  
const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  const {name, value} = e.target;
  setFormData(prevData => ({...prevData, [name]: value}))
}


const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {

      const response = apiLoginRequest(formData);
      console.log(response);

    }catch (error) {
      console.error(error);
    }
  }


console.log(formData);

return (
    <div className="login">
  <form onSubmit={handleSubmit}>

    <div>
      <label htmlFor="email">Email:</label> 
      <br />
      <input type="email" name="email" id="email" onChange={handleChange} value={formData.email} required maxLength={45} />
      <br />
    </div>

    <div>
      <label htmlFor="password">Password:</label> 
      <br />
      <input type="password" name="password" id="password" onChange={handleChange} value={formData.password} required maxLength={60} />
      <br />
    </div>

    <button type="submit">Sign Up</button>

  </form>
</div>
    
)
}

export default Login