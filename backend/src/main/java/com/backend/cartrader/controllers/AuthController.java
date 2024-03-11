package com.backend.cartrader.controllers;

import com.backend.cartrader.exception.NonExistingUserException;
import com.backend.cartrader.payload.request.LoginRequest;
import com.backend.cartrader.payload.request.SignupRequest;
import com.backend.cartrader.payload.response.ExceptionResponse;
import com.backend.cartrader.payload.response.JwtResponse;
import com.backend.cartrader.payload.response.MessageResponse;
import com.backend.cartrader.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth API")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/registration")
    @Operation(summary = "Create a new user")
    public ResponseEntity<MessageResponse> register(
            @Valid @RequestBody SignupRequest request
    ){
        return authService.createUser(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user")
    public ResponseEntity<JwtResponse> authenticate(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.authenticate(request);
    }

    @DeleteMapping("/")
    @Operation(summary = "Delete logged in user")
    public ResponseEntity<?> deleteUser() {

        try {

            return authService.deleteUser();

        }catch (NonExistingUserException e) {
            return new ResponseEntity<>(new ExceptionResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
