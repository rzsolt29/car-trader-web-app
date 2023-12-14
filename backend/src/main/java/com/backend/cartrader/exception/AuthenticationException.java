package com.backend.cartrader.exception;

import com.backend.cartrader.error.AbstractRuntimeExceptionInheritance;
import com.backend.cartrader.error.ErrorCode;

public class AuthenticationException extends AbstractRuntimeExceptionInheritance {
    public AuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    public static AuthenticationException of(final ErrorCode errorCode) {
        final var message = String.format("Rejecting authentication because of reason '%s'", errorCode);

        return new AuthenticationException(errorCode, message);
    }

}
