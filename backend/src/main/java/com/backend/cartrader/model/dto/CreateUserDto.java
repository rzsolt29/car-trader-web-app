package com.backend.cartrader.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    @Email
    private String email;
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,25}$",
            message="Password must contain one number, one lowercase and one uppercase character. It has to be at least 8 character long.")
    private String password;
}
