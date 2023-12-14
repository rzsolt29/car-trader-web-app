package com.backend.cartrader.services;

import com.backend.cartrader.error.ErrorCode;
import com.backend.cartrader.exception.AuthenticationException;
import com.backend.cartrader.model.Role;
import com.backend.cartrader.model.User;
import com.backend.cartrader.model.dto.CreateUserDto;
import com.backend.cartrader.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void createUser(CreateUserDto request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new AuthenticationException(ErrorCode.EMAIL_ADDRESS_ALREADY_EXISTS, "Given email already exists in database");
        }

        User newUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .createdOn(Instant.now())
                .role(Role.USER)
                .build();

        userRepository.save(newUser);

    }
}
