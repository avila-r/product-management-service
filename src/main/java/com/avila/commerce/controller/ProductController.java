package com.avila.commerce.controller;
import com.avila.commerce.model.Product;
import com.avila.commerce.service.ProductAccessService;
import com.avila.commerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/commerce/product")
@AllArgsConstructor
public class ProductController {
    private final ProductAccessService productAccessService;
    private final ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<Product.ProductResponseDTO> saveProduct(@RequestBody Product.ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(
                productAccessService.convertProductToResponse(
                        productService.saveProduct(productAccessService.convertRequestToProduct(productRequestDTO))
                )
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody Product.ProductRequestDTO productRequestDTO){
        return productService.deleteProduct(productAccessService.convertRequestToProduct(productRequestDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<Product.ProductResponseDTO> updateProduct(@RequestBody Product.ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(
                productAccessService.convertProductToResponse(
                        productService.updateProduct(productAccessService.convertRequestToProduct(productRequestDTO))
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Product.ProductResponseDTO>> getAll(){
        return ResponseEntity.ok(
                productService.getAllProducts().stream()
                        .map(productAccessService::convertProductToResponse)
                        .toList()
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product.ProductResponseDTO> getByIdEndpoint(@PathVariable Long id){
        return ResponseEntity.ok(
                productAccessService.convertProductToResponse(productService.getProductById(id))
        );
    }

    @GetMapping("/id")
    public ResponseEntity<Product.ProductResponseDTO> getByIdParam(@RequestParam(name = "id", defaultValue = "0") Long id){
        return ResponseEntity.ok(
                productAccessService.convertProductToResponse(productService.getProductById(id))
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product.ProductResponseDTO>> listByCategoryEndpoint(@PathVariable String category){
        return ResponseEntity.ok(
                productService.getProductsByCategory(category).stream()
                        .map(productAccessService::convertProductToResponse)
                        .toList()
        );
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product.ProductResponseDTO>> listByCategoryParam(@RequestParam(name = "category") String category){
        return ResponseEntity.ok(
                productService.getProductsByCategory(category).stream()
                        .map(productAccessService::convertProductToResponse)
                        .toList()
        );
    }
}