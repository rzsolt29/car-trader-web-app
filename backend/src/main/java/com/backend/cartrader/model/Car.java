package com.backend.cartrader.model;

import com.backend.cartrader.config.validation.EnumNamePattern;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "published_on")
    private Instant publishedOn;

    @Size(max = 45)
    private String make;

    @Size(max = 60)
    private String model;

    private Integer price;

    @Column(name = "body_type")
    @Enumerated(EnumType.STRING)
    @EnumNamePattern(regexp = "CONVERTIBLE|COUPE|ESTATE|HATCHBACK|MPV|PICKUP|SALOON|SUV")
    private BodyType bodyType;

    @Size(max = 15)
    private String gearbox;

    @Size(max = 15)
    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "production_year")
    private Integer productionYear;

    @Size(max = 30)
    private String colour;

    private Integer doors;

    @Column(name = "engine_size")
    private Integer engineSize;

    @Column(name = "engine_power")
    private Integer enginePower;

    @Enumerated(EnumType.STRING)
    @EnumNamePattern(regexp = "DRIVETRAIN_FWD|DRIVETRAIN_RWD|DRIVETRAIN_AWD|DRIVETRAIN_4WD")
    private Drivetrain drivetrain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

}
