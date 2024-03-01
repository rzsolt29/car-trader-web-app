package com.backend.cartrader.exception;

import com.backend.cartrader.error.AbstractRuntimeExceptionInheritance;
import com.backend.cartrader.error.ErrorCode;

public class AuthorizationException extends AbstractRuntimeExceptionInheritance {
    public AuthorizationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    public static AuthorizationException of(final ErrorCode errorCode) {
        final var message = String.format("Rejecting authorization because of reason '%s'", errorCode);

        return new AuthorizationException(errorCode, message);
    }

}
