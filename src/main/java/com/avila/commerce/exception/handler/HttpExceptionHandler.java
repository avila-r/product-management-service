package com.avila.commerce.exception.handler;
import com.avila.commerce.model.Product;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@ControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @PostMapping("/commerce/product")
    public ResponseEntity<InvalidProductRequestMessage> handleHttpMessageNotReadableException(@NotNull HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Status-Reason", e.getMessage());
        headers.add("X-Status-Error", "Bad Request");
        headers.add("X-Status-Exception", e.getClass().getName());
        headers.add("X-Status-Message", "Invalid request body");
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(
                        new InvalidProductRequestMessage(
                                status,
                                "Expected convertable JSON request body, but received invalid request body.",
                                e.getHttpInputMessage(),
                                new Product.ProductRequestDTO(
                                        "Product Name",
                                        "Product Category",
                                        "Product Description",
                                        500.0,
                                        10
                                )
                        )
                );
    }

    public record InvalidProductRequestMessage(
            HttpStatus status,
            String reason,
            HttpInputMessage input,
            Product.ProductRequestDTO model
    ){
        @Override @Contract(pure = true)
        public @NotNull String toString() {
            return "InvalidProductRequestMessage" +
                    "{" +
                    "Status: " + status +
                    ", Reason: '" + reason + '\'' +
                    "\n" +
                    ", Invalid request: \n" + input + '\'' +
                    ", Expected request: \n" + model +
                    "\n" +
                    "}";
        }
    }
}