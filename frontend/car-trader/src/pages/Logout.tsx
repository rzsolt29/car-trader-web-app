import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DeleteJwtCookie } from "../utils/DeleteJwtCookie";
import Cookies from 'universal-cookie';
import { boolean } from "yup";

const cookies = new Cookies();

const Logout = () => {

    const navigate = useNavigate();
    
    var jwtCookieDeleted = false;
    if (jwtCookieDeleted) {
        navigate("/")
    }

return (
    <div className="logout">
        <DeleteJwtCookie/>
        Logged out
        <button className="backToHome" onClick={() => navigate("/")}> Back to main page</button>
    </div>
    
)
}

export default Logout