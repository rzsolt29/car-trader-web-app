import React, { useState } from "react";
import { SignUpFormState } from "../interfaces/SignUpFormState";
import { apiRegistrationRequest } from "../api/apiAuthActions";
import { Navbar } from "../utils/Navbar";
import "./Navbar.css";
import "./Register.css";
import { RegistrationError } from "../utils/RegistrationError";

const Register = () => {

    const [formData, setFormData] = useState<SignUpFormState> ({
        email: '',
        password: '',
        confirm_password: ''
      })

      enum ERegistrationStatus {
        pending = 0,
        success = 1,
        error = 2
      }

      const [registrationStatus, setRegistrationStatus] = useState<ERegistrationStatus>(ERegistrationStatus.pending)

      
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      const {name, value} = e.target;
      setFormData(prevData => ({...prevData, [name]: value}))
    }

    
    const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
          if (formData.password !== formData.confirm_password) {
            throw new Error("Password and confirm password must be the same");
          }
          apiRegistrationRequest(formData).catch((e: RegistrationError) => setRegistrationStatus(ERegistrationStatus.error));
          setRegistrationStatus(ERegistrationStatus.success);

        }catch (error: any) {
          setRegistrationStatus(ERegistrationStatus.error);
        }
      }


    return (
        <div className="register">
          <header>
              <Navbar />
          </header>
      <form className="registrationForm" onSubmit={handleSubmit}>

        <div className="RegistrationHeader">Create Account</div>
        <div>
          <label className="fieldText" htmlFor="email">Email:</label> 
          <br />
          <input className="inputField"type="email" name="email" id="email" onChange={handleChange} value={formData.email} required maxLength={45} />
          <br />
        </div>

        <div>
          <label className="fieldText" htmlFor="password">Password:</label> 
          <br />
          <input className="inputField" type="password" name="password" id="password" onChange={handleChange} value={formData.password} required maxLength={60} />
          <br />
        </div>

        <div>
          <label className="fieldText" htmlFor="confirm_password">Confirm password:</label> 
          <br />
          <input className="inputField" type="password" name="confirm_password" id="confirm_password" onChange={handleChange} value={formData.confirm_password} required maxLength={60} />
          <br />
        </div>

        <button className="submitBtn" type="submit">Sign Up</button>
        {registrationStatus === ERegistrationStatus.success && <p>Registration successful</p>}
        {registrationStatus === ERegistrationStatus.error && <p>Registration error</p>}

      </form>
    </div>
        
    )
}

export default Register