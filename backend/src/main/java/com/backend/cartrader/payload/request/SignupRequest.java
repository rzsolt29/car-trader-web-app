package com.backend.cartrader.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(max = 45)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 60)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,25}$",
            message="Password must contain one number, one lowercase and one uppercase character. It has to be at least 8 character long.")
    private String password;
}
