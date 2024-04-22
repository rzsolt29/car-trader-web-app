import React from "react";
import "./Navbar.css";

const Home = () => {
    return (
        <div>
            <header>
                <a className="brand" href="/" >Home</a>   
            <nav>
                <ul>
                    <li><a href="/login" >Account</a></li>
                </ul>
            </nav>
            </header>
            <section>
                <h2>This is the main page.</h2>
            </section>
        </div>
    )
}

export default Home