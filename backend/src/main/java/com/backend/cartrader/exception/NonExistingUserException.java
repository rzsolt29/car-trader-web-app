package com.backend.cartrader.exception;

import com.backend.cartrader.error.AbstractRuntimeExceptionInheritance;
import com.backend.cartrader.error.ErrorCode;

public class NonExistingUserException extends AbstractRuntimeExceptionInheritance {
    public NonExistingUserException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static NonExistingUserException ofEmail(final ErrorCode errorCode) {
        final String message = String.format("Unable to find user because of reason '%s' ", errorCode);

        return new NonExistingUserException(errorCode, message);
    }
}
