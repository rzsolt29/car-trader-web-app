package com.backend.cartrader.controllers;

import com.backend.cartrader.payload.request.LoginRequest;
import com.backend.cartrader.payload.request.SignupRequest;
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a new user")
    public ResponseEntity<?> register(
            @Valid @RequestBody SignupRequest request
    ){
        return authService.createUser(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login a user")
    public ResponseEntity<?> authenticate(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
