package com.avila.commerce.contract;
import com.avila.commerce.model.Product;

public interface ProductAccessContract {
    Product convertRequestToProduct(Product.ProductRequestDTO productRequestDTO);
    Product.ProductResponseDTO convertProductToResponse(Product product);
}