package com.avila.commerce.contract;
import com.avila.commerce.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductContract {
    Product saveProduct(Product product);
    Product getProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Product product);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);

    Optional<Product> getProductById(Long id);
    Optional<Product> getProductByName(String name);
}