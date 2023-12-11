package com.backend.cartrader.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    @Autowired
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Car trading API")
                        .description("Car trading backend API"));
    }
}
