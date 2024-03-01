package com.backend.cartrader.services;

import com.backend.cartrader.error.ErrorCode;
import com.backend.cartrader.exception.AuthenticationException;
import com.backend.cartrader.exception.AuthorizationException;
import com.backend.cartrader.exception.NonExistingCarException;
import com.backend.cartrader.model.Car;
import com.backend.cartrader.model.ERole;
import com.backend.cartrader.model.Role;
import com.backend.cartrader.model.User;
import com.backend.cartrader.payload.request.CreateCarRequest;
import com.backend.cartrader.payload.request.SearchForCarRequest;
import com.backend.cartrader.payload.response.MessageResponse;
import com.backend.cartrader.repository.CarRepository;
import com.backend.cartrader.repository.UserRepository;
import com.backend.cartrader.repository.specification.GenericSpesification;
import com.backend.cartrader.repository.specification.SearchCriteria;
import com.backend.cartrader.repository.specification.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    CarRepository carRepository;

    UserRepository userRepository;

    public ResponseEntity<MessageResponse> createCar(CreateCarRequest createCarRequest) {

        Optional<User> loggedInUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (loggedInUser.isEmpty()){
            throw new AuthenticationException(ErrorCode.UNKNOWN_USER, "Internal error");
        }
        Car newCar = Car.builder()
                .publishedOn(Instant.now())
                .make(createCarRequest.getMake())
                .model(createCarRequest.getModel())
                .price(createCarRequest.getPrice())
                .bodyType(createCarRequest.getBodyType())
                .gearbox(createCarRequest.getGearbox())
                .fuelType(createCarRequest.getFuelType())
                .productionYear(createCarRequest.getProductionYear())
                .colour(createCarRequest.getColour())
                .doors(createCarRequest.getDoors())
                .engineSize(createCarRequest.getEngineSize())
                .enginePower(createCarRequest.getEnginePower())
                .drivetrain(createCarRequest.getDrivetrain())
                .owner(loggedInUser.get())
                .build();

        carRepository.save(newCar);

        return new ResponseEntity<>(new MessageResponse("New car created"), HttpStatus.CREATED);

    }

    public ResponseEntity<List<Car>> getMyCars() {

        Optional<User> loggedInUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (loggedInUser.isEmpty()){
            throw new AuthenticationException(ErrorCode.UNKNOWN_USER, "Internal error");
        }

        List<Car> userCars = carRepository.findAllByOwner(loggedInUser.get());

        for (Car userCar : userCars) {
            userCar.setOwner(null);
        }

        return ResponseEntity.ok(userCars);
    }

    public Car getCar(Integer id) {

        Optional<Car> car = carRepository.findById(id);

        if (car.isEmpty()){
            throw new NonExistingCarException(ErrorCode.INVALID_CAR_ID, "Car not found with given id");
        }
        else {
            User owner = car.get().getOwner();
            owner.setPassword(null);
            car.get().setOwner(owner);

            return car.get();
        }
    }

    public ResponseEntity<List<Car>> getCarsByParameters(@RequestBody SearchForCarRequest request) {

        GenericSpesification<Car> genericSpesification = new GenericSpesification<>();

        if (request.getMake() != null && !request.getMake().isBlank()) {
            genericSpesification.add(new SearchCriteria("make", request.getMake(), SearchOperation.EQUAL));
        }

        if (request.getModel() != null && !request.getModel().isBlank()) {
            genericSpesification.add(new SearchCriteria("model", request.getModel(), SearchOperation.EQUAL));
        }

        if (request.getPriceFrom() != null) {
            genericSpesification.add(new SearchCriteria("price", request.getPriceFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }

        if (request.getPriceTo() != null) {
            genericSpesification.add(new SearchCriteria("price", request.getPriceTo(), SearchOperation.LESS_THAN_EQUAL));
        }

        if (request.getBodyType() != null) {
            genericSpesification.add(new SearchCriteria("bodyType", request.getBodyType(), SearchOperation.EQUAL));
        }

        if (request.getGearbox() != null && !request.getGearbox().isBlank()) {
            genericSpesification.add(new SearchCriteria("gearbox", request.getGearbox(), SearchOperation.EQUAL));
        }

        if (request.getFuelType() != null && !request.getFuelType().isBlank()) {
            genericSpesification.add(new SearchCriteria("fuelType", request.getFuelType(), SearchOperation.EQUAL));
        }

        if (request.getProductionYearFrom() != null) {
            genericSpesification.add(new SearchCriteria("productionYear", request.getProductionYearFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }

        if (request.getProductionYearTo() != null) {
            genericSpesification.add(new SearchCriteria("productionYear", request.getProductionYearTo(), SearchOperation.LESS_THAN_EQUAL));
        }

        if (request.getColour() != null && !request.getColour().isBlank()) {
            genericSpesification.add(new SearchCriteria("colour", request.getColour(), SearchOperation.EQUAL));
        }

        if (request.getDoors() != null) {
            genericSpesification.add(new SearchCriteria("doors", request.getDoors(), SearchOperation.EQUAL));
        }

        if (request.getEngineSizeFrom() != null) {
            genericSpesification.add(new SearchCriteria("engineSize", request.getEngineSizeFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }

        if (request.getEngineSizeTo() != null) {
            genericSpesification.add(new SearchCriteria("engineSize", request.getEngineSizeTo(), SearchOperation.LESS_THAN_EQUAL));
        }

        if (request.getEnginePowerFrom() != null) {
            genericSpesification.add(new SearchCriteria("enginePower", request.getEnginePowerFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }

        if (request.getEnginePowerTo() != null) {
            genericSpesification.add(new SearchCriteria("enginePower", request.getEnginePowerTo(), SearchOperation.LESS_THAN_EQUAL));
        }

        if (request.getDrivetrain() != null) {
            genericSpesification.add(new SearchCriteria("drivetrain", request.getDrivetrain(), SearchOperation.EQUAL));
        }

        List<Car> foundCars = carRepository.findAll(genericSpesification);

        for (Car car : foundCars) {
            User owner = car.getOwner();
            owner.setPassword(null);
            car.setOwner(owner);
        }

        return ResponseEntity.ok(foundCars);
    }

    public ResponseEntity<MessageResponse> deleteCar(Integer carId) {
        Optional<User> loggedInUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (loggedInUser.isEmpty()){
            throw new AuthenticationException(ErrorCode.UNKNOWN_USER, "Internal error");
        }

        if (loggedInUser.get().getRoles().contains(new Role(2, ERole.ROLE_ADMIN))) {

            carRepository.deleteById(carId);

        }else if (loggedInUser.get().getRoles().contains(new Role(1, ERole.ROLE_USER))) {

            Optional<Car> car = carRepository.findById(carId);

            if (car.isEmpty()) {
                throw new NonExistingCarException(ErrorCode.INVALID_CAR_ID, "Car not found with given id");
            }

            if (Objects.equals(car.get().getOwner().getId(), loggedInUser.get().getId())) {
                carRepository.deleteById(carId);
            }else {
                throw new AuthorizationException(ErrorCode.CAR_BELONGS_TO_DIFFERENT_USER, "Car with given id does not belongs to logged in user");
            }
        }

        return ResponseEntity.ok(new MessageResponse("Car deleted successfully"));
    }
}
