package com.backend.cartrader.controllers;

import com.backend.cartrader.model.dto.CreateUserDto;
import com.backend.cartrader.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a new user")
    public void register(
            @Valid @RequestBody CreateUserDto request
    ){
        userService.createUser(request);
    }

}
