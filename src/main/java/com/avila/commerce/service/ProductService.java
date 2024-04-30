package com.avila.commerce.service;
import com.avila.commerce.contract.ProductContract;
import com.avila.commerce.exception.product.ProductAlreadyExistsException;
import com.avila.commerce.exception.product.ProductNotFoundException;
import com.avila.commerce.model.Product;
import com.avila.commerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @AllArgsConstructor
public class ProductService implements ProductContract {
    private final ProductRepository productRepository;
    private final ProductAccessService productAccessService;

    @Override
    public Product saveProduct(@NotNull Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ProductAlreadyExistsException("Name already in use");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(@NotNull Product product) {
        if (productRepository.existsByName(product.getName())){
            productRepository.delete(
                    productRepository.findByName(product.getName())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found")));
            return productRepository.save(product);
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public ResponseEntity<String> deleteProduct(@NotNull Product product) {
        if (productRepository.existsByName(product.getName())) {
            productRepository.delete(
                    productRepository.findByName(product.getName())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"))
            );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Product deleted successfully: " + productAccessService.convertProductToResponse(product));
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category)
                .orElseThrow(() -> new ProductNotFoundException("There are no products in this category"));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
}