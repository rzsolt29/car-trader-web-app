package com.backend.cartrader.services;

import com.backend.cartrader.config.security.jwt.JwtUtils;
import com.backend.cartrader.config.security.services.UserDetailsImpl;
import com.backend.cartrader.error.ErrorCode;
import com.backend.cartrader.exception.AuthenticationException;
import com.backend.cartrader.model.*;
import com.backend.cartrader.payload.request.LoginRequest;
import com.backend.cartrader.payload.request.SignupRequest;
import com.backend.cartrader.payload.response.JwtResponse;
import com.backend.cartrader.payload.response.MessageResponse;
import com.backend.cartrader.repository.CarRepository;
import com.backend.cartrader.repository.RoleRepository;
import com.backend.cartrader.repository.UserRepository;
import com.backend.cartrader.repository.UserRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    RoleRepository roleRepository;

    UserRolesRepository userRolesRepository;

    CarRepository carRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;


    public ResponseEntity<MessageResponse> createUser(SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .createdOn(Instant.now())
                .build();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(signUpRequest.getEmail());
        if (savedUser.isPresent()){
            UserRole newUserRole =  UserRole.builder()
                    .userId(savedUser.get().getId())
                    .roleId(userRole.getId())
                    .build();
            userRolesRepository.save(newUserRole);
        }

        return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.CREATED);
    }

    public ResponseEntity<JwtResponse> authenticate(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> deleteUser() {

        Optional<User> loggedInUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (loggedInUser.isEmpty()){
            throw new AuthenticationException(ErrorCode.UNKNOWN_USER, "Internal error");
        }

        List<Car> carsOfLoggedInUser = carRepository.findAllByOwner(loggedInUser.get());

        for (var car : carsOfLoggedInUser) {
            carRepository.delete(car);
        }

        List<UserRole> rolesOfLoggedInUser = userRolesRepository.findAllByUserId(loggedInUser.get().getId());

        for (var roleOfLoggedInUser : rolesOfLoggedInUser) {
            userRolesRepository.delete(roleOfLoggedInUser);
        }

        userRepository.deleteById(loggedInUser.get().getId());

        return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
    }
}
