package com.backend.cartrader.services;

import com.backend.cartrader.error.ErrorCode;
import com.backend.cartrader.exception.AuthenticationException;
import com.backend.cartrader.model.Car;
import com.backend.cartrader.model.User;
import com.backend.cartrader.payload.request.CreateCarRequest;
import com.backend.cartrader.payload.response.MessageResponse;
import com.backend.cartrader.repository.CarRepository;
import com.backend.cartrader.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
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

        for (int i = 0; i < userCars.size(); i++) {
            userCars.get(i).setOwner(null);
        }

        return ResponseEntity.ok(userCars);
    }
}
