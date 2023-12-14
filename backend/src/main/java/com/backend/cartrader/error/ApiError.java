package com.backend.cartrader.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ApiError {

    private final ErrorCode errorCode;

    private String errorMessage;

    private String requestUrl;

    private String requestType;

    private Instant timeStamp = Instant.now();

    public ApiError(ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
