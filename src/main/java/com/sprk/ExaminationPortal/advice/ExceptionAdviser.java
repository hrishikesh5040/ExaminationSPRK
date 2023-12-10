package com.sprk.ExaminationPortal.advice;

import com.sprk.ExaminationPortal.Exception.InvalidDataException;
import com.sprk.ExaminationPortal.Exception.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdviser {




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtException.class)
    public Map<String, String> handleJwtException(JwtException exception) {

        return getErrorMap(exception);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataException.class)
    public Map<String, String> handleInvalidDataException(InvalidDataException exception) {
        return getErrorMap(exception);
    }




    private Map<String, String> getErrorMap(Throwable exception) {
        // Create a map to store error messages and their corresponding field names (if applicable)
        Map<String, String> errorMap = new HashMap<>();

        // Use a loop to process the exception and its causes
        Throwable currentException = exception;
        while (null != currentException) {
            if (currentException instanceof BindException) {
                BindException bindException = (BindException) currentException;
                if (!bindException.getFieldErrors().isEmpty()) {
                    FieldError firstError = bindException.getFieldErrors().get(0);
                    errorMap.put("error", firstError.getDefaultMessage());
                    break; // Exit loop since we've added an error message
                }
            } else if (currentException instanceof MissingRequestHeaderException) {
                errorMap.put("error", "Required request header 'Authorization' is missing");
                break; // Exit loop since we've added an error message
            } else if (currentException instanceof RuntimeException) {
                errorMap.put("error", currentException.getMessage());
                break; // Exit loop since we've added an error message
            }

            currentException = currentException.getCause(); // Move to the next cause
        }

        return errorMap;
    }
}
