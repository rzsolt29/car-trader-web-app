import React, { useState } from "react";
import { SignUpFormState } from "../interfaces/SignUpFormState";
import { apiRegistrationRequest } from "../api/apiAuthActions"

const Register = () => {

    const [formData, setFormData] = useState<SignUpFormState> ({
        email: '',
        password: '',
        confirm_password: ''
      })

      
      const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setFormData(prevData => ({...prevData, [name]: value}))
      }

    
      const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
          const response = apiRegistrationRequest(formData);
          console.log(response);
        }catch (error) {
          console.error(error);
        }
      }

    
      console.log(formData);

    return (
        <div className="register">
      <form onSubmit={handleSubmit}>

        <div>
          <label htmlFor="email">Email:</label> 
          <br />
          <input type="email" name="email" id="email" onChange={handleChange} value={formData.email} required maxLength={45}/>
          <br />
        </div>

        <div>
          <label htmlFor="password">Password:</label> 
          <br />
          <input type="password" name="password" id="password" onChange={handleChange} value={formData.password} required maxLength={60}/>
          <br />
        </div>

        <div>
          <label htmlFor="confirm_password">Confirm password:</label> 
          <br />
          <input type="password" name="confirm_password" id="confirm_password" onChange={handleChange} value={formData.confirm_password} required maxLength={60} 
          />
          <br />
        </div>

        <button>Sign Up</button>

      </form>
    </div>
        
    )
}

export default Register