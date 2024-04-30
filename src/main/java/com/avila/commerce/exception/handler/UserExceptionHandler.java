package com.avila.commerce.exception.handler;
import com.avila.commerce.exception.user.UsernameAlreadyExistsException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    public record UserExceptionMessage(HttpStatus status, String reason){}

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    ResponseEntity<UserExceptionMessage> handleUsernameAlreadyExistsException(@NotNull UsernameAlreadyExistsException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Status-Reason", e.getMessage());
        headers.add("X-Status-Error", "Conflict");
        headers.add("X-Status-Exception", e.getClass().getName());
        headers.add("X-Status-Message", "Username already in use");
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(
                        new UserExceptionMessage(
                                status,
                                "Username already exists in use and cannot be created. It must be unique."
                        )
                );
    }
}