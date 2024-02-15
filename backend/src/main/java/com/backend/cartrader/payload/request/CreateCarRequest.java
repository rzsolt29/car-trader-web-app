package com.backend.cartrader.payload.request;

import com.backend.cartrader.model.BodyType;
import com.backend.cartrader.model.Drivetrain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    @NotBlank
    @Size(max = 45)
    private String make;

    @Size(max = 60)
    private String model;

    private Integer price;

    @Size(max = 15)
    private BodyType bodyType;

    @Size(max = 15)
    private String gearbox;

    @Size(max = 15)
    private String fuelType;

    private Integer productionYear;

    @Size(max = 30)
    private String colour;

    private Integer doors;

    private Integer engineSize;

    private Integer enginePower;

    @Size(max = 30)
    private Drivetrain drivetrain;
}
