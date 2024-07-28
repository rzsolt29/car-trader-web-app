import React, { useState } from "react";
import { apiGetUsersCarsRequest } from "../api/apiCarActions";
import { CarList } from "../utils/CarList";
import { Navbar } from "../utils/Navbar";
import "./Navbar.css";

const Profile = () => {

    interface Car {
        id: number;
        publishedOn: any;
        make: string;
        model: string;
        price: number;
    }
    const [cars, setCarList] = useState<Car[]>([]);
    const [carListRequested, setCarListRequested] = useState(false);


    var response = null;
    if (!carListRequested) {
        response = apiGetUsersCarsRequest();
        setCarListRequested(true);
        console.log(response);

        response
        .then((result) => {
            setCarList(result.data);
        })
    }


    return (
        <div>
            <header>
                <Navbar />
            </header>
            <section>
                <CarList cars={cars} />
            </section>
        </div>
    )
}

export default Profile
