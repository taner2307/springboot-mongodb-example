package com.tutorial.controller;

import com.tutorial.model.Product;
import com.tutorial.repository.ProductRepository;
import com.tutorial.request.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllProductsById(@PathVariable("id") String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }else {
            return ResponseEntity.ok("The product with id: " + id+ " was not found");
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        return ResponseEntity.status(201).body(productRepository.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.ok("The product with id: " + id+ " was not found");
        }
    }

}

