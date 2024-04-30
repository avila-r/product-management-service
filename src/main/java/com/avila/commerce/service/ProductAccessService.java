package com.avila.commerce.service;
import com.avila.commerce.contract.ProductAccessContract;
import com.avila.commerce.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductAccessService implements ProductAccessContract {
    @Override
    public @NotNull Product convertRequestToProduct(Product.@NotNull ProductRequestDTO productRequestDTO) {
        return productRequestDTO.toEntity();
    }

    @Override
    public Product.@NotNull ProductResponseDTO convertProductToResponse(Product product) {
        return new Product.ProductResponseDTO(product);
    }
}