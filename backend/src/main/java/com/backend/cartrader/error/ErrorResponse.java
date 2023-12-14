package com.backend.cartrader.error;

import lombok.Value;

@Value
public class ErrorResponse {
    private final ApiError error;

}
