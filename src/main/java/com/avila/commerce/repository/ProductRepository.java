package com.avila.commerce.repository;
import com.avila.commerce.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
}