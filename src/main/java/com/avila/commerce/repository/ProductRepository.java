package com.avila.commerce.repository;
import com.avila.commerce.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
    Optional<List<Product>> findByCategory(String category);
}