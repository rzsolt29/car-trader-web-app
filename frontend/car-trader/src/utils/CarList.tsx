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