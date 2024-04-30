package com.avila.commerce.contract;
import com.avila.commerce.model.Product;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ProductContract {
    Product saveProduct(Product product);
    Product updateProduct(Product product);

    ResponseEntity<?> deleteProduct(Product product);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);

    Product getProductById(Long id);
    Product getProductByName(String name);
}