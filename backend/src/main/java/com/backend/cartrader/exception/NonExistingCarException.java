package com.backend.cartrader.exception;

import com.backend.cartrader.error.AbstractRuntimeExceptionInheritance;
import com.backend.cartrader.error.ErrorCode;

public class NonExistingCarException extends AbstractRuntimeExceptionInheritance {
    public NonExistingCarException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static NonExistingCarException ofEmail(final ErrorCode errorCode) {
        final String message = String.format("Unable to find car because of reason '%s' ", errorCode);

        return new NonExistingCarException(errorCode, message);
    }
}
