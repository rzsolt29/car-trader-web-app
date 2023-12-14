package com.backend.cartrader.error;

import com.backend.cartrader.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AuthenticationException exception, WebRequest request) {
        return checkExceptions(exception, request);
    }


    private ResponseEntity<ApiError> checkExceptions(AbstractRuntimeExceptionInheritance exception, WebRequest request) {
        if (exception != null) {

            //log.warn(exception.getMessage(), exception);
            ApiError apiError = new ApiError(exception.getErrorCode(), exception.getMessage());
            if (request != null) {
                try {
                    if (request instanceof ServletWebRequest) {
                        apiError.setRequestUrl(((ServletWebRequest) request).getRequest().getRequestURI());
                        apiError.setRequestType(((ServletWebRequest) request).getRequest().getMethod());
                    }
                } catch (Exception ex) {
                    log.warn(ex.getMessage());
                }
            }
            return ResponseEntity.status(exception.getErrorCode().getStatus()).body(apiError);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
