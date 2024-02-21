package com.backend.cartrader.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    EMAIL_ADDRESS_ALREADY_EXISTS(ErrorType.VALIDATION, HttpStatus.CONFLICT),
    UNKNOWN_USER(ErrorType.AUTHENTICATION, HttpStatus.NOT_FOUND),
    WRONG_LOGIN_PASSWORD(ErrorType.AUTHENTICATION, HttpStatus.CONFLICT),
    ACCESS_DENIED(ErrorType.AUTHORIZATION, HttpStatus.UNAUTHORIZED),
    MISSING_TOKEN(ErrorType.AUTHORIZATION, HttpStatus.FORBIDDEN),
    INVALID_TOKEN(ErrorType.AUTHORIZATION, HttpStatus.FORBIDDEN),
    NON_EXISTING_EMAIL(ErrorType.VALIDATION, HttpStatus.NOT_FOUND),
    TOO_MANY_REQUESTS(ErrorType.VALIDATION, HttpStatus.TOO_MANY_REQUESTS),
    INVALID_CAR_ID(ErrorType.VALIDATION, HttpStatus.BAD_REQUEST);

    private final ErrorType errorType;

    private final HttpStatus status;

    ErrorCode(final ErrorType errorType, HttpStatus status) {
        this.errorType = errorType;
        this.status = status;
    }

}
