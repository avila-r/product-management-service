package com.avila.commerce.contract;
import com.avila.commerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductAccessContract {
    Product convertRequestToProduct(Product.ProductRequestDTO productRequestDTO);
    Product.ProductResponseDTO convertProductToResponse(Product product);
}