import React from "react";
import { useNavigate } from "react-router-dom";
import { DeleteJwtCookie } from "../utils/DeleteJwtCookie";
import "./Logout.css";


const Logout = () => {

    const navigate = useNavigate();
    
    var jwtCookieDeleted = false;
    if (jwtCookieDeleted) {
        navigate("/")
    }

return (
    <div className="logout">
        <DeleteJwtCookie/>
        <h1>Logged out</h1>
        <button className="backToHome" onClick={() => navigate("/")}> Back to main page</button>
    </div>
)
}

export default Logout