import "./CarList.css";
  
  interface Car {
    id: number;
    publishedOn: any;
    make: string;
    model: string;
    price: number;
  }
  
  interface CarListProps {
    cars: Car[];
  };

export const CarList = (items: CarListProps) => {
    
    if (items.cars.length === 0) {
      return (
        <h2>You have no cars for sale</h2>    
    )
    }

    return (
            <ul className="tiles-container">
            {items.cars.map(car => (
                <li className="tile" key={car.id}>
                    <span>{car.make}</span>
                    <div>{car.model}</div>
                    { car.price != null ? <div>HUF {car.price}</div> : null}
                </li>
            ))}
            </ul>
    )
}