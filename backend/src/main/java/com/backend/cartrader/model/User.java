package com.backend.cartrader.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 45)
    private String email;

    @Size(max = 60)
    private String password;

    @Column(name = "created_on")
    private Instant createdOn;

    @Enumerated(EnumType.STRING)
    private Role role;
}
