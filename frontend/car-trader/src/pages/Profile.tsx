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


    // TODO handle infinit loop when user does not have any car
    if (cars.length === 0) {
        const response = apiGetUsersCarsRequest();

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
                <h2>profile page</h2>
                <CarList cars={cars} />
            </section>
        </div>
    )
}

export default Profile