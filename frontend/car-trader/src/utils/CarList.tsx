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
            <ul>
            {items.cars.map(car => (
                <li key={car.id}>
                    <span>{car.make}</span>
                </li>
            ))}
            </ul>
    )
}