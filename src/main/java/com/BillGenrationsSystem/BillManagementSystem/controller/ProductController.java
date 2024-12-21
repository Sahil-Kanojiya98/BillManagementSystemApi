package com.BillGenrationsSystem.BillManagementSystem.controller;


import com.BillGenrationsSystem.BillManagementSystem.model.dto.ProductStockRequestDto;
import com.BillGenrationsSystem.BillManagementSystem.model.Product;
import com.BillGenrationsSystem.BillManagementSystem.repository.ProductRepository;
import com.BillGenrationsSystem.BillManagementSystem.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductStockRequestDto product) {
        try{
            return new ResponseEntity<>(productsService.createProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error placing order: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Product> all(){
        return productRepository.findAll();
    }

}
