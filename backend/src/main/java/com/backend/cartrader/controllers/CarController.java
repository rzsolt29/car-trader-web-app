package com.backend.cartrader.controllers;

import com.backend.cartrader.payload.request.CreateCarRequest;
import com.backend.cartrader.services.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API")
@AllArgsConstructor
public class CarController {

    private CarService carService;

    @PostMapping("/add-car")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a new car")
    public ResponseEntity<?> createNewCar(
            @Valid @RequestBody CreateCarRequest request
    ){
        return carService.createCar(request);
    }

}
