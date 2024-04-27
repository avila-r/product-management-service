package com.avila.commerce.controller;
import com.avila.commerce.model.Product;
import com.avila.commerce.service.ProductAccessService;
import com.avila.commerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/commerce/product")
@AllArgsConstructor
public class ProductController {
    private final ProductAccessService productAccessService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product.ProductResponseDTO> saveProduct(Product.ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(
                productAccessService.convertProductToResponse(
                        productService.saveProduct(productAccessService.convertRequestToProduct(productRequestDTO))
                )
        );
    }
}