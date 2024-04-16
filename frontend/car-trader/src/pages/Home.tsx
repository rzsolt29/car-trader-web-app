import React from "react";
import "./Home.css"

const Home = () => {
    return (
        <div>
            <header>
            <a className="navbar-brand" href="/" >Home</a>
            <a className="navbar-right" href="/login" >Account</a>
            </header>
        </div>
    )
}

export default Home