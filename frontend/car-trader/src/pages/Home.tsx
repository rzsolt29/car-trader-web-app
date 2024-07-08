import React from "react";
import { Navbar } from "../utils/Navbar";
import "./Navbar.css";
import "../utils/Navbar"


const Home = () => {
    return (
        <div>
            <header>
                <Navbar />
            </header>
            <section>
                <h2>This is main page.</h2>

            </section>
        </div>
    )
}

export default Home