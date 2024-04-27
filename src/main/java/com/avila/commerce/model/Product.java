package com.avila.commerce.model;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Entity @Table(name = "products")
@Getter @EqualsAndHashCode(of = "id")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;

    public record ProductRequestDTO(
            String name,
            String description,
            Double price,
            Integer stock
    ) {
        public @NotNull Product toEntity() {
            Product product = new Product();
            product.name = name;
            product.description = description;
            product.price = price;
            product.stock = stock;
            return product;
        }
    }

    public record ProductResponseDTO(
            String name,
            String description,
            Double price
    ) {
        public ProductResponseDTO(@NotNull Product product) {
            this(product.name, product.description, product.price);
        }
    }
}