package com.avila.commerce.exception.handler;
import com.avila.commerce.exception.product.ProductAlreadyExistsException;
import com.avila.commerce.exception.product.ProductNotFoundException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    public record ProductExceptionMessage(
            HttpStatus status,
            String reason
    ){
        @Override @Contract(pure = true)
        public @NotNull String toString() {
            return "InvalidProductRequestMessage" +
                    "{" +
                    "Status: " + status +
                    ", Reason: '" + reason + '\'' +
                    "}";
        }
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductExceptionMessage> handleProductNotFoundException(@NotNull ProductNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Status-Reason", e.getMessage());
        headers.add("X-Status-Error", "Not Found");
        headers.add("X-Status-Exception", e.getClass().getName());
        headers.add("X-Status-Message", "Product not found");
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(
                        new ProductExceptionMessage(
                                status,
                                "Product was not found in database. Please check the product ID and try again."
                        )
                );
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ProductExceptionMessage> handleProductAlreadyExistsException(@NotNull ProductAlreadyExistsException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Status-Reason", e.getMessage());
        headers.add("X-Status-Error", "Conflict");
        headers.add("X-Status-Exception", e.getClass().getName());
        headers.add("X-Status-Message", "Product already exists");
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(
                        new ProductExceptionMessage(
                                status,
                                "Product already exists in database. Please check the inserted product name and try again."
                        )
                );
    }
}