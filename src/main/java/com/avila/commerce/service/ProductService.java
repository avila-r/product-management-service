package com.avila.commerce.service;
import com.avila.commerce.contract.ProductContract;
import com.avila.commerce.model.Product;
import com.avila.commerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class ProductService implements ProductContract {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // TODO ------------------------------------------------------>

    @Override
    public Product getProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return Optional.empty();
    }
}